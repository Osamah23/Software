Feature: sign in page
  I want to sign in sakncom
  
Scenario: tenant login
 When user1 enter correct username <"tasbeehh"> and user1 enter correct Password <"0444">
 Then user1 go to tenant page

 Scenario: owner login
 When user2 enter correct username <"osamahh">  and user2 enter correct Password <"0599">
 Then user2 go to owner page
 
  Scenario: admin login
 When user3 enter correct username <"haya">  and user3 enter correct Password <"22300">
 Then user3 go to admin page
 
 Scenario: failed tenant login
 When user1 enter wrong username <"shahd"> and user1 enter wrong Password <"0544">
 Then login failed
 
 Scenario:  failed owner login
 When user2 enter wrong username <"shahd"> and user2 enter wrong Password <"0544">
 Then login failed
 
 Scenario: failed admin login
 When user3 enter wrong username <"shahd"> and user3 enter wrong Password <"0544">
 Then login failed
