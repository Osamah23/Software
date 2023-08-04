Feature: View Housing Details

Scenario: Display details of a chosen house
  Given details housing
    | HouseID |
    | A       |
    | B       |
    | C       |
  When the user enter HouseID "B"
  Then the system should display the details of it

Scenario: Back to tenant page
 
  When the user enter "0"
  Then the system should back to tenant page
