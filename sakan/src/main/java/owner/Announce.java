package owner;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Announce {
	    @SuppressWarnings("unused")
		private String id;
	    private ArrayList <BufferedImage> photos;
	    private String location;
	    private String residenceInfo;
	    private String services;
	    private double monthlyRent;
	    private boolean inclusiveOfElectricityWater;
	    private String contactInformation;

	    // Constructor
	    public Announce( ArrayList <BufferedImage> photos, String location, String residenceInfo,
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
	    }

	    // Getters and Setters for the attributes

	    public ArrayList <BufferedImage> getPhotos() {
	        return photos;
	    }

	    public void setPhotos(ArrayList <BufferedImage>photos) {
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
	}



