package tenantpackage;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sakancomm.SignUpSer;
public class SignUpServiceTest {
boolean ch=false;
	 boolean SignUpflag = false;
	String n;
	String Pass;
	String UT;	
	@Given("Registered users")
	public void registered_users(io.cucumber.datatable.DataTable dataTable) {
	   
	        for (Map<String, String> row : dataTable.asMaps()) {
	            String username = row.get("Username");
	            String password = row.get("Password");
	            String userType = row.get("UserType");
	            SignUpSer.signUp(username, password, userType);
	        }
	}

	@Given("This user has username <{string}> , Password is <{string}> and UserType is <{string}>")
	public void this_user_has_username_password_is_and_user_type_is(String string, String string2, String string3) {
	   n=string;
	   Pass=string2;
	   UT=string3;
	}

	@When("he is registered before")
	public void he_is_registered_before() {
 
		SignUpSer SignUpSer = new SignUpSer();
        boolean isRegistered = SignUpSer.isUserRegistered(n);
        Assertions.assertTrue(isRegistered, "The user is already registered.");
        boolean signUpResult = SignUpSer.signUp(n, Pass, UT);
        SignUpflag=signUpResult;
        Assertions.assertFalse(SignUpflag, "Registration failed.");
   
	}

	@Then("System show him it is already registered")
	public void system_show_him_it_is_already_registered() {
		assertFalse(SignUpflag); 
	}
	@Given("This user does not exist before has username <{string}> , Password is <{string}> and UserType is <{string}>")
	public void this_user_does_not_exist_before_has_username_password_is_and_user_type_is(String string, String string2, String string3) {
		   n=string;
		   Pass=string2;
		   UT=string3;   
	}
@When("He has not registered before")
	public void he_has_not_registered_before() {
		SignUpSer SignUpSer = new SignUpSer();
        boolean isRegistered = SignUpSer.isUserRegistered(n);
        Assertions.assertFalse(isRegistered, "The user is already registered.");
        boolean signUpResult = SignUpSer.signUp(n, Pass, UT);
        SignUpflag=signUpResult;
        Assertions.assertTrue(SignUpflag, "Registration failed.");
   
	}

	@Then("The registration has been completed successfully")
	public void the_registration_has_been_completed_successfully() {
		assertTrue(SignUpflag);
	}
}

