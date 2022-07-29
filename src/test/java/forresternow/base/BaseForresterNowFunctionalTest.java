package forresternow.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.ArrayList;

/**
 * This abstract class contains generic values and methods that are used for all
 * the ForresterNow REST-Assured functional test cases.
 */
public abstract class BaseForresterNowFunctionalTest {

    // Environment properties
    private static ArrayList<String> BASE_HOST_MICRO = new ArrayList<String>();
    private static final String BASE_PATH_MICRO = "/ForrSVC";

    private static final String BASE_HOST_MULE = "http://web-esbdev21.forrester.com";
    private static final String BASE_PATH_MULE = "";

    // System properties
    // (mvn test -Dserver.port=9000 -Dserver.host=http://example.com)
    private static final String SERVER_HOST = "server.host";
    private static final String SERVER_BASE = "server.base";
    private static final String SERVER_PORT = "server.port";

    // Test user properties
    protected static final String TEST_EMAIL_ADDRESS = "jvazquez@forrester.com";
    protected static final String TEST_USER_ID = "2988862";

    protected static final String CORPORATE_AG = "1-74B5S4";

    // Header definitions (key, value)
    protected static final String API_KEY_HEADER = "apikey";
    protected static final String API_KEY_VALUE = "YmZiZjM5NzktZmRlNS00MTE5LWEwN2UtOTZlZDIyZWIxNzhl";  // DEV
    //protected static final String API_KEY_VALUE = "YmZiZjM5NzktZmRlNS00MTE5LWEwN2UtOTZlZDIyZWIxNzhl";  // TEST


    protected static final String AUTHORIZATION_HEADER = "Authorization";
    protected static String AUTHORIZATION_ID_TOKEN = "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Imp2YXpxdWV6QGZvcnJlc3Rlci5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6Ly9mb3JyZGV2LmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHxEQkNvbm5lY3Rpb25XaXRoU2lnbnVwfDI5ODg4NjIiLCJhdWQiOiJ1aEQwZ1N4ZkZ6aFd1VWV2SVJySHlCMmZZdEREVTJCbSIsImlhdCI6MTU4OTMwMDIwOSwiZXhwIjoxNTkxODkyMjA5fQ.wxjC-cYUdS-P3241CvFQ7LaJjf2iKcnWD2afbEZ6Fu4";
    protected static String AUTHORIZATION_ACCESS_TOKEN = "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik56VXlRa00xUTBWQk5EQTJRamM1TmtRM05rUXpNVE13TVRaRFJETXpRVUV6UTBZMU5FUXhRUSJ9.eyJodHRwOi8vYXBpLmZvcnJlc3Rlci5jb20vdXNlckVtYWlsIjoianZhenF1ZXpAZm9ycmVzdGVyLmNvbSIsImlzcyI6Imh0dHBzOi8vZm9ycmRldi5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8REJDb25uZWN0aW9uV2l0aFNpZ251cHwyOTg4ODYyIiwiYXVkIjpbImh0dHBzOi8vZGV2c3Zjcy5mb3JyZXN0ZXIuY29tL0ZvcnJSRVNUL3YyIiwiaHR0cHM6Ly9mb3JyZGV2LmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE1ODkzMDAyMDksImV4cCI6MTU4OTM4NjYwOSwiYXpwIjoidWhEMGdTeGZGemhXdVVldklSckh5QjJmWXRERFUyQm0iLCJzY29wZSI6Im9wZW5pZCBlbWFpbCBnbGltcHppdCBvZmZsaW5lX2FjY2VzcyIsImd0eSI6InBhc3N3b3JkIn0.X1rZBNXGC_11Z6-Ootkc9LraniMytuLAiLCzO95YCWxi8BRxtF26VYHijNFhs35TT6fGwRIqbUx11lEqMct9ivpBOpXNU2Vw86OUq_lMvBivaVKgEQ-YRqkpoWfx_OVPrXm7xfT_tcQDcvdIH36wJ-eIeEROpJ2FbX4z3M8vYKeaPbzUCPTDFEqzq2sUD4iMrreGIXbchoUQW_ODMofw9e-YAtOgiSY-mAwV9O-nSDF8-uUaL026h1bYuj1HyaX6q9vMwNY9zIKU0fyLcouuziA9K3_NcEFx_0rFhSsxT0h6K3QEHBjID6kGqAJQOic3HdH45Khqxk2CzhZoCOrJiA";


    // HTTP status codes
    protected static final int STATUS_OK = 200;

    // Request Specifications
    public static ArrayList<RequestSpecification> requestSpecificationMicro = new ArrayList<RequestSpecification>();
    public static RequestSpecification requestSpecificationMule;

    // Response Specifications
    public static ResponseSpecification responseSpecification;

    /**
     *
     */
    public static void populateTokens() {


        AUTHORIZATION_ID_TOKEN = "x";
        AUTHORIZATION_ACCESS_TOKEN = "y";
    }

    @BeforeClass
    @Parameters({"server_type", "servers"})
    public void setup(String server_type, String servers) {
        //populateTokens();


        System.out.println("name in @Test is " + server_type);

        if ((!"dev".equals(server_type)) && (!"test".equals(server_type)) && (!"stg".equals(server_type)) && (!"prod".equals(server_type)))
        {
            System.exit(0);
        }

        // int count = 0;

        for (int i = 0; i < servers.length(); i++)
        {
//            if ((!"21".equals(servers.substring(i, i + 2))) && (!"22".equals(servers.substring(i, i +2))) && (!"23".equals(servers.substring(i , i +2))))
//            {
//                System.exit(0);
//            }
            BASE_HOST_MICRO.add("http://web-app" + server_type + servers.substring(i, i + 2) + ".forrester.com");
            i += 2;
           //  count++;
        }

        System.out.println(BASE_HOST_MICRO);
        // Input Validation ^^

        //setting up requestSpecificationMirco Object

        for(int i = 0; i < BASE_HOST_MICRO.size(); i++)
        {
            System.out.println("Hi");
            RequestSpecification temp = new RequestSpecBuilder()
                    .setBaseUri(BASE_HOST_MICRO.get(i))
                    .setBasePath(BASE_PATH_MICRO)
                    .setPort(getPort())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build();
            System.out.println("Bye");
            if(temp != null)
            {
                System.out.println("yES!!!");
                requestSpecificationMicro.add(temp);
//                System.out.println(requestSpecificationMicro.isEmpty());
            }
            else
            {
                System.out.println("Temp is empty!!");
            }
//            requestSpecificationMicro.add(temp);
        }

        System.out.print("HI");

//        requestSpecificationMicro = new RequestSpecBuilder()
//                .setBaseUri(BASE_HOST_MICRO.get(0))
//                .setBasePath(BASE_PATH_MICRO)
//                .setPort(getPort())
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .addHeader("charset", "UTF-8")
//                .build();

        requestSpecificationMule = new RequestSpecBuilder()
                .setBaseUri(BASE_HOST_MULE)
                .setBasePath(BASE_PATH_MULE)
                .setPort(getPort())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("charset", "UTF-8")
                .build();

        // Generic response specification
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(STATUS_OK)
                .build();
        RestAssured.responseSpecification = responseSpecification;
    }

    public abstract int getPort();

//    @Parameters({"server_type"})
//    @Test
//    public void baseTestMethod(String serverType) {
//
//        System.out.println("Browser name in @Test is " + serverType);
//    }
}
