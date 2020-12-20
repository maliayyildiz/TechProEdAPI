package APIClasses;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostRequest02 extends TestBase {
    /*                Warm Up (10 Minutes)
				 When
				   I send POST Request to https://restful-booker.herokuapp.com/booking ==> spec02
				   with the request body
						{
						    "firstname": "Ali",
						    "lastname": "Can",
						    "totalprice": 111,
						    "depositpaid": true,
						    "bookingdates": {
						        "checkin": "2020-09-16",
						        "checkout": "2020-09-18"
						    },
						    "additionalneeds": "Wifi"
					    }
				 Then
				   Status code is 200
				   Content Type is "application/json"
				   Assert all response body details

				 Note: Create Request Body by using Map
				*/
    @Test
    public void post01() {

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("firstname", "Veli");
        reqBody.put("lastname", "Mark");
        reqBody.put("totalprice", 111);
        reqBody.put("depositpaid", true);
        Map<String,String> datesMap = new HashMap<>();
        datesMap.put("checkin", "2020-09-16");
        datesMap.put("checkout", "2020-09-18");
        reqBody.put("bookingdates", datesMap);
        reqBody.put("additionalneeds", "Wifi");

        Response response = given().
                contentType(ContentType.JSON).
                auth().
                basic("admin", "password123").
                spec(spec02).
                body(reqBody).
                when().
                post();
        response.prettyPrint();

        //Hard Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("booking.firstname", equalTo(reqBody.get("firstname")),
                        "booking.lastname", equalTo(reqBody.get("lastname")),
                        "booking.totalprice", equalTo(reqBody.get("totalprice")),
                        "booking.depositpaid", equalTo(reqBody.get("depositpaid")),
                        "booking.bookingdates",equalTo(reqBody.get("bookingdates")),
                        //If you want to assert checkin and checkout one by one do the following two lines
                        "booking.bookingdates.checkin", equalTo(datesMap.get("checkin")),
                        "booking.bookingdates.checkout", equalTo(datesMap.get("checkout")),
                        "booking.additionalneeds", equalTo(reqBody.get("additionalneeds")));
        //The data which you send to API is Expected Data
        //The data which you get from API is Actual Data

        //Soft Assertion: Use De-Serialization(GSON) + Soft Assert
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualData.get("booking").toString().contains(reqBody.get("firstname").toString()));
        softAssert.assertTrue(actualData.get("booking").toString().contains(reqBody.get("lastname").toString()));
        softAssert.assertTrue(actualData.get("booking").toString().contains(reqBody.get("totalprice").toString()));
        softAssert.assertTrue(actualData.get("booking").toString().contains(reqBody.get("depositpaid").toString()));
        softAssert.assertTrue(actualData.get("booking").toString().contains(reqBody.get("bookingdates").toString()));
        softAssert.assertTrue(actualData.get("booking").toString().contains(datesMap.get("checkin").toString()));
        softAssert.assertTrue(actualData.get("booking").toString().contains(datesMap.get("checkout").toString()));
        softAssert.assertTrue(actualData.get("booking").toString().contains(reqBody.get("additionalneeds").toString()));
        softAssert.assertAll();
    }
}
