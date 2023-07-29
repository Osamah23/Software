package tenantpackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sakancomMain.SignUpService;
import sakancomMain.UserType;

public class SignUpServiceTest {
	private SignUpService signUpService;
    private String username;
    private String password;
    private UserType userType;
    private String registrationResult;
@Given("Registered users")
public void registered_users(io.cucumber.datatable.DataTable dataTable) {
    
}

@Given("This user has username {string} , Password is {string} and UserType is {string}")
public void this_user_has_username_password_is_and_user_type_is(String username, String password, String usertype) {
	this.username = username;
    this.password = password;
  //  this.userType = UserType.valueOf(userType);
}

@When("he is registered before")
public void he_is_registered_before() {
  
}

@Then("System show him it is already registered")
public void system_show_him_it_is_already_registered() {
    
}

@Given("This user does not exist before has username <{string}> , Password is <{string}> and UserType is <{string}>")
public void this_user_does_not_exist_before_has_username_password_is_and_user_type_is(String string, String string2, String string3) {
  
}

@When("He has not registered before")
public void he_has_not_registered_before() {
   
}

@Then("The registration has been completed successfully")
public void the_registration_has_been_completed_successfully() {

}
}
