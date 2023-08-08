package sakancommain;
import owner.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Skankom implements Serializable {
	private static Skankom instance;
	private Map<String, Housing> housings;
	private Map<String, Furniture> furnitures;
	private Map<String, Apartment> apartments;
	private Map<String, Owner> owners;
	private Map<String, Tenant> tenants;
	private Map<String, Floor> floors;
	private Map<String, User> users;
	private Map<String, Admin> admins;
	private Map<String, Announcement> announcements;
	private Map<String, AnnouncementStatus> announcementsStatus;

	private Skankom() {
		housings = new HashMap<>();
		furnitures = new HashMap<>();
		apartments = new HashMap<>();
		owners = new HashMap<>();
		tenants = new HashMap<>();
		users = new HashMap<>();
		admins = new HashMap<>();
		announcements = new HashMap<>();
		announcementsStatus = new HashMap<>();
		floors = new HashMap<>();
	}
	private static final Logger LOGGER = Logger.getLogger(Skankom.class.getName());

	public static synchronized Skankom getInstance(){
		if (instance == null) {
			try {
				instance = Skankom.readFromFile();
			} catch (IOException e) {
				LOGGER.log(Level.INFO, "error in read from file");
			}
		}
		return instance;
	}
	
	public void save() throws IOException {
		writeToFile();
	}
	
	public void writeToFile() throws IOException {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try {
			fileOut= new FileOutputStream("instance.ser");
			try {
            out = new ObjectOutputStream(fileOut);
            out.writeObject(getInstance());
            out.close();
            fileOut.close();
        } 
			finally {
				if(out!=null) {
				out.close();
				}
			}
		}
	
		catch (Exception e) {
			LOGGER.log(Level.INFO, "error in write to file function!");
        }
		finally {
			if(fileOut!=null) { 
			fileOut.close();
			}
		  }
	}
	
	public static synchronized Skankom readFromFile() throws IOException {
		Skankom deserializedInstance = null;
		FileInputStream fileIn = null;
		ObjectInputStream in = null;

		try {
			 fileIn = new FileInputStream("instance.ser");
			 try {
			 in = new ObjectInputStream(fileIn);
			deserializedInstance = (Skankom) in.readObject();
			in.close();
			fileIn.close();
		}
			 finally {
					if(in!=null) {
					in.close();
					}
					}	 
		}
		catch (Exception e) {
			LOGGER.log(Level.INFO, "error in read from file function!");
		}
		finally {
			if(fileIn!=null) { 
			fileIn.close();
			}
			if(in!=null) {
			in.close();
			}// Multiple streams were opened. Only the last is closed.
		  }
		

		if (deserializedInstance != null) {
			return deserializedInstance;
		}
		return new Skankom();
	}

	public Map<String, Housing> getHousings() {
		return housings;
	}

	public Map<String, Announcement> getAnnouncements() {
		return announcements;
	}

	public Map<String, AnnouncementStatus> getAnnouncementsStatus() {
		return announcementsStatus;
	}

	public Map<String, Furniture> getFurnitures() {
		return furnitures;
	}

	public Map<String, Apartment> getApartments() {
		return apartments;
	}

	public Map<String, Owner> getOwners() {
		return owners;
	}

	public Map<String, Tenant> getTenants() {
		return tenants;
	}

	public Map<String, Floor> getFloors() {
		return floors;
	}

	public Map<String, Admin> getAdmins() {
		return admins;
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void addHousing(Housing housing) throws IOException {
		if (housing == null) { return; }
		housings.put(housing.getId(), housing);
		save();
	}

	public void removeHousing(Housing housing) throws IOException {
		if (housing == null) { return; }
		for (String floorId: housing.getFloors()) {
			Floor floor = getFloor(floorId);
			removeFloor(floor);
		}
		housings.remove(housing.getId());
		save();
	}

	public void removeApartment(Apartment apartment) throws IOException {
		if (apartment == null) { return; }
		apartments.remove(apartment.getId());
		save();
	}

	public void removeFloor(Floor floor) throws IOException {
		if (floor == null) { return; }
		for (String apartmentId: floor.getApartments()) {
			Apartment apartment = getApartment(apartmentId);
			removeApartment(apartment);
		}
		floors.remove(floor.getId());
		save();
	}

	public void addFurniture(Furniture furniture) throws IOException {
		if (furniture == null) { return; }
		furnitures.put(furniture.getId(), furniture);
		save();
	}
	
	public void removeFurniture(Furniture furniture) throws IOException {
		if (furniture == null) { return; }
		furnitures.remove(furniture.getId());
		save();
	}

	public void addApartment(Apartment apartment) throws IOException {
		if (apartment == null) { return; }
		apartments.put(apartment.getId(), apartment);
		save();
	}

	public void addOwner(Owner owner) throws IOException {
		if (owner == null) { return; }
		owners.put(owner.getId(), owner);
		save();
	}

	public void addTenant(Tenant tenant) throws IOException {
		if (tenant == null) { return; }
		tenants.put(tenant.getId(), tenant);
		save();
	}

	public void addUser(User user) throws IOException {
		if (user == null) { return; }
		users.put(user.getUserName(), user);
		save();
	}

	public void addAnnouncement(Announcement announcement) throws IOException {
		if (announcement == null) { return; }
		announcements.put(announcement.getId(), announcement);
		addAnnouncementStatus(announcement , AnnouncementStatus.PENDING);
		save();
	}

	public void removeAnnouncement(Announcement announcement) throws IOException {
		if (announcement == null) { return; }
		announcements.remove(announcement.getId());
		removeAnnouncementStatus(announcement);
		save();
	}

	public void removeAnnouncementStatus(Announcement announcement) throws IOException {
		if (announcement == null) { return; }
		announcementsStatus.remove(announcement.getId());
		save();
	}

	public List<Announcement> getAnnouncements(Owner owner) {
		Collection<Announcement> collection = getAnnouncements().values();
		Announcement[] announcements1 = collection.toArray(new Announcement[0]);
		ArrayList<Announcement> announs = new ArrayList<>();
		for (Announcement announcement: announcements1) {
			if (announcement.getOwner().equals(owner.getId())) {
				announs.add(announcement);
			}
		}
		return announs;
	}

	public List<Housing> getHousings(Owner owner) {
		Collection<Housing> collection = getHousings().values();
		Housing[] housings = collection.toArray(new Housing[0]);
		ArrayList<Housing> houses = new ArrayList<>();
		for (Housing housing: housings) {
			if (housing.getOwnerId().equals(owner.getId())) {
				houses.add(housing);
			}
		}
		return houses;
	}

	public ArrayList<Announcement> getAcceptedAnnouncements() {
		ArrayList<Announcement> announcements = new ArrayList<>();
		for (String key: announcementsStatus.keySet()) {
			AnnouncementStatus status = announcementsStatus.get(key);
			if (status == AnnouncementStatus.ACCEPTED) {
				Announcement announcement = getAnnouncement(key);
				if (announcement != null) {
					announcements.add(announcement);
				}
			}
		}
		return announcements;
	}

	public void addAnnouncementStatus(Announcement announcement, AnnouncementStatus status) throws IOException {
		if (announcement == null) { return; }
		announcementsStatus.put(announcement.getId(), status);
		save();
	}

	public void addAdmin(Admin admin) throws IOException {
		if (admin == null) { return; }
		admins.put(admin.getId(), admin);
		save();
	}

	public void addFloor(Floor floor) throws IOException {
		if (floor == null) { return; }
		floors.put(floor.getId(), floor);
		save();
	}

	public Housing getHousing(String id) {
		return housings.get(id);
	}

	public Furniture getFurniture(String id) {
		return furnitures.get(id);
	}

	public Apartment getApartment(String id) {
		return apartments.get(id);
	}

	public Tenant getTenant(String id) {
		return tenants.get(id);
	}

	public Floor getFloor(String id) {
		return floors.get(id);
	}

	public Owner getOwner(String id) {
		return owners.get(id);
	}

	public Admin getAdmin(String id) {
		return admins.get(id);
	}

	public User getUser(String id) {
		return users.get(id);
	}

	public Announcement getAnnouncement(String id) {
		return announcements.get(id);
	}

	public AnnouncementStatus getAnnouncementStatus(String id) {
		return announcementsStatus.get(id);
	}
}
