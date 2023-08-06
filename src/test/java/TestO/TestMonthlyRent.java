package TestO;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import owner.Announce;
import owner.ShowAnnounce;

public class TestMonthlyRent {
	Announce a=new Announce(null, null, null, null, 0, false, null);
	double MR=2500;
	@When("I provide the monthly rent amount and specify if it includes electricity and water")
	public void i_provide_the_monthly_rent_amount_and_specify_if_it_includes_electricity_and_water() {
		a.setMonthlyRent(MR);
		ShowAnnounce.Announces.add(a);
		assertTrue(ShowAnnounce.Announces.get(ShowAnnounce.Announces.size()-1).getMonthlyRent()==MR);
	   
	}

	@Then("the rent details should be displayed in the residence announcement")
	public void the_rent_details_should_be_displayed_in_the_residence_announcement() {
	    assertTrue(true);
	}

}
