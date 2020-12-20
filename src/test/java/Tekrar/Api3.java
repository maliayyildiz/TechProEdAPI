package Tekrar;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;


import static io.restassured.RestAssured.given;

public class Api3 extends TestBase {
    /*
		When I send a GET request to REST API URL
		https://restful-booker.herokuapp.com/booking/1
	    And Accept type is “application/JSON”
	    Then
	    HTTP Status Code should be 200
	    And Response format should be "application/JSON"
	    And firstname should be "Susan"
	    And lastname should be "Brown"
	    And checkin date should be "2015-02-16"
	    And checkout date should be "2017-06-20"
   */

    @Test
    public void api1() {
        spec02.pathParam("id", 1);
        Response response = given()
                //  .accept(ContentType.JSON)
                .spec(spec02).when().get("/{id}");
        response.prettyPrint();
//        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
//                .body("firstname", equalTo("Susan"),
//                        "lastname", equalTo("Brown"),
//                        "bookingdates.checkin", equalTo("2015-02-16"),
//                        "bookingdates.checkout", equalTo("2017-06-20"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.jsonPath().getString("firstname"), "Jim");
        softAssert.assertEquals(response.jsonPath().getString("lastname"), "Jones");
        softAssert.assertEquals(response.jsonPath().get("bookingdates.checkin"), "2020-02-02");
        softAssert.assertEquals(response.jsonPath().get("bookingdates.checkout"), "2020-10-09");
        softAssert.assertAll();
    }


}
