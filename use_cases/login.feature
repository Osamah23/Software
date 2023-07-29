Feature: sign in page
  I want to sign in sakncom
  
Scenario: tenant login
 When user1 enter correct username <"tasbeeh"> and user enter correct Password <"05944">
 Then user1 go to tenant page

 Scenario: owner login
 When user2 enter correct username <"osamah">  and user enter correct Password <"05999">
 Then user2 go to owner page
 
  Scenario: admin login
 When user3 enter correct username <"haya">  and user enter correct Password <"22300">
 Then user3 go to admin page