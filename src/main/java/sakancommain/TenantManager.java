package sakancommain;

import java.io.IOException;
import java.util.*;
import owner.*;

public class TenantManager {
	private static Skankom skankom = Skankom.getInstance();

	public static void createTenant(User user) throws IOException {
		System.out.println("Enter Tenant Name:");
		String tenantName = SakancomApp.getScanner().nextLine();
		System.out.println("Enter University Major:");
		String universityMajor = SakancomApp.getScanner().nextLine();
		System.out.println("Enter Age:");
		int age = SakancomApp.scanInt();
		System.out.println("Enter Email:");
		String email = SakancomApp.getScanner().nextLine();
		System.out.println("Enter Phone Number:");
		String phoneNumber = SakancomApp.getScanner().nextLine();
		Tenant tenant = new Tenant(tenantName, email, phoneNumber, universityMajor, age);
		user.setUserId(tenant.getId());
		skankom.addTenant(tenant);
		skankom.addUser(user);
		System.out.println(tenant.toString() + " is created.");
		enterAsTenant(tenant);
	}

	public static void enterAsTenant(Tenant tenant) throws IOException {
		System.out.println("Choose one of the following options:\n1) Show Announecemnts \n2) Manage Reservations \n3) Manage Booking \n4) Manage Furniture \n5) Exit");
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
			System.out.println("You haven't booked any apartment yet.");
			return;
		}
		System.out.println("Choose one of the following options:\n1) Show Booking Details \n2) Remove Booking \n3) Return Back \n4) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			System.out.println(tenant.toString());
			Housing bookedHousing = skankom.getHousing(tenant.getBookedHousing());
			if (bookedHousing != null) {
				Owner owner = skankom.getOwner(bookedHousing.getOwnerId());
				if (owner != null) {
					System.out.println(owner.toString());
				}
			}
			System.out.println("Time to pay rent: " + tenant.getTimeToPay());
			break;
		case 2:
			System.out.println("Delete Booking Confirmation:\n1) Confirm \n2) Cancel");
			choice = SakancomApp.scanInt();
			if (choice == 1) {
				Housing housing = skankom.getHousing(tenant.getBookedHousing());
				if (housing != null) {
					housing.removeTenant(tenant);
				}
				tenant.removebBookedHousing();
				System.out.println("Booked Apartment is deleted successflly");
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