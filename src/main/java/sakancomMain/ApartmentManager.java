package sakancomMain;

import java.io.IOException;
import java.util.*;
import owner.*;

public class ApartmentManager {
	private static Skankom skankom = Skankom.getInstance();

	public static Apartment createApartment(Floor floor) throws IOException {
		System.out.println("Enter Number of Bedrooms:");
		int numberOfBedrooms = SakancomApp.scanInt();
		System.out.println("Enter Number of Bathrooms:");
		int numberOfBathrooms = SakancomApp.scanInt();
		System.out.println("Does it has Balcony? \n1) Yes\n2) No");
		boolean hasBalcony = SakancomApp.scanInt() == 1;
		System.out.println("Adding Apartment Confirmation:\n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			Apartment apartment = new Apartment( numberOfBathrooms, numberOfBedrooms, hasBalcony);
			skankom.addApartment(apartment);
			return apartment;
		}
		return null;
	}

	public static String deleteApartment(Floor floor) throws IOException {
		ArrayList<Apartment> apartments = getApartments(floor);
		if (apartments == null) { return null; }
		return removeApartment(apartments, floor);
	}

	public static ArrayList<Apartment> getApartments(Floor floor) {
		if (floor.getApartments().isEmpty()) {
			System.out.println("No apartments added yet to " + floor.toString());
			return null;
		}
		int counter = 1;
		ArrayList<Apartment> apartments = new ArrayList<>();
		for (String apartmentId: floor.getApartments()) {
			Apartment apartment = skankom.getApartment(apartmentId);
			if (apartment != null) {
				apartments.add(apartment);
				System.out.println(counter + ") " + apartment.toString());
				counter++;
			}
		}
		return apartments;
	}

	public static void showApartments(Floor floor) throws IOException {
		ArrayList<Apartment> apartments = getApartments(floor);
		if (apartments == null) { return; }
		showApartmentDetails(apartments);
	}

	public static void showApartmentDetails(ArrayList<Apartment> apartments) throws IOException {
		System.out.println("Choose one of the following options: \n1) Show Apartment Details \n2) Return Back");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			System.out.println("Enter Apartment Number:");
			int apartmentNumber = SakancomApp.scanInt();
			if (apartmentNumber > apartments.size()) {
				System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
				if (SakancomApp.scanInt() == 1) {
					showApartmentDetails(apartments);
				}
				return;
			}
			Apartment apartment = apartments.get(apartmentNumber - 1);
			apartment.viewApartmentInfo();
			showApartmentDetails(apartments);
		default:
			return;
		}
	}

	public static String removeApartment(ArrayList<Apartment> apartments, Floor floor) throws IOException {
		System.out.println("Enter Apartment Number:");
		int apartmentNumber = SakancomApp.scanInt();
		if (apartmentNumber > apartments.size()) {
			System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				return removeApartment(apartments, floor);
			}
			return null;
		}
		System.out.println("1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return null; }
		Apartment apartment = apartments.get(apartmentNumber - 1);
		String apartmentInfo = apartment.toString();
		floor.removeApartment(apartment);
		skankom.removeApartment(apartment);
		return apartmentInfo;
	}
}