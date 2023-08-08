package sakancommain;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class ReservationManager {
	private ReservationManager () {}
	private static Skankom skankom = Skankom.getInstance();
	private static final Logger LOGGER = Logger.getLogger(ReservationManager.class.getName());

	public static void showReservations() {
		Collection<Housing> collection = skankom.getHousings().values();
		Housing[] housings = collection.toArray(new Housing[0]);
		if (housings.length == 0) {
			LOGGER.log(Level.INFO,"No housings added to show.");
			return;
		}
		for (Housing housing: housings) {
			String k=housing.toString() + "\nReservations:\n[";
			LOGGER.log(Level.INFO,k);
			List<String> tenants = null;
				tenants = housing.getTenants();
			
			for (String tenantId: tenants) {
				Tenant tenant = skankom.getTenant(tenantId);
				if (tenant != null) {
					String l=tenant.toString();
					LOGGER.log(Level.INFO,l);
				}
			}
			LOGGER.log(Level.INFO,"]");
			LOGGER.log(Level.INFO,"*****");
		}
	}

	public static void showReservations(Owner owner) throws IOException {
		ArrayList<Housing> housings = skankom.getHousings(owner);
		if (housings.isEmpty()) {
			LOGGER.log(Level.INFO,"No housings added to show.");
			return;
		}
		int counter = 1;
		for (Housing housing: housings) {
			String q=counter + ") " + housing.toString();
			LOGGER.log(Level.INFO,q);
			counter++;
		}
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Choose Housing \n2) Return Back");
		int choice = SakancomApp.scanInt();
		if(choice==1) {
			showAnnoucements(owner, housings);
			showReservations(owner);
		}
	}

	public static void showAnnoucements(Owner owner, List<Housing> housings) throws IOException {
		LOGGER.log(Level.INFO,"Enter Housing Number:");
		int housingNumber = SakancomApp.scanInt();
		if (housingNumber > housings.size()) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				showAnnoucements(owner, housings);
			}
			return;
		}
		int counter = 1;
		Housing housing = housings.get(housingNumber - 1);
		Map<String, String> reservations = housing.getReservations();
		if (reservations.isEmpty()) {
			LOGGER.log(Level.INFO,"No reservations to show.");
			return;
		}
		for (String key : reservations.keySet()) {
			String reservationId = reservations.get(key);
			Tenant tenant = skankom.getTenant(key);
			Apartment apartment = skankom.getApartment(reservationId);
			if (tenant != null && apartment != null) {
				String s=counter + ") Owner: " + owner.toString() + ", Apartment: " + apartment.toString();
				LOGGER.log(Level.INFO,s);	
				counter++;
			}
		}
		showReservationDetails(owner, housings, housing, reservations);
	}

	public static void showReservationDetails(Owner owner, List<Housing> housings, Housing housing, Map<String, String> reservations) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Accept/Reject Reservation \n2) Return Back \n3) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			acceptRejectReservation(housing, reservations);
			showReservationDetails(owner, housings, housing, reservations);
			break;
		case 2:
			showAnnoucements(owner, housings);
			break;
		default:
			
		}
	}

	public static void acceptRejectReservation(Housing housing, Map<String, String> reservations) throws IOException {
		LOGGER.log(Level.INFO,"Enter Reservation Number:");
		int reservationNumber = SakancomApp.scanInt();
		if (reservationNumber > reservations.size()) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again 2) Cancel");	
			if (SakancomApp.scanInt() == 1) {
				acceptRejectReservation(housing, reservations);
			}
			return;
		}
		LOGGER.log(Level.INFO,"1) Accept \n2) Reject");
		Boolean accept = SakancomApp.scanInt() == 1;
		String tenantId = reservations.keySet().toArray(new String[0])[reservationNumber - 1];
		String apartmentId = reservations.get(tenantId);
		if (Boolean.TRUE.equals(accept)) {
			housing.addTenant(tenantId, apartmentId);
		} else {
			housing.removeReservation(tenantId);
			LOGGER.log(Level.INFO,"Reservation is deleted.");
		}
	}

	public static void showReservations(Tenant tenant) throws IOException {
		List<String> reservations = tenant.getReservedHousing();
		if (reservations.isEmpty()) {
			LOGGER.log(Level.INFO,"No reservations added to show.");
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
					String j=counter + ") Reservation Housing " + housing.toString() + ", Apartment " + apartment.toString();
					LOGGER.log(Level.INFO,j);
					counter++;
				}
			}
		}
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Remove Reservation \n2) Return Back");
		int choice = SakancomApp.scanInt();
		if(choice==1) {
			removeReservation(tenant, reservations);
			showReservations(tenant);
		}
	}

	public static void removeReservation(Tenant tenant, List<String> reservations) throws IOException {
		LOGGER.log(Level.INFO,"Enter Reservation Number:");
		int reservationNumber = SakancomApp.scanInt();
		if (reservationNumber > reservations.size()) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again 2) Cancel");	
			if (SakancomApp.scanInt() == 1) {
				removeReservation(tenant, reservations);
			}
			return;
		}
		LOGGER.log(Level.INFO,"Deleting Confirmation: \n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			String reservation = reservations.get(reservationNumber - 1);
			Housing housing = skankom.getHousing(reservation);
			if (housing != null) {
				housing.removeReservation(tenant.getId());
				String y="Reservation to Housing " + housing.toString() + " is deleted";
				LOGGER.log(Level.INFO,y);
			} else {
				tenant.removeReservedHousing(reservation);
				LOGGER.log(Level.INFO,"Reservation is deleted");
			}
		}
	}
}