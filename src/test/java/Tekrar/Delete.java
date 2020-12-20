package Tekrar;

import io.restassured.response.Response;
import org.junit.Test;
import utilities.TestBase;


import static io.restassured.RestAssured.given;

public class Delete extends TestBase {
     /*
	 For DELETE Request we need just Endpoint like GET Request, we do not need Request Body
	*/

    @Test
    public void api(){
        Response response = given().spec(spec01).when().get("198");
        response.prettyPrint();

        Response response1 = given().spec(spec01).when().delete("198");
        response1.prettyPrint();
    }
}
