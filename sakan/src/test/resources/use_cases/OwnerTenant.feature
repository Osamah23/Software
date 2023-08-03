Feature: dashboard of owner 

Scenario: Dashboard owner control panel
    When I navigate to my Dashboard Owner Control Panel
    Then I should see a list of my announced private residences

  Scenario: View tenant details
    When I choose one of my private residences from the control panel
    Then the number of tenants should appear for that residence

  Scenario: View residence floors
    When I choose a specific private residence from the control panel
    And I select the option to view the floors
    Then the number of floors for that residence should appear

  Scenario: View apartments on a specific floor
    When I choose a specific floor of the residence from the control panel
    Then the apartments on that floor should appear for me

  Scenario: View tenant details for a specific apartment
    When I choose a specific apartment from the control panel
    Then the names of the tenants and their means of communication should appear
    And the number of bathrooms, bedrooms, and if it has a balcony should be displayed