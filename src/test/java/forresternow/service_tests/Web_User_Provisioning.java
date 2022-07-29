package forresternow.service_tests;

import com.forrester.forresternow.base.BaseForresterNowFunctionalTest;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class Web_User_Provisioning   extends BaseForresterNowFunctionalTest {

    //private static final String RESOURCE_PATH = "/api/content";

    public int getPort() {
        return 9042;
    }

    /**
     * Test the GET contentById method, passing fnComposition as type in the query and the contentId as part of the path.
     */
    @Test
    public void testGetContentById() {
        // num = 9030;
        // RestAssured.port=9030;
        // requestSpecificationMicro.setPort()
        given()
                .log().uri()  // logs all the request
                //.contentType(ContentType.JSON)
                //.queryParam("type", "research")
                .when()
                .spec(requestSpecificationMule)
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
