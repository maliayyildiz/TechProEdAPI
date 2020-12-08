package APIClasses;

import org.hamcrest.Matchers;
import org.junit.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.TestBase;

import static io.restassured.RestAssured.*;

public class GetRequest08 extends TestBase {

	/*
		     				Warm Up (13 minutes)
		1) Create a class and name it as GetRequest08
		2) When
		     I send a GET Request to https://jsonplaceholder.typicode.com/todos
		   Then
			 HTTP Status code should be "200"
			 And Content type should be in "JSON" format
			 And there should be 200 "title"
			 And "dignissimos quo nobis earum saepe" should be one of the "title"s
			 And 111, 121, and 131 should be among the "id"s
			 And 4th title is "et porro tempora"
			 And last title is "ipsam aperiam voluptates qui"
    */

    @Test
    public void get01() {
        Response response = given().
                spec(spec01).
                when().
                get();
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", Matchers.hasSize(200),
                        "title", Matchers.hasItem("dignissimos quo nobis earum saepe"),
                        "id",Matchers.hasItems(111, 121, 131),
                        "title[3]", Matchers.equalTo("et porro tempora"),
                        "title[-1]", Matchers.equalTo("ipsam aperiam voluptates qui"));//title[199] works as well
    }
}
