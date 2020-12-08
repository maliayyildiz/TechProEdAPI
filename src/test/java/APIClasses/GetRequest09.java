package APIClasses;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import io.restassured.response.Response;
import utilities.TestBase;


public class GetRequest09 extends TestBase {

	/*
	 Among the data there should be someone whose first name is Jim
	 URL: https://restful-booker.herokuapp.com/booking
	*/

	/*
	 Query param is used to filter the result
	 Syntax ==> ?key=value&key=value
	 1.Way:You can type query param inside the get() method parenthesis (Not Recommended)
	 2.Way:You can use spec02.queryParam("firstname", "Jim"); (Use it for single query param)
	 3.Way:You can use spec02.queryParams("firstname", "Jim", "totalprice", 2); (Use it for multiple query params)
	*/

    @Test
    public void get01() {

        //spec02.queryParam("firstname", "Jim");
        //spec02.queryParam("totalprice", 2);
        spec02.queryParams("firstname", "Jim", //To remove repetation use "queryParams()" method
                "totalprice", 2);

        Response response = given().
                spec(spec02).
                when().
                get();
        response.prettyPrint();

        //Assert that the data whose first name is Jim exists among the data
        assertTrue(response.asString().contains("bookingid"));

    }

}