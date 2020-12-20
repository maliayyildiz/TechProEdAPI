package Tekrar;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Api1 {

     Response response;
        /*
	 Positive Scenario
	 when I send a GET Request to https://restful-booker.herokuapp.com/booking
	 and I accept type "application/json"  ==> Means API is accepting data just in Json Format
	 then status code should be 200
	 and content type should be "application/json" ==> Response body must be in Json format
	 */

      @Test
    public void api1(){
      response = given().accept(ContentType.JSON).when().get("https://restful-booker.herokuapp.com/booking");
      response.prettyPrint();

      response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

      }

       /*
     Positive Scenario
     when I send a GET Request to https://restful-booker.herokuapp.com/booking/5
     then status code should be 200
     and content type should be "application/json"
    */
    @Test
    public void api2(){
        response = given().when().get("https://restful-booker.herokuapp.com/booking/5");

        response.then().assertThat().statusCode(200).contentType("application/json");
    response.prettyPrint();
    }

        /*
	 Negative Scenario
	 when I send a GET Request to https://restful-booker.herokuapp.com/booking/1001
	 then status code should be 404
	 and Response Body contains "Not Found"
	 and Response Body does not contain "Suleyman"
	*/

    @Test
    public  void api3(){
        response = given().when().get("https://restful-booker.herokuapp.com/booking/1001");
        response.prettyPrint();
        response.then().assertThat().statusCode(404);
        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("Suleyman"));
    }



}
