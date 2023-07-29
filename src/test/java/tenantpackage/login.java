package tenantpackage;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sakancomMain.User;

public class login {

	boolean check ;
	boolean check2 ;
	boolean check3 ;

	
	@When("user1 enter correct username <{string}> and user enter correct Password <{string}>")
	public void user1_enter_correct_username_and_user_enter_correct_password(String string, String string2) {
	    
		 check = User.fun(string, string2);
	}
	@Then("user1 go to tenant page")
	public void user1_go_to_tenant_page() {
	    assertTrue(check && User.t);

	}

	@When("user2 enter correct username <{string}>  and user enter correct Password <{string}>")
	public void user2_enter_correct_username_and_user_enter_correct_password(String string, String string2) {
		   check2 = User.fun(string, string2);
  
	}

	@Then("user2 go to owner page")
	public void user2_go_to_owner_page() {
		assertTrue(check2 && User.o); 
	}

	@When("user3 enter correct username <{string}>  and user enter correct Password <{string}>")
	public void user3_enter_correct_username_and_user_enter_correct_password(String string, String string2) {
		check3 = User.fun(string, string2);   
	}

	@Then("user3 go to admin page")
	public void user3_go_to_admin_page() {
		assertTrue(check3 && User.d);    
	}

}
