package sakancommain;

import java.io.IOException;
import owner.*;

public class FloorManager {
	private FloorManager () {}
	private static Skankom skankom = Skankom.getInstance();

	public static void manageFloors(Housing housing) throws IOException {
		System.out.println("Choose one of the following options:\n1) Add Floor \n2) Remove Floor \n3) Show Floors \n4) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			Floor floor = new Floor(housing.getId(), housing.getFloors().size() + 1);
			floor = createFloor(housing, floor);
			System.out.println(floor.toString() + " is created to Housing with: " + housing.toString());
			housing.addFloor(floor);
			skankom.addFloor(floor);
			manageFloors(housing);
			break;
		case 2:
			String floorInfo = deleteFloor(housing);
			if (floorInfo != null) {
				System.out.println(floorInfo + " is deleted from Housing with: " + housing.toString());
			}
			manageFloors(housing);
			break;
		case 3:
			showFloors(housing);
			manageFloors(housing);
			break;
		case 4:
			break;
		default:
			System.out.println("Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				manageFloors(housing);
			}
		}
	}

	public static void showFloors(Housing housing) {
		if (housing.getFloors().isEmpty()) {
			System.out.println("No floors added yet to " + housing.toString());
			return;
		}
		System.out.println("Floors in " + housing.toString() + " are: [");
		for (String floorId: housing.getFloors()) {
			Floor floor = skankom.getFloor(floorId);
			if (floor != null) {
				System.out.println(floor.toString());
			}
		}
		System.out.println("]");
	}

	public static String deleteFloor(Housing housing) throws IOException {
		if (housing.getFloors().isEmpty()) {
			System.out.println("No floors added yet to " + housing.toString());
			return null;
		}
		return removeFloor(housing);
	}

	public static String removeFloor(Housing housing) throws IOException {
		System.out.println("Enter Floor Number:");
		int floorNumber = SakancomApp.scanInt();
		if (floorNumber > housing.getFloors().size()) {
			System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				return removeFloor(housing);
			}
			return null;
		}
		System.out.println("1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return null; }
		String floorId = housing.getFloors().get(floorNumber - 1);
		Floor floor = skankom.getFloor(floorId);
		String floorInfo = floor.toString();
		housing.removeFloor(floor);
		skankom.removeFloor(floor);
		return floorInfo;
	}

	public static Floor createFloor(Housing housing, Floor floor) throws IOException {
		System.out.println("Choose one of the following options:\n1) Add Apartment \n2) Remove Apartment \n3) Show Apartments \n4) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			Apartment apartment = ApartmentManager.createApartment();
			if (apartment != null) {
				floor.addApartment(apartment);
				System.out.println("Apartment with Info: " + apartment.toString() + " is added to " + floor.toString());
			}
			return createFloor(housing, floor);
		case 2:
			String apartmentInfo = ApartmentManager.deleteApartment(floor);
			if (apartmentInfo != null) {
				System.out.println("Apartment with Info: " + apartmentInfo + " is deleted from " + floor.toString());
			}
			return createFloor(housing, floor);
		case 3:
			ApartmentManager.showApartments(floor);
			return createFloor(housing, floor);
		case 4:
			return floor;
		default:
			System.out.println("Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				return createFloor(housing, floor);
			}
		}
		return floor;
	}
}