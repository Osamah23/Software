package tenantpackage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class booking {

@Given("a list of houses with their state.")
public void a_list_of_houses_with_their_state(io.cucumber.datatable.DataTable dataTable) {
  
	
	
	
	
	
	
}

@Given("there is a house with ID {string} available for booking")
public void there_is_a_house_with_id_available_for_booking(String string) {
   
}

@Given("the house is located in {string}")
public void the_house_is_located_in(String string) {
  
}

@Given("the house has a price of {string} per month")
public void the_house_has_a_price_of_per_month(String string) {
   
}

@When("the user books the house with ID {string} for the next month")
public void the_user_books_the_house_with_id_for_the_next_month(String string) {
   
}

@Then("the system should confirm the booking with a success message")
public void the_system_should_confirm_the_booking_with_a_success_message() {
 
}

@Then("the house status should be updated as {string}")
public void the_house_status_should_be_updated_as(String string) {
   
}

@Given("there is a house with ID {string} unavailable for booking")
public void there_is_a_house_with_id_unavailable_for_booking(String string) {
   
}

@When("the user tries to book the house with ID {string}")
public void the_user_tries_to_book_the_house_with_id(String string) {
    
}

@Then("the system should display an error message indicating the house is not available for booking")
public void the_system_should_display_an_error_message_indicating_the_house_is_not_available_for_booking() {
    
}

@When("the user tries to book the house with ID {string} for a past date")
public void the_user_tries_to_book_the_house_with_id_for_a_past_date(String string) {
  
}

@Then("the system should display an error message indicating the booking date is invalid")
public void the_system_should_display_an_error_message_indicating_the_booking_date_is_invalid() {
   
}

@Given("the user account balance is {string}")
public void the_user_account_balance_is(String string) {
   
}

@Then("the system should display an error message indicating insufficient funds")
public void the_system_should_display_an_error_message_indicating_insufficient_funds() {
   
}

}
