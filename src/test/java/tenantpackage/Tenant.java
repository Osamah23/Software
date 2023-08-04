package tenantpackage;
import org.junit.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sakancomm.Enter1;

public class Tenant {
	boolean t=false;
	@When("user enter input <{string}>")
	public void user_enter_input(String string) {
	//	Enter1.tenant();
		t=Enter1.te(string);
	}

	@Then("user go to available housing page")
	public void user_go_to_available_housing_page() {
	  Assert.assertTrue(t);  
	}

	@Then("user go to booking page")
	public void user_go_to_booking_page() {
		  Assert.assertTrue(t);  

	}

	@Then("user go to sale page")
	public void user_go_to_sale_page() {
		  Assert.assertTrue(t);  

	}
}
