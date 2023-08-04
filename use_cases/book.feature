Feature: Book Accommodation

Background:
Given Registered users as tenant
| Username | Password | UserType |
| tasbeeh  | 05944    | 1        |

And houses
| House ID | Location | Price | Services                       | Pictures                              | available |
| A        | Albathan | 1000  | Wi-Fi, Parking, Gym            | https://example.com/house1/pic1       | yes       |
| B        | Beita    | 1000  | Washer/Dryer, Balcony, Pool    | https://example.com/house2/pic1       | yes       |
| C        | Nablus   | 200   | Parking, Gym, Pet-friendly     | https://example.com/house3/pic1       | no        |

Scenario: User selects a non-existent accommodation for booking
Given i login as UserType <"1">
  When I select the accommodation with House ID "D"
  Then the booking failed
  
Scenario: User books an available accommodation
Given i login as UserType <"1">

  When I select the accommodation with House ID "A" and available is "yes"
#  And the control panel should appear
And booking succeed

  Scenario: User tries to book a non-available existing accommodation
  Given i login as UserType <"1">
  When I select the accommodation with House ID "C"  and available is1 "no"
  Then booking failed