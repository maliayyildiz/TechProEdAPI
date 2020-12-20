package Tekrar;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Api9 extends TestBase {
   /*
	 When
	 	 I send GET Request to http://dummy.restapiexample.com/api/v1/employees
	 Then
		 Status code is 200
		 1)Print all ids greater than 10 on the console
		   Assert that there are 14 ids greater than 10
		 2)Print all ages less than 30 on the console
		   Assert that maximum age is 23
		 3)Print all employee names whose salaries are greater than 350000
		   Assert that Charde Marshall is one of the employees whose salary is greater than 350,000
		 4)Print all employee salaries whose ids are less than 11
		   Assert that maximum salary is 433060
	 */
    @Test
    public void api(){
        Response response = given().spec(spec03).when().get();
      //  response.prettyPrint();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        List<String> idList  = jsonPath.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
        System.out.println(idList);
        softAssert.assertEquals(idList.size(), 14);

        List<String> ageList = jsonPath.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
        System.out.println(ageList);

        List<Integer> ageListNew = new ArrayList<>();
        for (String w : ageList){
            ageListNew.add(Integer.valueOf(w));
        }
        System.out.println(ageListNew);

        Collections.sort(ageListNew);
        System.out.println(ageListNew);

        softAssert.assertTrue(ageListNew.get(ageListNew.size()-1)==23);

       List<String > nameList = jsonPath.getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
        System.out.println(nameList);
        softAssert.assertTrue(nameList.contains("Charde Marshall"));

       List<String> salaryList = jsonPath.getList("data.findAll{Integer.valueOf(it.id)<11}.employee_salary");
        System.out.println(salaryList);

        List<Integer> salaryNew = new ArrayList<>();
        for (String w: salaryList){
            salaryNew.add(Integer.valueOf(w));
        }

        Collections.sort(salaryNew);
        System.out.println(salaryNew);
     //   softAssert.assertEquals(salaryNew.get(salaryNew.size()-1), 433060);

        softAssert.assertAll();
    }
}
