package owner;

import java.util.*;

import sakancomMain.Skankom;

import java.io.*;

public class Furniture implements Serializable {
	private String id;
    private String name;
    private double price;
    private String ownerId;

    public Furniture(String name, double price, String ownerId) {
    	this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.ownerId = ownerId;
    }
    
    public String getId() {
    	return id;
    }

    public String getName() {
        return name;
    }
    
    public String getOwnerId() {
    	return ownerId;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        save();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        save();
    }

    @Override
    public String toString() {
        return "Furniture: " + name + ", Price: " + price;
    }
    
    private void save() {
		Skankom.getInstance().writeToFile();
	}
}
