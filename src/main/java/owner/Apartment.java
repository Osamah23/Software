package owner;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import sakancomMain.Skankom;

public class Apartment implements Serializable {
	private String id;
	private String floorId;
	private int numberOfBathrooms;
	private int numberOfBedrooms;
	private boolean hasBalcony;
	private List<String> tenants;
	private static final Logger LOGGER = Logger.getLogger(Apartment.class.getName());

	public Apartment(int numberOfBathrooms, int numberOfBedrooms, boolean hasBalcony) {
		this.id = UUID.randomUUID().toString();
		this.numberOfBathrooms = numberOfBathrooms;
		this.numberOfBedrooms = numberOfBedrooms;
		this.hasBalcony = hasBalcony;
		this.tenants = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public String getFloorId() {
		return floorId;	
	}

	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) throws IOException {
		this.numberOfBathrooms = numberOfBathrooms;
		save();
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) throws IOException {
		this.numberOfBedrooms = numberOfBedrooms;
		save();
	}

	public boolean hasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(boolean hasBalcony) throws IOException {
		this.hasBalcony = hasBalcony;
		save();
	}

	public List<String> getTenants() {
		return tenants;
	}

	public void addTenant(String tenant) throws IOException {
		this.tenants.add(tenant);
		save();
	}
	
	public void setFloorId(String floorId) throws IOException {
		this.floorId = floorId;
		save();
	}

	public void removeTenant(String tenant) throws IOException {
		this.tenants.remove(tenant);
		save();
	}

	public void viewApartmentInfo() {
		System.out.println(toString());
		if (tenants.isEmpty()) {
			LOGGER.log(Level.INFO,"There is no tenants yet.");
		} else {
			LOGGER.log(Level.INFO,"The listed tenants in the apartment:");
			for (String tenantId : tenants) {
				Tenant tenant = Skankom.getInstance().getTenant(tenantId);
				if (tenant != null) {
					String c=" * " + tenant.toString();
					LOGGER.log(Level.INFO,c);
				}
			}
		}
	}
	
	@Override
    public String toString() {
        return "Number of Bathrooms: " + numberOfBathrooms + ", Number of Bedrooms: " + numberOfBedrooms + (hasBalcony ? ", with Balcony." : ", without Balcony.");
    }
	
	private void save() throws IOException {
		Skankom.getInstance().writeToFile();
	}
}

