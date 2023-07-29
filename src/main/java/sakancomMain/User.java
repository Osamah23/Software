package sakancomMain;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User<userName> {
 private String userName;
 private String password;
 private String userType; 

 public User(String userName, String password, String userType) {
     this.userName = userName;
     this.password = password;
     this.userType = userType;
 }

 public User(String string, String string2) {
	// TODO Auto-generated constructor stub
}

public String getUserName() {
     return userName;
 }

 public void setUserName(String userName) {
     this.userName = userName;
 }

 public String getPassword() {
     return password;
 }

 public void setPassword(String password) { 
     this.password = password;
 }

 public String getUserType() {
     return userType;
 }

 public void setUserType(String userType) {
     this.userType = userType;
 }

 // Override toString method for better printing of user information
 @Override
 public String toString() {
     return "User{" +
             "userName='" + userName + '\'' +
             ", password='" + password + '\'' +
             ", userType='" + userType + '\'' +
             '}';
 }
public static boolean t=false; 
public static boolean o=false; 
public static boolean d=false;

public static boolean fun(String name , String pass) {
	
     ArrayList<User> userList = new ArrayList<>();
     userList.add(new User("tasbeeh", "05944", "tenant"));
     userList.add(new User("shahad", "789", "tenant"));
     userList.add(new User("haya", "22300", "admin"));
     userList.add(new User("osamah", "05999", "owner"));

     for (User user : userList) {
        if(name.equals(user.getUserName()) && pass.equals(user.getPassword())) {
        	if(user.getUserType().equals("tenant")) {
        	t=true;	
        	
        	return true ;
           }
        	else if(user.getUserType().equals("owner")) {
            	o=true;	
            	
            	return true ;
            }
        	else if(user.getUserType().equals("admin")) {
            	d=true;	
            	
            	return true ;
            }
     }
   
 }
     return false;
}


}


