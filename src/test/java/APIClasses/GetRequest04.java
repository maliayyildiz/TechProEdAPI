package APIClasses;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest04 {

	/*
	 1)How to get all Headers data
	 2)How to get a specific Header
	 3)How to assert Headers one by one
	 */

    @Test
    public void get01() {
        Response response = given().when().get("https://restful-booker.herokuapp.com/booking");
        response.prettyPrint();

        //How to get all Headers data
        System.out.println(response.getHeaders());

        //Assert if Headers contain "Expect-CT"
        System.out.println(response.getHeader("Expect-CT"));//null
        assertEquals(response.getHeader("Expect-CT"),null);

        //How to get a specific Header
        System.out.println(response.getHeader("Server"));//Cowboy
        //Assert that Via header has the value "1.1 vegur"
        assertEquals(response.getHeader("Via"),"1.1 vegur");

    }

}
