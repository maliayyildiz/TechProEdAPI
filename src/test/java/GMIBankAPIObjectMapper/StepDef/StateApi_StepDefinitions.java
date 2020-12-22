package GMIBankAPIObjectMapper.StepDef;

import GMIBankAPIObjectMapper.Pojos.States;
import GMIBankAPIObjectMapper.gmiUtil.ConfigReader;
import GMIBankAPIObjectMapper.gmiUtil.ReadTxt;
import GMIBankAPIObjectMapper.gmiUtil.WriteToTxt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StateApi_StepDefinitions {
    Response response;
    States[] states;
    String filePath = "AllState.txt";
    @Given("user sets all states to response using {string}")
    public void user_sets_all_states_to_response_using(String url) {
        response = given().headers(
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
      //  response.prettyPrint();
    }

    @Given("user manipulates all states")
    public void user_manipulates_all_states() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        states = obj.readValue(response.asString(),States[].class);
    //    System.out.println(states[6].getName());

    }

    @Given("user saves the states to correspondent files")
    public void user_saves_the_states_to_correspondent_files() {
      //  WriteToTxt.saveAllStates(filePath,states);


    }

    @Then("user validate all states")
    public void user_validate_all_states() {
       List<States> list = ReadTxt.returnAllStates(filePath);
       String expected = "ohio";
       // System.out.println(list.get(0).getName());
        Assert.assertEquals(expected, list.get(10).getName());

    }
}
