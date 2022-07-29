package forresternow.service_tests;

import com.forrester.forresternow.base.BaseForresterNowFunctionalTest;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class Web_Shares_and_Grants extends BaseForresterNowFunctionalTest {

    //private static final String RESOURCE_PATH = "/api/content";

    public int getPort() {
        return 9027;
    }

    /**
     * Test the GET contentById method, passing fnComposition as type in the query and the contentId as part of the path.
     */
    @Test
    public void testGetContentByI_num1() {
        // num = 9030;
        // RestAssured.port=9030;
//        requestSpecificationMicro.setPort()
        given()
                .log().uri()  // logs all the request
                //.contentType(ContentType.JSON)
                //.queryParam("type", "research")
                .when()
                .spec(requestSpecificationMicro.get(0))
                .request("GET", "/actuator/health")
                .then()
                .spec(responseSpecification)
                .log().all()//.status()  // logs all the response
                //.log().status()
                .assertThat()  // the assertThat() method, which is only syntactic sugar, (meaning that making the code more readable)
                .statusCode(STATUS_OK)
                .and()
                //.contentType(ContentType.JSON)
                //.and()
                .body("", CoreMatchers.notNullValue())
                .and()
                .body(containsString("UP"))
        ;
    }
    @Parameters({"server_type"})
    @Test
    public void testGetContentByI_num2(String server_type) {

        if ("dev".equals(server_type))
        {
            System.out.println("Goodbye");
            Assert.assertEquals("dev", "anything", "Only 1 dev server!!" );
            return;
        }

        if (requestSpecificationMicro.size() < 2) {
            System.out.println("Hello");
            Assert.assertEquals(requestSpecificationMicro.size(), 2, "Only one server was entered for this port");
            return;
        }

        System.out.println("HIII");
        given()
                .log().uri()  // logs all the request
                //.contentType(ContentType.JSON)
                //.queryParam("type", "research")
                .when()
                .spec(requestSpecificationMicro.get(1))
                .request("GET", "/actuator/health")
                .then()
                .spec(responseSpecification)
                .log().all()//.status()  // logs all the response
                //.log().status()
                .assertThat()  // the assertThat() method, which is only syntactic sugar, (meaning that making the code more readable)
                .statusCode(STATUS_OK)
                .and()
                //.contentType(ContentType.JSON)
                //.and()
                .body("", CoreMatchers.notNullValue())
                .and()
                .body(containsString("UP"))
        ;
    }
    //Test = 21,22
    //Stag = 21,22
    // Prod = 21,22,23
    @Parameters({"server_type"})
    @Test
    public void testGetContentByI_num3(String server_type) {

        if (requestSpecificationMicro.size() < 3) {
            Assert.assertEquals(requestSpecificationMicro.size(), 3, "Only two servers was entered for this port");
            return;
        }
        if ("dev".equals(server_type))
        {
            Assert.assertEquals("dev", "anything", "Only 1 dev server!!" );
            return;
        }
        if ("test".equals(server_type))
        {
            System.out.println("Goodbye");
            Assert.assertEquals("dev", "anything", "Only 2 test server!!" );
            return;
        }
        if ("stg".equals(server_type))
        {
            System.out.println("Goodbye");
            Assert.assertEquals("dev", "anything", "Only 2 stag server!!" );
            return;
        }
        given()
                .log().uri()  // logs all the request
                //.contentType(ContentType.JSON)
                //.queryParam("type", "research")
                .when()
                .spec(requestSpecificationMicro.get(2))
                .request("GET", "/actuator/health")
                .then()
                .spec(responseSpecification)
                .log().all()//.status()  // logs all the response
                //.log().status()
                .assertThat()  // the assertThat() method, which is only syntactic sugar, (meaning that making the code more readable)
                .statusCode(STATUS_OK)
                .and()
                //.contentType(ContentType.JSON)
                //.and()
                .body("", CoreMatchers.notNullValue())
                .and()
                .body(containsString("UP"))
        ;
    }
}

