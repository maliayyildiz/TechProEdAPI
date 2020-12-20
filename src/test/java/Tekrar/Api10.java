package Tekrar;



import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.TestBase;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Api10 extends TestBase {
     /*
    GSON is a converter
    GSON is used to convert Json Format Data to Java Objects --> De-Serialization
    GSON is used to convert Java object to Json Data Format ==> Serialization
     */
     @Test
    public void api(){
      Response response = given().spec(spec01).when().get("/2");
     // response.prettyPrint();

      HashMap<String, Object> hashMap = response.as(HashMap.class);
         System.out.println(hashMap);

         System.out.println(hashMap.keySet());
         System.out.println(hashMap.values());

         Assert.assertEquals("quis ut nam facilis et officia qui",hashMap.get("title"));
         Assert.assertEquals(1,hashMap.get("userId"));
      //softassertion


     }

}
