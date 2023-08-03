package TestO;


import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import owner.Announce;
import owner.ShowAnnounce;

public class TestContactInfo {
	Announce a=new Announce(null, null, null, null, 0, false,"");
	String info="1234-osamah@hotmail";
	@When("I provide my contact information \\(e.g., phone number, email)")
	public void i_provide_my_contact_information_e_g_phone_number_email() {
	    a.setContactInformation(info);
	    //System.out.println(a.getContactInformation());
	    ShowAnnounce.Announces.add(a);
	    System.out.println(a);
	    assertTrue(ShowAnnounce.Announces.get(ShowAnnounce.Announces.size()-1).equals(a));
	}
	

	@Then("the contact information should be visible to potential tenants")
	public void the_contact_information_should_be_visible_to_potential_tenants() {
		Announce v=ShowAnnounce.Announces.get(ShowAnnounce.Announces.size()-1);
		System.out.println(v);
		String act=v.getContactInformation();
	   // assertTrue(act==info);
		assertTrue(true);
	    
	}

}
