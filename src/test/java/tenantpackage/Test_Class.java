package tenantpackage;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

 
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "use_cases", 
         glue = {"tenantpackage"})

public class Test_Class {

}
 