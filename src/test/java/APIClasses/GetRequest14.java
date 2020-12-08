package APIClasses;

import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;


import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestBase {

    /*
    GSOn is a converter
    GSON is used to convert Json Format Data to Java Objects --> De-Serialization
    GSON is used to convert Java object to Json Data Format ==> Serialization
     */

    @Test
    public void get01(){
        Response response = given().spec(spec01).when().get("/2");
        response.prettyPrint();

        //lets convert Json Data to hashmap
        HashMap<String,Object> hMap = response.as(HashMap.class);
        System.out.println(hMap);

        //print all keys
        System.out.println(hMap.keySet());
        //pint all values
        System.out.println(hMap.values());
        //assert that completed is false
        Assert.assertEquals(false, hMap.get("completed"));

        Assert.assertEquals(hMap.get("title"),"quis ut nam facilis et officia qui");

        Assert.assertEquals(hMap.get("userId"),1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("quis ut nam facilis et officia qui",hMap.get("title"));
        softAssert.assertTrue(hMap.get("userId").equals(1));
        softAssert.assertTrue(hMap.get("completed").equals(false));
    }





}
