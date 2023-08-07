package sakancomMain;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class ReservationManager {
	private static Skankom skankom = Skankom.getInstance();
	private static final Logger LOGGER = Logger.getLogger(ReservationManager.class.getName());

	public static void showReservations() throws IOException {
		Collection<Housing> collection = skankom.getHousings().values();
		Housing[] housings = collection.toArray(new Housing[0]);
		if (housings.length == 0) {
			System.out.println("No housings added to show.");
			return;
		}
		for (Housing housing: housings) {
			System.out.println(housing.toString() + "\nReservations:\n[");
			List<String> tenants = null;
			try {
				tenants = housing.getTenants();
			} catch (IOException e) {
				LOGGER.log(Level.INFO, "error in get tenant func");
			}
			for (String tenantId: tenants) {
				Tenant tenant = skankom.getTenant(tenantId);
				if (tenant != null) {
					System.out.println(tenant.toString());
				}
			}
			System.out.println("]");
			System.out.println("*****");
		}
	}

	public static void showReservations(Owner owner) throws IOException {
		ArrayList<Housing> housings = skankom.getHousings(owner);
		if (housings.isEmpty()) {
			System.out.println("No housings added to show.");
			return;
		}
		int counter = 1;
		for (Housing housing: housings) {
			System.out.println(counter + ") " + housing.toString());
			counter++;
		}
		System.out.println("Choose one of the following options:\n1) Choose Housing \n2) Return Back");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			showAnnoucements(owner, housings);
			showReservations(owner);
			break;
		default:
			break;
		}
	}

	public static void showAnnoucements(Owner owner, ArrayList<Housing> housings) throws IOException {
		System.out.println("Enter Housing Number:");
		int housingNumber = SakancomApp.scanInt();
		if (housingNumber > housings.size()) {
			System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				showAnnoucements(owner, housings);
			}
			return;
		}
		int counter = 1;
		Housing housing = housings.get(housingNumber - 1);
		Map<String, String> reservations = housing.getReservations();
		if (reservations.isEmpty()) {
			System.out.println("No reservations to show.");
			return;
		}
		for (String key : reservations.keySet()) {
			String reservationId = reservations.get(key);
			Tenant tenant = skankom.getTenant(key);
			Apartment apartment = skankom.getApartment(reservationId);
			if (tenant != null && apartment != null) {
				System.out.println(counter + ") Owner: " + owner.toString() + ", Apartment: " + apartment.toString());	
				counter++;
			}
		}
		showReservationDetails(owner, housings, housing, reservations);
	}

	public static void showReservationDetails(Owner owner, ArrayList<Housing> housings, Housing housing, Map<String, String> reservations) throws IOException {
		System.out.println("Choose one of the following options:\n1) Accept/Reject Reservation \n2) Return Back \n3) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			acceptRejectReservation(housing, reservations);
			showReservationDetails(owner, housings, housing, reservations);
			break;
		case 2:
			showAnnoucements(owner, housings);
			break;
		}
	}

	public static void acceptRejectReservation(Housing housing, Map<String, String> reservations) throws IOException {
		System.out.println("Enter Reservation Number:");
		int reservationNumber = SakancomApp.scanInt();
		if (reservationNumber > reservations.size()) {
			System.out.println("The entered number is not exist.\n1) Try Again 2) Cancel");	
			if (SakancomApp.scanInt() == 1) {
				acceptRejectReservation(housing, reservations);
			}
			return;
		}
		System.out.println("1) Accept \n2) Reject");
		Boolean accept = SakancomApp.scanInt() == 1;
		String tenantId = reservations.keySet().toArray(new String[0])[reservationNumber - 1];
		String apartmentId = reservations.get(tenantId);
		if (accept) {
			housing.addTenant(tenantId, apartmentId);
		} else {
			housing.removeReservation(tenantId);
			System.out.println("Reservation is deleted.");
		}
	}

	public static void showReservations(Tenant tenant) throws IOException {
		ArrayList<String> reservations = tenant.getReservedHousing();
		if (reservations.isEmpty()) {
			System.out.println("No reservations added to show.");
			return;
		}
		int counter = 1;
		for (String reservation: reservations) {
			Housing housing = skankom.getHousing(reservation);
			if (housing != null) {
				String apartmentId = housing.getReservations().get(tenant.getId());
				if (apartmentId == null) { continue; }
				Apartment apartment = skankom.getApartment(apartmentId);
				if (apartment != null) {
					System.out.println(counter + ") Reservation Housing " + housing.toString() + ", Apartment " + apartment.toString());
					counter++;
				}
			}
		}
		System.out.println("Choose one of the following options:\n1) Remove Reservation \n2) Return Back");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			removeReservation(tenant, reservations);
			showReservations(tenant);
			break;
		default:
			break;
		}
	}

	public static void removeReservation(Tenant tenant, ArrayList<String> reservations) throws IOException {
		System.out.println("Enter Reservation Number:");
		int reservationNumber = SakancomApp.scanInt();
		if (reservationNumber > reservations.size()) {
			System.out.println("The entered number is not exist.\n1) Try Again 2) Cancel");	
			if (SakancomApp.scanInt() == 1) {
				removeReservation(tenant, reservations);
			}
			return;
		}
		System.out.println("Deleting Confirmation: \n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			String reservation = reservations.get(reservationNumber - 1);
			Housing housing = skankom.getHousing(reservation);
			if (housing != null) {
				housing.removeReservation(tenant.getId());
				System.out.println("Reservation to Housing " + housing.toString() + " is deleted");
			} else {
				tenant.removeReservedHousing(reservation);
				System.out.println("Reservation is deleted");
			}
		}
	}
}