Feature: Provide monthly rent details

  Scenario: User can provide the monthly rent and its inclusions for the private residence
    Given I am a registered user on Sakancom
    And I am logged in to my account
    When I navigate to the "Announce Residence" page
    And I provide the monthly rent amount and specify if it includes electricity and water
    And I submit the announcement
    Then the rent details should be displayed in the residence announcement
