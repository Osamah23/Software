Feature: Provide residence location and information

  Scenario: User can provide the location and information of their private residence
    Given I am a registered as owner on Sakancom
    And I am logged in to my account
    When I navigate to the "Announce Residence" page
    And I fill in the residence location and relevant information
    And I submit the announcement
    Then the residence information should be saved on Sakancom
