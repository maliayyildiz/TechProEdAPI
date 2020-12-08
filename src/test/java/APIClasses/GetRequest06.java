package APIClasses;


import static org.hamcrest.Matchers.*;
import org.junit.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest06 {

	 /*
		When
		    I send a GET request to REST API URL
			http://dummy.restapiexample.com/api/v1/employees
	        And accept type is “application/JSON”
	    Then
		    HTTP Status Code should be 200
		    And Response format should be "application/JSON"
		    And there should be 24 employees
		    And "Ashton Cox" should be one of the employees
		    And 21, 61, and 23 should be among the employee ages
    */

    @Test
    public void get01() {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("http://dummy.restapiexample.com/api/v1/employees");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"),
                        "data.employee_age", hasItems("21", "23", "61"));
    }

}
