package GMIBankAPIObjectMapper.StepDef;

import GMIBankAPIObjectMapper.Pojos.Customer;
import GMIBankAPIObjectMapper.gmiUtil.ConfigReader;
import GMIBankAPIObjectMapper.gmiUtil.ReadTxt;
import GMIBankAPIObjectMapper.gmiUtil.WriteToTxt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;

import io.restassured.response.Response;


import java.util.List;

import static io.restassured.RestAssured.given;

public class CustomerApi_StepDefinitions {

    Response response;
    Customer[] customers;
    @Given("user provides the api endpoint to set the response using {string}")
    public void user_provides_the_api_endpoint_to_set_the_response_using(String url) {
        response =  given().headers(
                "Authorization",
                "Bearer " + ConfigReader.getProperty("bearer_token"),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .when()
                .get(url)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

     //   response.prettyPrint();
    }

    @Given("manipulate all customers' data")
    public void manipulate_all_customers_data() throws Exception {

        ObjectMapper obj = new ObjectMapper();
        customers = obj.readValue(response.asString(), Customer[].class);
        //  System.out.println(customers[2].getFirstName());


    }

    @Given("user sets the data in correspondent file")
    public void user_sets_the_data_in_correspondent_file() {
        WriteToTxt.saveDataInFile("NewFile.txt" , customers);

    }

    @Then("user validate date")
    public void user_validate_date() {
        List<Customer> list = ReadTxt.returnCustomerSSN("NewFile.txt");
        Customer customer = new Customer();
        customer.setSsn("123-47-2476");
        int count = 0 ;

        String expected = "111-10-1000";

        int expectedNumberOfSSN = 0;
        for (int i = 0; i<list.size(); i++){
           if (list.get(i).getSsn().equals(expected)){
               System.out.println("Found item "+ expectedNumberOfSSN);
           }
        }
    }
}
