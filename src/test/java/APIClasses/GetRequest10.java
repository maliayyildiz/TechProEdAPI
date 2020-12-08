package APIClasses;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.TestBase;

import static io.restassured.RestAssured.*;

public class GetRequest10 extends TestBase {

	/*
	 When
		 I send a GET request to REST API URL
		 https://restful-booker.herokuapp.com/booking/5
	 Then
		 HTTP Status Code should be 200
		 And response content type is “application/JSON”
		 And response body should be like;
		 {
		  "firstname": "Sally",
		  "lastname": "Ericsson",
		  "totalprice": 111,
		  "depositpaid": false,
		  "bookingdates": { "checkin": "2017-05-23",
		                    "checkout":"2019-07-02" }
	     }
	*/

	/*
	 JSONPATH: JsonPath is used to navigate in Json Data
	*/

    @Test
    public void get01() {

        spec02.pathParam("bookingid", 5);
        Response response = given().spec(spec02).when().get("/{bookingid}");
        response.prettyPrint();

        //Assert the followings by using Soft Assertion
        JsonPath json = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        //Print the firstname on the console
        System.out.println(json.getString("firstname"));
        softAssert.assertEquals(json.getString("firstname"), "Susan");

        //Print the lastname on the console
        System.out.println(json.getString("lastname"));
        softAssert.assertEquals(json.getString("lastname"), "Wilson");

        //Print the totalprice on the console
        System.out.println(json.getInt("totalprice"));
        softAssert.assertEquals(json.getInt("totalprice"),817);

        //Print the depositpaid on the console
        System.out.println(json.getBoolean("depositpaid"));
        softAssert.assertEquals(json.getBoolean("depositpaid"), true);

        //Print the checkin on the console
        System.out.println(json.getString("bookingdates.checkin"));
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2018-03-01");

        //Print the checkout on the console
        System.out.println(json.getString("bookingdates.checkout"));
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2020-07-20");

        //Print the additionalneeds on the console
        System.out.println(json.getString( "additionalneeds"));
        softAssert.assertEquals(json.getString("additionalneeds"), "Breakfast");

        softAssert.assertAll();
    }

}
