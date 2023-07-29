Feature: Sign Up
Background: Pre-registered Users

Given Registered users
      |  tasbeeh |   05944  | t  |
      |  osamah  |   05999  | o  |
      |   haya   |   22300  | a  |
      
 Scenario: Pre-existing user registration
 Given This user has username "osamah" , Password is "05999" and UserType is "o" 
 When he is registered before
 Then System show him it is already registered
 
Scenario: User registration does not exist before
 Given This user does not exist before has username <"username"> , Password is <"Password"> and UserType is <"UserType">
 When He has not registered before
 Then The registration has been completed successfully
 