Feature: Add photos to the residence announcement

  Scenario: User can upload photos of their private residence
    Given I am a registered as owner on Sakancom
    And I am logged in to my account
    When want to create or edit exists residence 
    And I upload photos of my private residence
    Then the photos should be added to the residence announcement