package owner;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import sakancommain.*;

import java.io.*;

public class Tenant extends Person implements Serializable {
	private String id;
	private String universityMajor;
	private int age;
	private String bookedHousing;
	private ArrayList<String> listedFurniture;
	private ArrayList<String> reservedHousingIds;
	private String timeToPay;

	public Tenant(String name, String email, String phoneNumber, String universityMajor, Integer age) throws IOException {
    	this.id = UUID.randomUUID().toString();
		this.universityMajor = universityMajor;
		this.age = age;
		this.bookedHousing = null;
		this.listedFurniture = new ArrayList<>();
		this.reservedHousingIds = new ArrayList<>();
		this.timeToPay = null;
		super.setName(name);
		super.setEmail(email);
		super.setPhoneNumber(phoneNumber);
	}
	
	public String getId() {
		return id;
	}

	public String getUniversityMajor() {
		return universityMajor;
	}
	
	public String getTimeToPay() {
		return timeToPay;
	}

	public void setTimeToPay(String timeToPay) throws IOException {
		this.timeToPay = timeToPay;
		save1();
	}
	
	public void setUniversityMajor(String universityMajor) throws IOException {
		this.universityMajor = universityMajor;
		save1();
	}

	public int getAge() {
		return age;
	}

	public void setAge(Integer age) throws IOException {
		this.age = age;
		save1();
	}

	public void bookHousing(Housing housing) throws IOException {
		if (housing == null) { return; }
		bookedHousing = housing.getId();
		save1();
	}

	public void addFurnitureForSale(Furniture furniture) throws IOException {
		listedFurniture.add(furniture.getId());
		save1();
	}
	public void removebBookedHousing() throws IOException {
		bookedHousing = null;
		save1();
	}
	
	public List<String> getListedFurniture() {
		return listedFurniture;
	}

	public void removeListedFurniture(Furniture furniture) throws IOException {
		listedFurniture.remove(furniture.getId());
		save1();
	}

	public void addReservedHousing(Housing housing) throws IOException {
		reservedHousingIds.add(housing.getId());
		save1();
	}

	public void removeReservedHousing(String housingId) throws IOException {
		reservedHousingIds.remove(housingId);
		save1();
	}

	public String getBookedHousing() {
		return bookedHousing;
	}

	public List<String> getReservedHousing() {
		return reservedHousingIds;
	}
	private static final Logger LOGGER = Logger.getLogger(Tenant.class.getName());
	public void viewBookedHousing(){
		if (bookedHousing == null) {
			String b="You haven't booked any housing yet.";
			LOGGER.log(Level.INFO,b);
		} else {
			Housing housing = Skankom.getInstance().getHousing(bookedHousing);
			if (housing != null) {
				String a="Your booked housing:\n"+housing.getLocation();
				LOGGER.log(Level.INFO,a);
				
			}
		}
	}

	public void viewListedFurniture(){
		if (listedFurniture.isEmpty()) {
			String k="You haven't listed any furniture for sale.";
			LOGGER.log(Level.INFO,k);
		} else {
			LOGGER.log(Level.INFO,"Your listed furniture for sale:");
			for (String furnitureId : listedFurniture) {
				Furniture furniture = Skankom.getInstance().getFurniture(furnitureId);
				if (furniture != null) {
					String c=furniture.getName() + " - Price: " + furniture.getPrice();
					LOGGER.log(Level.INFO,c);
					
				}
			}
		}
	}

	public String getTenantInfo() {
		return "Age: " + age + ", University Major: " + universityMajor;
	}

	@Override
	public String toString() {
		return "Tenant Name: " + super.getName() + ", " + getTenantInfo() + ", " + super.toString();
	}
	
	private void save1() throws IOException {
		Skankom.getInstance().writeToFile();
	}
}
