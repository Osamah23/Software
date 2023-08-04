package tenantpackage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sakancomm.User1;

public class login {
	boolean k =false;
	boolean check2=false ;
	boolean check3=false ;
	private boolean k2=false;
	private boolean ch2=false;
	private boolean ch3=false;

	@When("user1 enter correct username <{string}> and user1 enter correct Password <{string}>")
	public void user1_enter_correct_username_and_user1_enter_correct_password(String string, String string2) {
	    k = User1.fun(string, string2);
	   
	}

	@When("user2 enter correct username <{string}>  and user2 enter correct Password <{string}>")
	public void user2_enter_correct_username_and_user2_enter_correct_password(String string, String string2) {
		   check2 = User1.fun(string, string2);
		 
		    }

	@When("user3 enter correct username <{string}>  and user3 enter correct Password <{string}>")
	public void user3_enter_correct_username_and_user3_enter_correct_password(String string, String string2) {
		check3 = User1.fun(string, string2);   
		  
	}

	@When("user1 enter wrong username <{string}> and user1 enter wrong Password <{string}>")
	public void user1_enter_wrong_username_and_user1_enter_wrong_password(String string, String string2) {
	    k2 = User1.fun(string, string2);

	}

	@When("user2 enter wrong username <{string}> and user2 enter wrong Password <{string}>")
	public void user2_enter_wrong_username_and_user2_enter_wrong_password(String string, String string2) {
	    ch2 = User1.fun(string, string2);

	}

	@When("user3 enter wrong username <{string}> and user3 enter wrong Password <{string}>")
	public void user3_enter_wrong_username_and_user3_enter_wrong_password(String string, String string2) {
	    ch3 = User1.fun(string, string2);
 
	}

@Then("user1 go to tenant page")
public void user1_go_to_tenant_page() {
    assertTrue(k && User1.t);

}
@Then("user2 go to owner page")
	public void user2_go_to_owner_page() {
		assertTrue(check2 && User1.o); 
	}
@Then("user3 go to admin page")
	public void user3_go_to_admin_page() {
		assertTrue(check3 && User1.d);    
	}
	
	@Then("login failed")
	public void login_failed() {
		assertFalse(k2||ch2||ch3);    

	}

}
