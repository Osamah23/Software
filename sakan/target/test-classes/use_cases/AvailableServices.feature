Feature: Specify available services in the residence

  Scenario: User can select available services in the private residence
    Given I am a registered user on Sakancom
    And I am logged in to my account
    When I navigate to the "Announce Residence" page
    And I select the available services in the residence (e.g., Wi-Fi, gym, etc.)
    And I submit the announcement
    Then the specified services should be displayed in the residence details
