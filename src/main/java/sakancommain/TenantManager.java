package sakancommain;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class TenantManager {
	private static Skankom skankom = Skankom.getInstance();
	private TenantManager() {}
	private static final Logger LOGGER = Logger.getLogger(TenantManager.class.getName());

	public static void createTenant(User user) throws IOException {
		LOGGER.log(Level.INFO,"Enter Tenant Name:");
		String tenantName = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter University Major:");
		String universityMajor = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter Age:");
		int age = SakancomApp.scanInt();
		LOGGER.log(Level.INFO,"Enter Email:");
		String email = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter Phone Number:");
		String phoneNumber = SakancomApp.getScanner().nextLine();
		Tenant tenant = new Tenant(tenantName, email, phoneNumber, universityMajor, age);
		user.setUserId(tenant.getId());
		skankom.addTenant(tenant);
		skankom.addUser(user);
		String a=tenant.toString() + " is created.";
		LOGGER.log(Level.INFO,a);
		enterAsTenant(tenant);
	}

	public static void enterAsTenant(Tenant tenant) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Show Announecemnts \n2) Manage Reservations \n3) Manage Booking \n4) Manage Furniture \n5) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			AnnouncementManager.showAnnouncements(tenant);
			break;
		case 2:
			ReservationManager.showReservations(tenant);
			enterAsTenant(tenant);
			break;
		case 3:
			manageBooking(tenant);
			enterAsTenant(tenant);
			break;
		case 4:
			FurnitureManager.manageFurniture(tenant);
			enterAsTenant(tenant);
			break;
		default:
			SakancomApp.entrance();
			return;
		}
	}

	public static void manageBooking(Tenant tenant) throws IOException {
		if (tenant.getBookedHousing() == null) {
			String l="You haven't booked any apartment yet.";
			LOGGER.log(Level.INFO,l);
			return;
		}
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Show Booking Details \n2) Remove Booking \n3) Return Back \n4) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			String w=tenant.toString();
			LOGGER.log(Level.INFO,w);
			Housing bookedHousing = skankom.getHousing(tenant.getBookedHousing());
			if (bookedHousing != null) {
				Owner owner = skankom.getOwner(bookedHousing.getOwnerId());
				if (owner != null) {
					String e=owner.toString();
					LOGGER.log(Level.INFO,e);
				}
			}
			String m="Time to pay rent: " + tenant.getTimeToPay();
			LOGGER.log(Level.INFO,m);
			break;
		case 2:
			LOGGER.log(Level.INFO,"Delete Booking Confirmation:\n1) Confirm \n2) Cancel");
			choice = SakancomApp.scanInt();
			if (choice == 1) {
				Housing housing = skankom.getHousing(tenant.getBookedHousing());
				if (housing != null) {
					housing.removeTenant(tenant);
				}
				tenant.removebBookedHousing();
				LOGGER.log(Level.INFO,"Booked Apartment is deleted successflly");
			}
			enterAsTenant(tenant);
			break;
		case 3:
			break;
		default:
			SakancomApp.entrance();
			return;
		}
	}
}