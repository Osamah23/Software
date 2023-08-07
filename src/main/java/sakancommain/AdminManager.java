package sakancommain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class AdminManager{
	private AdminManager() {}
	private static Skankom skankom = Skankom.getInstance();
	private static final Logger LOGGER = Logger.getLogger(AdminManager.class.getName());
	public static void createAdmin(User user) throws IOException {
		LOGGER.log(Level.INFO,"Enter Admin Name:");
		String adminName = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter Email:");
		String email = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter Phone Number:");
		String phoneNumber = SakancomApp.getScanner().nextLine();
		Owner owner = new Owner( email, phoneNumber);
		Admin admin = new Admin(owner.getId(), adminName);
		user.setUserId(admin.getId());
		skankom.addAdmin(admin);
		skankom.addOwner(owner);
		skankom.addUser(user);
		String ax=admin.toString() + " is created.";
		LOGGER.log(Level.INFO,ax);
		enterAsAdmin(admin);
	}

	public static void enterAsAdmin(Admin admin) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Show Announecemnts \n2) Show Reservations \n3) Add Announcement \n4) Show Housings \n5) Add Housing \n6) Exit");
		int choice = SakancomApp.scanInt();
		Owner owner = skankom.getOwner(admin.getOwnerId());
		switch (choice) {
		case 1:
			AnnouncementManager.showAnnouncements(admin);
			break;
		case 2:
			ReservationManager.showReservations();
			enterAsAdmin(admin);
			break;
		case 3:
			if (owner != null) {
				AnnouncementManager.addAnnouncement(owner);
			}
			enterAsAdmin(admin);
			break;
		case 4:
			HousingManager.showHousings(admin);
			enterAsAdmin(admin);
			break;
		case 5:
			if (owner != null) {
				HousingManager.createHousing(owner);
			}
			enterAsAdmin(admin);
			break;
		default:
			SakancomApp.entrance();
			return;
		}
	}
}