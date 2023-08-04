package tenantpackage;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sakancomm.HousingDataProvider;

public class HousesSteps {
    private List<Map<String, String>> housingData;
    private HousingDataProvider housingDataProvider;
boolean ch=false;
boolean ch1=false;

    @Given("details housing")
    public void detaials_housing(io.cucumber.datatable.DataTable dataTable) {
        housingData = dataTable.asMaps(String.class, String.class);
    }

   
    @When("the user enter HouseID {string}")
    public void the_user_enter_house_id(String string) {
        housingDataProvider = new HousingDataProvider();
     // HousingDataProvider.housedetails(string);
        ch=HousingDataProvider.displayHouseDetails(string);
    }
    @When("the user enter {string}")
    public void the_user_enter(String input) {
      //  HousingDataProvider.backing(input);
      ch1=  HousingDataProvider.backing(input);
    }

    @Then("the system should display the details of it")
    public void thenSystemShouldDisplayDetailsOfChosenHouse() {
        assertTrue(ch);
    }

    @Then("the system should back to tenant page")
    public void thenSystemShouldBackToTenantPage() {
        assertTrue(ch1);
    }
}
