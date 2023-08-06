package owner;

import java.util.*;

import sakancomMain.Skankom;

import java.io.*;

public class Admin implements Serializable {
	private String id;
    private String ownerId;
    private String name;

    public Admin(String ownerId, String name) {
    	this.id = UUID.randomUUID().toString();
    	this.ownerId = ownerId;
        this.name = name;
    }
    
    public String getId() {
    	return id;
    }
    
    public String getOwnerId() {
    	return ownerId;
    }
    
    public String getName() {
        return name;
    }

    public void setOwnerId(String ownerId) {
    	this.ownerId = ownerId;
    	save();
    }
    
    public void setName(String name) {
        this.name = name;
        save();
    }

    @Override
    public String toString() {
        return "Admin Name: " + name;
    }
    
    private void save() {
		Skankom.getInstance().writeToFile();
	}
}
