package tenantpackage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sakancomm.HousingDataProvider;
import sakancomm.User1;

public class BookingSteps {
	boolean ten=false;
	boolean ch =false;
	HousingDataProvider provider = new HousingDataProvider(); // Create an instance
    List<List<String>> availableHousingUnits = provider.getAvailableHousingUnits();

    HousingDataProvider housingDataProvider = new HousingDataProvider();

	@Given("Registered users as tenant")
	public void registered_users_as_tenant(io.cucumber.datatable.DataTable dataTable) {
		 List<Map<String, String>> usersData = dataTable.asMaps(String.class, String.class);
	        for (Map<String, String> user : usersData) {
	            String username = user.get("Username");
	            String password = user.get("Password");
	            String userType = user.get("UserType");
	            User1 userObj = new User1(username, password, userType);
	        }
	}

	@Given("houses")
	public void houses(io.cucumber.datatable.DataTable dataTable) {
	    List<Map<String, String>> housingData = dataTable.asMaps(String.class, String.class);
        HousingDataProvider.setAvailableHousingUnits(housingData); 
	}
	@Given("i login as UserType <{string}>")
	public void i_login_as_user_type(String string) {
      ten = User1.t;
	}
	@When("I select the accommodation with House ID {string}")
	public void i_select_the_accommodation_with_house_id(String string) {
	  HousingDataProvider.bookHouse(string, null); 
	  
	}

	@Then("the booking failed")
	public void the_booking_failed() {
	  assertFalse(HousingDataProvider.book());
	}

	@When("I select the accommodation with House ID {string} and available is {string}")
	public void i_select_the_accommodation_with_house_id_and_available_is(String string, String string2) {
		  HousingDataProvider.bookHouse(string,string2); 
		
	}
	@Then("booking succeed")
	public void booking_succeed() {

		assertTrue( HousingDataProvider.book());

	}

	@When("I select the accommodation with House ID {string}  and available is1 {string}")
	public void i_select_the_accommodation_with_house_id_and_available_is1(String string, String string2) {
		  HousingDataProvider.bookHouse(string, string2); 

	}

	@Then("booking failed")
	public void booking_failed() {
		  assertFalse(HousingDataProvider.book());
  
	}
}
