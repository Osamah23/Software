package owner;

import java.io.IOException;
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

    public void setName(String name) throws IOException {
        this.name = name;
        save();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IOException {
        this.email = email;
        save();
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws IOException {
    	this.phoneNumber = phoneNumber;
    	save();
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Phone Number: " + phoneNumber;
    }
    
    private void save() throws IOException {
		Skankom.getInstance().writeToFile();
	}
}
