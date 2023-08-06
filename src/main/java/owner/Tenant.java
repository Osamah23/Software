package owner;

import java.util.*;
import java.io.*;
import sakancomMain.*;

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
	
	public ArrayList<String> getListedFurniture() {
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

	public ArrayList<String> getReservedHousing() {
		return reservedHousingIds;
	}

	public void viewBookedHousing() throws IOException {
		if (bookedHousing == null) {
			System.out.println("You haven't booked any housing yet.");
		} else {
			Housing housing = Skankom.getInstance().getHousing(bookedHousing);
			if (housing != null) {
				System.out.println("Your booked housing:");
				System.out.println(housing.getLocation());
			}
		}
	}

	public void viewListedFurniture() throws IOException {
		if (listedFurniture.isEmpty()) {
			System.out.println("You haven't listed any furniture for sale.");
		} else {
			System.out.println("Your listed furniture for sale:");
			for (String furnitureId : listedFurniture) {
				Furniture furniture = Skankom.getInstance().getFurniture(furnitureId);
				if (furniture != null) {
					System.out.println(furniture.getName() + " - Price: " + furniture.getPrice());
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
