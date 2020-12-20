package Tekrar;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import utilities.TestBase;

import static io.restassured.RestAssured.given;

public class Api8 extends TestBase {
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
    @Test
    public void api(){
        spec02.pathParam("id",5);
        Response response = given().spec(spec02).when().get("/{id}");
      //  response.prettyPrint();

//        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
//                body("firstname", equalTo("Sally"),"lastname", equalTo( "Ericsson")
//                        ,"totalprice",equalTo(111),
//                        "depositpaid", equalTo(false),
//                        "bookingdates.checkin", equalTo("2017-05-23"),
//                        "bookingdates.checkout", equalTo("2019-07-02"));


        //Assert the followings by using Soft Assertion

    }


}
