package owner;

import java.util.*;
import java.io.*;
import sakancomMain.*;

public class User implements Serializable {
	private String userId = null;
	private String userName;
	private String password;
	private UserType userType;
	
	public User(String userName, String password, UserType userType) {
		System.out.println("test user");
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }
	
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
		save();
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
		save();
	}
	
	public void setPassword(String password) {
		this.password = password;
		save();
	}
	
	public void setUserType(UserType userType) {
		this.userType = userType;
		save();
	}
	
	private void save() {
		Skankom.getInstance().writeToFile();
	}
}

