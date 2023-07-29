Feature: Book House
Background: 
   Given a list of houses with their state.
    |123|A|Small sakan  |1000| Wifi    |
    |789|B|Medium sakan |800 | parking |
    |101|C|student sakan|1200| gym     | 

  Scenario: Successful Booking
    Given there is a house with ID "123" available for booking
    And the house is located in "nablus"
    And the house has a price of "$1000" per month
    When the user books the house with ID "123" for the next month
    Then the system should confirm the booking with a success message
    And the house status should be updated as "Booked"

  Scenario: Booking Unavailable House
    Given there is a house with ID "456" unavailable for booking
    When the user tries to book the house with ID "456"
    Then the system should display an error message indicating the house is not available for booking

  Scenario: Booking with Invalid Dates
    Given there is a house with ID "789" available for booking
    And the house is located in "Ramallah"
    And the house has a price of "$800" per month
    When the user tries to book the house with ID "789" for a past date
    Then the system should display an error message indicating the booking date is invalid

  Scenario: Booking without Sufficient Funds
    Given there is a house with ID "101" available for booking
    And the house is located in "Nablus"
    And the house has a price of "$1200" per month
    And the user account balance is "$800"
    When the user tries to book the house with ID "101"
    Then the system should display an error message indicating insufficient funds
