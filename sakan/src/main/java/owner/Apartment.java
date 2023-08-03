package owner;

import java.util.ArrayList;
import java.util.List;

public class Apartment {
    private int numberOfBathrooms;
    private int numberOfBedrooms;
    private boolean hasBalcony;
    private List<Tenant> tenants;

    // Constructor
    public Apartment(int numberOfBathrooms, int numberOfBedrooms, boolean hasBalcony) {
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfBedrooms = numberOfBedrooms;
        this.hasBalcony = hasBalcony;
        this.tenants = new ArrayList<Tenant>();
    }

    // Getters and Setters for the attributes

    // ... (Getters and Setters for the attributes mentioned in the previous responses)
    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    // Setter for numberOfBathrooms
    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    // Getter for numberOfBedrooms
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    // Setter for numberOfBedrooms
    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    // Getter for hasBalcony
    public boolean hasBalcony() {
        return hasBalcony;
    }

    // Setter for hasBalcony
    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    // Other methods to add and manipulate tenants

    public void addTenant(Tenant tenant) {
        this.tenants.add(tenant);
    }

    // You can add other methods as needed to manipulate tenants and perform other operations related to the Apartment class.
}

