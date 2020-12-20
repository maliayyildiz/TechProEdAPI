package Tekrar;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.TestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Patch extends TestBase {
    /*
		 For PATCH Request(Partial Update) you need;
		 1)Endpoint ==> Mandatory
		 2)Request Body ==> Mandatory
		 3)Authorization ==> It depends on the API
		 4)Header ==> It depends on the API
	 */

	/*
						 {
						    "userId": 10,
						    "id": 198,
						    "title": "quis eius est sint explicabo",
						    "completed": true
					     }
	 */
    @Test
    public void api(){
        spec02.pathParam("id", 8);



        Map<String ,Object> reqBody = new HashMap<>();
        reqBody.put("firstname", "Team13");

        Response response = given().spec(spec02).when().patch("/{id}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

    }
}
