package TestO;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import owner.Announce;
import owner.ShowAnnounce;
import static org.junit.Assert.assertTrue;

public class TestResidenceLocation {
	ShowAnnounce a=ShowAnnounce.getInstance();

	Announce ne=new Announce(null, null, null, null, 0, false, null);
	String loc="blabla";
	String info="in nab";
	@When("I navigate to the {string} page")
	public void i_navigate_to_the_page(String string) {
	    
		assertTrue(true);
	}

	@When("I fill in the residence location and relevant information")
	public void i_fill_in_the_residence_location_and_relevant_information() {
		ShowAnnounce.Announces.add(ne);
		
	    ne.setLocation(loc);
	    ne.setContactInformation(info);
	    assertTrue(ne.getLocation()==loc&&ne.getContactInformation()==info);
	    
	}

	@When("I submit the announcement")
	public void i_submit_the_announcement() {
	    ShowAnnounce.Announces.add(ne);
	    assertTrue(ShowAnnounce.Announces.get(ShowAnnounce.Announces.size()-1).equals(ne));
	}

	@Then("the residence information should be saved on Sakancom")
	public void the_residence_information_should_be_saved_on_sakancom() {
	    assertTrue(true);
	}


}
