Feature: Add contact information

  Scenario: User can provide contact information for the private residence announcement
    Given I am a registered user on Sakancom
    And I am logged in to my account
    When I navigate to the "Announce Residence" page
    And I provide my contact information (e.g., phone number, email)
    And I submit the announcement
    Then the contact information should be visible to potential tenants
