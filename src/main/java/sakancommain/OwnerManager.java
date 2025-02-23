package sakancommain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;


public class OwnerManager {
	private static Skankom skankom = Skankom.getInstance();
	private OwnerManager() {}
	private static final Logger LOGGER = Logger.getLogger(OwnerManager.class.getName());

	public static void createOwner(User user) throws IOException {
		LOGGER.log(Level.INFO,"Enter Owner Name:");
		String name = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter Email:");
		String email = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter Phone Number:");
		String phoneNumber = SakancomApp.getScanner().nextLine();
		Owner owner = new Owner( email, phoneNumber);
		owner.setName(name);
		user.setUserId(owner.getId());
		skankom.addOwner(owner);
		skankom.addUser(user);
		String k=owner.toString() + " is created.";
		LOGGER.log(Level.INFO,k);
		enterAsOwner(owner);
	}
	
	public static void enterAsOwner(Owner owner) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Show Announecemnts \n2) Show Reservations \n3) Add Announcement \n4) Show Housings \n5) Add Housing \n6) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			AnnouncementManager.showAnnouncements(owner);
			break;
		case 2:
			ReservationManager.showReservations(owner);
			enterAsOwner(owner);
			break;
		case 3:
			AnnouncementManager.addAnnouncement(owner);
			enterAsOwner(owner);
			break;
		case 4:
			HousingManager.showHousings(owner);
			enterAsOwner(owner);
			break;
		case 5:
			HousingManager.createHousing(owner);
			enterAsOwner(owner);
			break;
		default:
			SakancomApp.entrance();
			return;
		}
	}
}