package owner;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;

import sakancomMain.PhotoManager;
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
	private ArrayList<String> photos;

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

	public List<BufferedImage> getPhotos() {
		ArrayList<BufferedImage> photosArray = new ArrayList<BufferedImage>();
		for (String url: photos) {
			try {
				BufferedImage source=ImageIO.read(new FileInputStream((url)));
				photosArray.add(source);
			} 
			 catch (IOException e) {
				 LOGGER.log(Level.INFO, "error in getting photos");
			} 
		}
		return photosArray;
	}
	
	public int getPhotosCount() {
		return photos.size();
	}

	public void addPhoto(String photo) throws IOException {
		this.photos.add(photo);
		save();
	}
	
	public void removePhoto(int photoIndex) throws IOException {
		if (photoIndex >= photos.size()) { return; }
		this.photos.remove(photoIndex);
		save();
	}

	public List<String> getServices() {
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
	private static final Logger LOGGER = Logger.getLogger(Announcement.class.getName());
	public void viewServices() {
		if (services.isEmpty()) {
			
			LOGGER.log(Level.INFO, "No services to show");
			return;	
		}
		LOGGER.log(Level.INFO,"Available srevices: ");
		for (String service: services) {
			String a="* " + service;
			LOGGER.log(Level.INFO, a);
			
		}
	}

	public void viewPhotos() {
		PhotoManager.showPhotos(this);
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


