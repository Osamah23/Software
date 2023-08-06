package owner;

import java.util.*;
import java.awt.*;
import java.io.*;
import sakancomMain.Skankom;

public class Announcement implements Serializable {
	private String id;
	private String title;
	private String description;
	private String ownerId;
	private String housingId;
	private double price;
	private boolean includesElectricityAndWater;
	private ArrayList<String> services;
	private ArrayList<Image> photos;

	public Announcement(String title,
			String description, 
			double price, 
			boolean includesElectricityAndWater, 
			String ownerId, 
			String housingId) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.description = description;
		this.price = price;
		this.includesElectricityAndWater = includesElectricityAndWater;
		this.ownerId = ownerId;
		this.housingId = housingId;
		this.photos = new ArrayList<>();
		this.services = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws IOException {
		this.price = price;
		save();
	}

	public ArrayList<Image> getPhotos() {
		return photos;
	}

	public void addPhotos(Image photo) throws IOException {
		this.photos.add(photo);
		save();
	}

	public ArrayList<String> getServices() {
		return services;
	}

	public void addService(String service) throws IOException {
		this.services.add(service);
		save();
	}

	public void removeService(String service) throws IOException {
		this.services.remove(service);
		save();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws IOException {
		this.title = title;
		save();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws IOException {
		this.description = description;
		save();
	}

	public String getOwner() {
		return ownerId;
	}

	public void setOwner(Owner owner) throws IOException {
		this.ownerId = owner.getId();
		save();
	}

	public String getHousing() {
		return housingId;
	}

	public void setHousing(Housing housing) throws IOException {
		this.housingId = housing.getId();
		save();
	}

	public void viewServices() {
		if (services.isEmpty()) {
			System.out.println("No services to show");
			return;	
		}
		System.out.println("Available srevices: ");
		for (String service: services) {
			System.out.println("* " + service);
		}
	}

	public void viewPhotos() {
		// FIXME
	}

	@Override
	public String toString() {
		Owner owner = null;
		owner = Skankom.getInstance().getOwner(ownerId);
		Housing housing = null;
		
			housing = Skankom.getInstance().getHousing(housingId);
		if (owner != null && housing != null) {
			return "Announcement: " + title + "\nDescription: " + description
					+ "\nOwner: " + owner.getName() + "\nLocation: " + housing.getLocation()
					+ "\nPrice: " + getPrice() + " ("+ (includesElectricityAndWater ? "includes" : "excludes") 
					+ " electricity and water)";
		}
		return "Announcement: " + title + "\nDescription: " + description;
	}
	
	private void save() throws IOException {
		Skankom.getInstance().writeToFile();
	}
}


