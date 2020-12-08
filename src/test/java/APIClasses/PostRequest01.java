package APIClasses;

import io.cucumber.java.hu.Ha;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class PostRequest01 extends TestBase {
/*
					 For Post Request, you need;
					 1)Endpoint ==> Mandatory
					 2)Request Body ==> Mandatory
					 3)Authorization ==> It depends on the API
					 4)Headers ==> It depends on the API
					 */
    /*
			 When
			   I send POST Request to http://dummy.restapiexample.com/api/v1/create
			 Then
			   Status code is 200
			   Content Type is "application/json"
			   "status" key has value "success"
			   "message" key has value "Successfully! Record has been added."

			 Note: Create Request Body in 3 different ways
			*/

    @Test
    public void post01(){
        spec04.pathParam("create","create");
      //1st way
     //   String reqBody = "{\"name\":\"mali\",\"salary\":\"444\",\"age\":\"35\"}";

     //2nd way to create request body by using JSONObject Class ==> better way
//        JSONObject reqBody = new JSONObject();
//        reqBody.put("name", "Mali");
//        reqBody.put("salary", "400");
//        reqBody.put("age","35");

        //3rd way to create req body by using Map
        Map<String,String> reqBody = new HashMap<>();
        reqBody.put("name", "Mal1i");
        reqBody.put("salary", "600");
        reqBody.put("age","35");

        //For basic authorization after spec() use ==> auth().basic("admin", "password123")
        //For Bearer Token authorization after spec() use ==> auth().oauth2("Token will be typed over here")

        Response response = given().
                               spec(spec04).auth().basic("admin","password123").
                            body(reqBody).when().post("/{create}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("status", Matchers.equalTo("success"),
                        "message",Matchers.equalTo("Successfully! Record has been added.") );

        HashMap<String ,Object> map = response.as(HashMap.class);
        System.out.println(map);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(map.get("message"),"Successfully! Record has been added.");
        softAssert.assertEquals(map.get("status"),"success");

        softAssert.assertAll();


    }
}
