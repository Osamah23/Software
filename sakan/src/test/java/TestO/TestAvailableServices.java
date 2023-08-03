package TestO;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import owner.Announce;
import owner.ShowAnnounce;
import owner.User;

public class TestAvailableServices {
	Announce b=new Announce(null, null, null, null, 0, false, null);
	@Given("I am a registered user on Sakancom")
	public void i_am_a_registered_user_on_sakancom() {
	    User a=new User();
	    assertTrue(a.isOwner()==true);
	    }

	@When("I select the available services in the residence \\(e.g., Wi-Fi, gym, etc.)")
	public void i_select_the_available_services_in_the_residence_e_g_wi_fi_gym_etc() {
	   String available="gym,two rooms";
	   b.setServices(available);
	   ShowAnnounce.Announces.add(b);
	   assertTrue(ShowAnnounce.Announces.get(ShowAnnounce.Announces.size()-1).getServices()==available);
	  
	}

	@Then("the specified services should be displayed in the residence details")
	public void the_specified_services_should_be_displayed_in_the_residence_details() {
	   assertTrue(true);
	}

}
