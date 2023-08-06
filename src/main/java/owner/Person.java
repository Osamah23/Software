package owner;

import java.util.*;

import sakancomMain.Skankom;

public class Person {
    private String name;
    private String email;
    private String phoneNumber;

    public Person() {
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        save();
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    	save();
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Phone Number: " + phoneNumber;
    }
    
    private void save() {
		Skankom.getInstance().writeToFile();
	}
}
