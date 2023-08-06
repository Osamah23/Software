package owner;

import java.util.*;
import java.io.*;
import sakancomMain.Skankom;

public class Floor implements Serializable {
	private String id;
	private String housingId;
	private int floorNumber;
	private List<String> apartments;

	public Floor(String housingId, int floorNumber) {
		this.id = UUID.randomUUID().toString();
		this.housingId = housingId;
		this.floorNumber = floorNumber;
		this.apartments = new ArrayList<>();
	}
	
	public String getId() {
		return id;
	}

	public String getHousingId() {
		return housingId;
	}
	
	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
		save();
	}
	
	public void setHousingId(String housingId) {
		this.housingId = housingId;
		save();
	}
	
	public List<String> getApartments() {
		return apartments;
	}

	public void addApartment(Apartment apartment) {
		apartments.add(apartment.getId());
		save();
	}
	
	public void removeApartment(Apartment apartment) {
		apartments.remove(apartment.getId());
		save();
	}

	public void viewApartments() {
		System.out.println(toString());
		if (apartments.isEmpty()) {
			System.out.println("You haven't listed any apartments yet.");
		} else {
			System.out.println("Your listed apartments:");
			for (String apartmentId : apartments) {
				Apartment apartment = Skankom.getInstance().getApartment(apartmentId);
				if (apartment != null) {
					 apartment.viewApartmentInfo();
				}
			}
		}
	}
	
	@Override
    public String toString() {
        return "Floor Number: " + floorNumber;
    }
	
	private void save() {
		Skankom.getInstance().writeToFile();
	}
}
