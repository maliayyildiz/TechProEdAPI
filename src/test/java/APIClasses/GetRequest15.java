package APIClasses;

import io.restassured.response.Response;
import org.junit.Test;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest15 extends TestBase {
    //GSON
    @Test
    public void get01(){
        Response response = given().spec(spec02).when().get();
        response.prettyPrint();

        List<Map<String,Integer>> ListOfMaps = response.as(ArrayList.class);
        System.out.println(ListOfMaps);

    }
}
