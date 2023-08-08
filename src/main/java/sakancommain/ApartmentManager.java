package sakancommain;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class ApartmentManager {
	private ApartmentManager () {}
	private static Skankom skankom = Skankom.getInstance();
	 private static final Logger LOGGER = Logger.getLogger(ApartmentManager.class.getName());
	public static Apartment createApartment() throws IOException {
		LOGGER.log(Level.INFO,"Enter Number of Bedrooms:");
		int numberOfBedrooms = SakancomApp.scanInt();
		LOGGER.log(Level.INFO,"Enter Number of Bathrooms:");
		int numberOfBathrooms = SakancomApp.scanInt();
		LOGGER.log(Level.INFO,"Does it has Balcony? \n1) Yes\n2) No");
		boolean hasBalcony = SakancomApp.scanInt() == 1;
		LOGGER.log(Level.INFO,"Adding Apartment Confirmation:\n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			Apartment apartment = new Apartment( numberOfBathrooms, numberOfBedrooms, hasBalcony);
			skankom.addApartment(apartment);
			return apartment;
		}
		return null;
	}

	public static String deleteApartment(Floor floor) throws IOException {
		List<Apartment> apartments = getApartments(floor);
		if (apartments == null) { return null; }
		return removeApartment(apartments, floor);
	}

	public static List<Apartment> getApartments(Floor floor) {
		if (floor.getApartments().isEmpty()) {
			String n="No apartments added yet to " + floor.toString();
			LOGGER.log(Level.INFO,n);
			return Collections.emptyList();
		}
		int counter = 1;
		ArrayList<Apartment> apartments = new ArrayList<>();
		for (String apartmentId: floor.getApartments()) {
			Apartment apartment = skankom.getApartment(apartmentId);
			if (apartment != null) {
				apartments.add(apartment);
				String j=counter + ") " + apartment.toString();
				LOGGER.log(Level.INFO,j);
				counter++;
			}
		}
		return apartments;
	}

	public static void showApartments(Floor floor) throws IOException {
		List<Apartment> apartments = getApartments(floor);
		if (apartments == null) { return; }
		showApartmentDetails(apartments);
	}

	public static void showApartmentDetails(List<Apartment> apartments) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options: \n1) Show Apartment Details \n2) Return Back");
		int choice = SakancomApp.scanInt();
		if (choice==1) {
			LOGGER.log(Level.INFO,"Enter Apartment Number:");
			int apartmentNumber = SakancomApp.scanInt();
			if (apartmentNumber > apartments.size()) {
				LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again \n2) Cancel");
				if (SakancomApp.scanInt() == 1) {
					showApartmentDetails(apartments);
				}
				return;
			}
			Apartment apartment = apartments.get(apartmentNumber - 1);
			apartment.viewApartmentInfo();
			showApartmentDetails(apartments);
		}
	}

	public static String removeApartment(List<Apartment> apartments, Floor floor) throws IOException {
		LOGGER.log(Level.INFO,"Enter Apartment Number:");
		int apartmentNumber = SakancomApp.scanInt();
		if (apartmentNumber > apartments.size()) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				return removeApartment(apartments, floor);
			}
			return null;
		}
		LOGGER.log(Level.INFO,"1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return null; }
		Apartment apartment = apartments.get(apartmentNumber - 1);
		String apartmentInfo = apartment.toString();
		floor.removeApartment(apartment);
		skankom.removeApartment(apartment);
		return apartmentInfo;
	}
}