package owner;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Announce {
	    @SuppressWarnings("unused")
		private String id;
	    private ArrayList <Image> photos=new ArrayList<Image>();
	    private String location;
	    private String residenceInfo;
	    private String services;
	    private double monthlyRent;
	    private boolean inclusiveOfElectricityWater;
	    private String contactInformation;
	    private String ownerName;
	    private int numberOfTenants=-1;
	    private int numberOfFloors=-1;
	    

	    // Constructor
	    public Announce( ArrayList <Image> photos, String location, String residenceInfo,
	                    String services, double monthlyRent, boolean inclusiveOfElectricityWater,
	                    String contactInformation,int numOfTenant,int numOfFloors) {
	    	this.id=Integer.toString((int)Math.random()*Integer.MAX_VALUE);
	        this.photos = photos;
	        this.location = location;
	        this.residenceInfo = residenceInfo;
	        this.services = services;
	        this.monthlyRent = monthlyRent;
	        this.inclusiveOfElectricityWater = inclusiveOfElectricityWater;
	        this.contactInformation = contactInformation;
	        this.ownerName=User.getName();
	        this.numberOfTenants=numOfTenant;
	        this.numberOfFloors=numOfFloors;
	    }
	    public Announce( ArrayList <Image> photos, String location, String residenceInfo,
                String services, double monthlyRent, boolean inclusiveOfElectricityWater,
                String contactInformation) {
	this.id=Integer.toString((int)Math.random()*Integer.MAX_VALUE);
    this.photos = photos;
    this.location = location;
    this.residenceInfo = residenceInfo;
    this.services = services;
    this.monthlyRent = monthlyRent;
    this.inclusiveOfElectricityWater = inclusiveOfElectricityWater;
    this.contactInformation = contactInformation;
    this.ownerName=User.getName();
}

	    // Getters and Setters for the attributes

	    public ArrayList <Image> getPhotos() {
	        return photos;
	    }

	    public void setPhotos(ArrayList <Image>photos) {
	        this.photos = photos;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    public String getResidenceInfo() {
	        return residenceInfo;
	    }

	    public void setResidenceInfo(String residenceInfo) {
	        this.residenceInfo = residenceInfo;
	    }

	    public String getServices() {
	        return services;
	    }

	    public void setServices(String services) {
	        this.services = services;
	    }

	    public double getMonthlyRent() {
	        return monthlyRent;
	    }

	    public void setMonthlyRent(double monthlyRent) {
	        this.monthlyRent = monthlyRent;
	    }

	    public boolean isInclusiveOfElectricityWater() {
	        return inclusiveOfElectricityWater;
	    }

	    public void setInclusiveOfElectricityWater(boolean inclusiveOfElectricityWater) {
	        this.inclusiveOfElectricityWater = inclusiveOfElectricityWater;
	    }

	    public String getContactInformation() {
	        return contactInformation;
	    }

	    public void setContactInformation(String contactInformation) {
	        this.contactInformation = contactInformation;
	    }
	    public String getOwnerName() {
	    	return ownerName;
	    }
	    public int getNumberOfTenants() {
	        return numberOfTenants;
	    }

	    // Setter for numberOfTenants
	    public void setNumberOfTenants(int numberOfTenants) {
	        this.numberOfTenants = numberOfTenants;
	    }

	    // Getter for numberOfFloors
	    public int getNumberOfFloors() {
	        return numberOfFloors;
	    }

	    // Setter for numberOfFloors
	    public void setNumberOfFloors(int numberOfFloors) {
	        this.numberOfFloors = numberOfFloors;
	    }
	}



