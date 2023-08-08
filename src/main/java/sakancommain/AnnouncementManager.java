package sakancommain;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class AnnouncementManager {
	private static Skankom skankom = Skankom.getInstance();
	private AnnouncementManager() {}
	private static final Logger LOGGER = Logger.getLogger(AnnouncementManager.class.getName());
	public static void showAnnouncements(Admin admin) throws IOException {
		Collection<Announcement> collection = skankom.getAnnouncements().values();
		Announcement[] announcements = collection.toArray(new Announcement[0]);
		if (announcements.length == 0) {
			LOGGER.log(Level.INFO,NOAN);
			AdminManager.enterAsAdmin(admin);
			return;
		}
		int counter = 1;
		for (Announcement announcement: announcements) {
			String a=counter + ") " + announcement.toString();
			LOGGER.log(Level.INFO,a);
			counter++;
		}
		
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Accept/Reject Announcement \n2) Remove Announcement \n3) Return Back \n4) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			acceptRejectAnnouncement(announcements);
			AdminManager.enterAsAdmin(admin);
			break;
		case 2:
			removeAnnouncement(announcements);
			AdminManager.enterAsAdmin(admin);
			break;
		case 3:
			AdminManager.enterAsAdmin(admin);
			break;
		default:
			SakancomApp.entrance();
		}
	}

	public static void showAnnouncements(Owner owner) throws IOException {
		ArrayList<Announcement> announcements = skankom.getAnnouncements(owner);

		if (announcements.isEmpty()) {
			LOGGER.log(Level.INFO,NOAN);
			OwnerManager.enterAsOwner(owner);
			return;
		}
		int counter = 1;
		for (Announcement announcement: announcements) {
			AnnouncementStatus status = skankom.getAnnouncementStatus(announcement.getId());
			String na=counter + ") Announcement Title: " + announcement.getTitle() + 
					", Description: " + announcement.getDescription()
					+ ", Status: " + getStatusString(status);
			LOGGER.log(Level.INFO,na);
			counter++;
		}
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Remove Announcement \n2) Return Back \n3) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			Announcement[] announs = announcements.toArray(new Announcement[0]); 
			removeAnnouncement(announs);
			OwnerManager.enterAsOwner(owner);
			break;
		case 2:
			OwnerManager.enterAsOwner(owner);
			break;
		default:
			SakancomApp.entrance();
		}
	}
	private static final String NOAN="No announcements to show";
	public static void showAnnouncements(Tenant tenant) throws IOException {
		ArrayList<Announcement> arrayList = skankom.getAcceptedAnnouncements();
		Announcement[] announcements = arrayList.toArray(new Announcement[0]);
		if (announcements.length == 0) {
			LOGGER.log(Level.INFO,NOAN);
			TenantManager.enterAsTenant(tenant);
			return;
		}
		int counter = 1;
		for (Announcement announcement: announcements) {
			String n=counter + ") " + announcement.toString();
			LOGGER.log(Level.INFO,n);
			counter++;
		}
		showAnnouncementsDetails(tenant, announcements);
	}
	
	public static void showAnnouncementsDetails(Tenant tenant, Announcement[] announcements) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Choose Announcement \n2) Return Back \n3) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			chooseAnnouncement(tenant, announcements);
			showAnnouncementsDetails(tenant, announcements);
			break;
		case 2:
			TenantManager.enterAsTenant(tenant);
			break;
		default:
			SakancomApp.entrance();
		}
	}
	private static final String ENTER="The entered number is not exist.\n1) Try Again 2) Cancel";
	private static final String ANNUM="Enter Announcement Number:";
	public static void chooseAnnouncement(Tenant tenant, Announcement[] announcements) throws IOException {
		LOGGER.log(Level.INFO,ANNUM);
		int announcementNumber = SakancomApp.scanInt();
		if (announcementNumber > announcements.length) {
			
			LOGGER.log(Level.INFO,ENTER);
			if (SakancomApp.scanInt() == 1) {
				chooseAnnouncement(tenant, announcements);
			}
			return;
		}
		LOGGER.log(Level.INFO,"1) Show Housing Apartments \n2) Show Housing Tenants \n3) Show Housing servcies \n4) Show Housing Photos \n5) Return Back");
		int choice = SakancomApp.scanInt();
		Announcement announcement =  announcements[announcementNumber - 1];
		String housingId = announcement.getHousing();
		Housing housing = skankom.getHousing(housingId);
		if (housing == null) {
			String h="Cannot find Housing with id: " + housingId;
			LOGGER.log(Level.INFO,h);
			chooseAnnouncement(tenant, announcements);
			return;
		}
		switch (choice) {
		case 1:
			showHousingApartments(tenant, announcements, housing);
			break;
		case 2:
			housing.viewTenants();
			break;
		case 3:
			announcement.viewServices();
			break;
		case 4:
			announcement.viewPhotos();
			break;
		default:
		}
	}
	
	public static void showHousingApartments(Tenant tenant, Announcement[] announcements, Housing housing) throws IOException {
		Apartment[] apartments = housing.getApartments().toArray(new Apartment[0]);
		if (apartments.length == 0) {
			LOGGER.log(Level.INFO,"No apartments to show");
			chooseAnnouncement(tenant, announcements);
			return;
		}
		int counter = 1;
		for(Apartment apartment: apartments) {
			String b=counter + ") " + apartment.toString();
			LOGGER.log(Level.INFO,b);
			counter++;
		}
		showHousingApartmentsDetails(tenant, announcements, housing, apartments);
	}
	
	public static void showHousingApartmentsDetails(Tenant tenant, Announcement[] announcements, Housing housing, Apartment[] apartments) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Choose Apartment \n2) Return Back \n3) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			chooseApartment(tenant, housing, apartments);
			showHousingApartmentsDetails(tenant, announcements, housing, apartments);
			break;
		case 2:
			chooseAnnouncement(tenant, announcements);
			break;
		default:
			SakancomApp.entrance();
		}
	}
	
	public static void chooseApartment(Tenant tenant, Housing housing, Apartment[] apartments) throws IOException {
		LOGGER.log(Level.INFO,"Enter Apartment Number:");
		int apartmentNumber = SakancomApp.scanInt();
		if (apartmentNumber > apartments.length) {
			LOGGER.log(Level.INFO,ENTER);
			if (SakancomApp.scanInt() == 1) {
				chooseApartment(tenant, housing, apartments);
			}
			return;
		}
		LOGGER.log(Level.INFO,"1) Reserve Apartment \n2) Return Back");
		int choice = SakancomApp.scanInt();
		Apartment apartment = apartments[apartmentNumber - 1];
		switch (choice) {
		case 1:
			LOGGER.log(Level.INFO,"Reserving Apartment Confirmation:\n1) Confirm \n2) Cancel");
			choice = SakancomApp.scanInt();
			if (choice == 1) {
				housing.addReservation(tenant.getId(), apartment.getId());
				tenant.addReservedHousing(housing);
				String j="Apartment with: " + apartment.toString() + " reservation request is sent.";
				LOGGER.log(Level.INFO,j);
			}
			break;
			default:
		}
	}
	
	public static String getStatusString(AnnouncementStatus status) {
		switch (status) {
		case ACCEPTED:
			return "accepted";
		case REJECTED:
			return "rejected";
		default:
			return "PENDING";
		}
	}

	public static void acceptRejectAnnouncement(Announcement[] announcements) throws IOException {
		LOGGER.log(Level.INFO,ANNUM);
		int announcementNumber = SakancomApp.scanInt();
		if (announcementNumber > announcements.length) {
			LOGGER.log(Level.INFO,ENTER);	
			if (SakancomApp.scanInt() == 1) {
				acceptRejectAnnouncement(announcements);
			}
			return;
		}
		System.out.println("1) Accept \n2) Reject");
		Boolean accept = SakancomApp.scanInt() == 1;
		Announcement announcement = announcements[announcementNumber - 1];
		skankom.addAnnouncementStatus(announcement, accept ? AnnouncementStatus.ACCEPTED : AnnouncementStatus.REJECTED);
		System.out.println("Announcement with Title: " + announcement.getTitle() + " is " + (accept ? "accepted" : "rejected"));
	}

	public static void removeAnnouncement(Announcement[] announcements) throws IOException {
		LOGGER.log(Level.INFO,ANNUM);
		int announcementNumber = SakancomApp.scanInt();
		if (announcementNumber > announcements.length) {
			System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				removeAnnouncement(announcements);
			}
			return;
		}
		System.out.println("1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return; }
		Announcement announcement = announcements[announcementNumber - 1];
		skankom.removeAnnouncement(announcement);
		System.out.println("Announcement with Title: " + announcement.getTitle() + " is deleted");
	}

	public static void addAnnouncement(Owner owner) throws IOException {
		List<String> housingIds = owner.getOwnedHosuings();
		if (housingIds.isEmpty()) {
			System.out.println("No housings yet to advertise them.");
			return;
		}
		int counter = 1;
		for (String housingId: housingIds) {
			Housing housing = skankom.getHousing(housingId);
			if (housing != null) {
				System.out.println(counter + ") " + housing.toString());
			}
			counter++;
		}
		System.out.println("Enter Hosuing Number:");
		int housingNumber = SakancomApp.scanInt();
		if (housingNumber > housingIds.size()) {
			System.out.println("The enetred number is not exist.\n1) Try Again \n2) Cancel");
			int choice = SakancomApp.scanInt();
			if (choice == 1) {
				addAnnouncement(owner);
			}
			return;
		}
		System.out.println("Enter Title:");
		String title = SakancomApp.getScanner().nextLine();
		System.out.println("Enter Description:");
		String description = SakancomApp.getScanner().nextLine();
		System.out.println("Enter Price:");
		double price = SakancomApp.scanDouble();
		System.out.println("Is electriciry and water enclusive:\n1) Yes\n2) No");
		boolean includesElectricityAndWater = SakancomApp.scanInt() == 1;
		Announcement announcement = new Announcement(title, description, price, includesElectricityAndWater, owner.getId(), housingIds.get(housingNumber - 1));
		skankom.addAnnouncement(announcement);
		createAnnouncementDetails(announcement);
		System.out.println("Adding Announcement Confirmation:\n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice != 1) {
			skankom.removeAnnouncement(announcement);
		}
	}

	public static void createAnnouncementDetails(Announcement announcement) throws IOException {
		System.out.println("Choose one of the following options:\n1) Manage Services \n2) Manage Photos \n3) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			ServiceManager.manageServices(announcement);
			createAnnouncementDetails(announcement);
			break;
		case 2:
			PhotoManager.managePhotos(announcement);
			createAnnouncementDetails(announcement);
			break;
		case 3:
			break;
		default:
			System.out.println("Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				createAnnouncementDetails(announcement);
			}
		}
	}
}