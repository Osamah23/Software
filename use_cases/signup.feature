Feature: Sign Up
Background: Pre-registered Users

Given Registered users
      |  tasbeeh |   05944  | 0 |
      |  osamah  |   05999  | 1 |
      |   haya   |   22300  | 2 |
      
 Scenario: Pre-existing user registration
 Given This user has username <"osamahh"> , Password is <"0599"> and UserType is <"2"> 
 When he is registered before
 Then System show him it is already registered
 
Scenario: User registration does not exist before
 Given This user does not exist before has username <"MIRA"> , Password is <"1122"> and UserType is <"1">
 When He has not registered before
 Then The registration has been completed successfully
 