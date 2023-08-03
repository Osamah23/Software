package TestO;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import owner.Announce;
import owner.ShowMyAnnounce;

public class TestOwnerTenant {
	int choose=0;
	Announce b=ShowMyAnnounce.getTarget(choose);
	@When("I navigate to my Dashboard Owner Control Panel")
	public void i_navigate_to_my_dashboard_owner_control_panel() {
	   assertTrue(true);
	}

	@Then("I should see a list of my announced private residences")
	public void i_should_see_a_list_of_my_announced_private_residences() {
	    ShowMyAnnounce a=ShowMyAnnounce.getInstance();
	    assertTrue(a!=null);
	}

	@When("I choose one of my private residences from the control panel")
	public void i_choose_one_of_my_private_residences_from_the_control_panel() {
	   
	   assertTrue(b!=null);
	}

	@Then("the number of tenants should appear for that residence")
	public void the_number_of_tenants_should_appear_for_that_residence() {
		b.setNumberOfTenants(0);
	   int x=b.getNumberOfTenants();
	   assertTrue(x>=0);
	}

	@When("I choose a specific private residence from the control panel")
	public void i_choose_a_specific_private_residence_from_the_control_panel() {
		assertTrue(b!=null);
	}

	@When("I select the option to view the floors")
	public void i_select_the_option_to_view_the_floors() {
	    b.setNumberOfFloors(choose);
	    assertTrue(b.getNumberOfFloors()>=0);
	}

	@Then("the number of floors for that residence should appear")
	public void the_number_of_floors_for_that_residence_should_appear() {
	   assertTrue(true);
	}

	@When("I choose a specific floor of the residence from the control panel")
	public void i_choose_a_specific_floor_of_the_residence_from_the_control_panel() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the apartments on that floor should appear for me")
	public void the_apartments_on_that_floor_should_appear_for_me() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I choose a specific apartment from the control panel")
	public void i_choose_a_specific_apartment_from_the_control_panel() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the names of the tenants and their means of communication should appear")
	public void the_names_of_the_tenants_and_their_means_of_communication_should_appear() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the number of bathrooms, bedrooms, and if it has a balcony should be displayed")
	public void the_number_of_bathrooms_bedrooms_and_if_it_has_a_balcony_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
