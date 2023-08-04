package sakancomm;

import java.util.List;

public class Housing {
    private String houseId;
    private String location;
    private double price;
    private String services;
    private String pictures;
    private String available;
    private String numberOfRooms;
    private String ownerName;
    private String ownerNumber;

    public Housing(String houseId, String location, double price, String services, String pictures, String available,
                   String numberOfRooms, String ownerName, String ownerNumber) {
        this.houseId = houseId;
        this.location = location;
        this.price = price;
        this.services = services;
        this.pictures = pictures;
        this.available = available;
        this.numberOfRooms = numberOfRooms;
        this.ownerName = ownerName;
        this.ownerNumber = ownerNumber;
    }

    public String getHouseId() {
        return houseId;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public String getServices() {
        return services;
    }

    public String getPictures() {
        return pictures;
    }

    public String getAvailable() {
        return available;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

	public void setAvailable(String string) {
        this.available = string;
		
	}
}
