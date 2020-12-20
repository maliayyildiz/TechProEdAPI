package Tekrar;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import utilities.TestBase;


import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Put extends TestBase {
    /*
			 For PUT Request(Full Update) we need;
			 1)Endpoint ==> Mandatory
			 2)Request Body ==> Mandatory
			 3)Authorization ==> It depends on the API
			 4)Headers ==> It depends on the API
	 */
    /*
		 When
		   I send PUT Request to http://dummy.restapiexample.com/api/v1/update/2
		 Then
		   Status code is 200
		   Content Type is "application/json"
		   "status" key has value "success"
		   "message" key has value "Successfully! Record has been updated."

		 Note: Create Request Body in 3 different ways
	*/
    @Test
    public void api(){
        spec04.pathParams("function","update", "id", 2);

        Map<String ,String> reqBody = new HashMap<>();
        reqBody.put("name","Naim");
        reqBody.put("salary","2000");
        reqBody.put("age","3");
        Response response  = given().spec(spec04).body(reqBody).when().put("/{function}/{id}");
        response.prettyPrint();

        response.then().assertThat().body("status", Matchers.equalTo("success"),
                "message", Matchers.equalTo("Successfully! Record has been updated."));
        //softassertion with de-serialization

    }

}
