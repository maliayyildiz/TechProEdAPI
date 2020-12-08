package APIClasses;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import sun.applet.resources.MsgAppletViewer;
import utilities.TestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends TestBase {
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
    public void put01(){
        spec04.pathParams("function", "update","id",2);

     //   use map for req body
        Map<String,String > reqBody = new HashMap<>();
        reqBody.put("name", "Naim");
        reqBody.put("salary", "60");
        reqBody.put("age","36");
        Response response = given().spec(spec04).body(reqBody).when().put("/{function}/{id}");
        response.prettyPrint();

        response.then().assertThat().body("status", Matchers.equalTo("success"),
                "message",Matchers.equalTo("Successfully! Record has been updated."));

        SoftAssert softAssert = new SoftAssert();
        HashMap<String,Object> map = response.as(HashMap.class);
        softAssert.assertEquals(map.get("status"),"success");
        softAssert.assertEquals(map.get("message"), "Successfully! Record has been updated.");


        softAssert.assertAll();

    }


}
