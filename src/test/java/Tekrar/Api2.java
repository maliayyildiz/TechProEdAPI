package Tekrar;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;

public class Api2 {

    /*
		 	                    Warm Up (10 minutes)
		 1) Create a class and name it as GetRequest03
		 2) When I send a GET Request to https://restful-booker.herokuapp.com/booking/5
		    Then
		    HTTP Status code should be "200"
		    And  Content type should be in "JSON" format
		    And  Status Line should be "HTTP/1.1 200 OK"
		    And  response body does not contain "Not Found"
		    And  response body contains "bookingdates"
	*/
    @Test
    public void api1(){
        Response response = given().when().get(" https://restful-booker.herokuapp.com/booking/5");
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");
        System.out.println(response.statusLine());
        System.out.println(response.contentType());
        System.out.println(response.statusCode());
        System.out.println(response.getHeaders());
        Assert.assertFalse(response.asString().contains("Not Found"));
        Assert.assertTrue(response.asString().contains("bookingdates"));
    }

}
