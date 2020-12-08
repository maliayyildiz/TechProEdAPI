package APIClasses;

import io.restassured.path.json.JsonPath;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetRequest13 {
    //How to work local Json data
    //C:\Users\YSMNI\Desktop\Employee.json
    @Test
    public void get01(){
        JsonPath  json = new JsonPath(new File("C:\\Users\\YSMNI\\Desktop\\Employee.json"));
        SoftAssert softAssert = new SoftAssert();

        //get all emp names whose salaries are grader than 150,000

        List<String> nameList = json.getList("data.findAll{Integer.valueOf(data.employee_salary)>150000}.employee_name");
        System.out.println(nameList);
    // asert that 16 employees are earning greater than 150 000
        List<Integer> salaryList = new ArrayList<>();
        for (String  w: nameList) {
            salaryList.add(Integer.valueOf(w));
        }
        softAssert.assertEquals(salaryList.size(),16);







        softAssert.assertAll();
    }
}
