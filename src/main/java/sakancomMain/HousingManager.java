package sakancomMain;

import java.util.*;
import owner.*;

public class HousingManager {
	private static Skankom skankom = Skankom.getInstance();

	public static Housing createHousing(Owner owner) {
		System.out.println("Enter Location:");
		String location = SakancomApp.getScanner().nextLine();
		System.out.println("Does it student apartment? \n1) Yes\n2) No");
		boolean isStudentApartment = SakancomApp.scanInt() == 1;
		Housing housing = new Housing(owner.getId(), location, isStudentApartment);
		housing = createHousingDetails(housing);
		System.out.println("Adding Housing Confirmation:\n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			owner.addHousing(housing);
			skankom.addHousing(housing);
			System.out.println("Housing with: " + housing.toString() + " is added.");
			return housing;
		}
		return null;
	}

	public static Housing createHousingDetails(Housing housing) {
		System.out.println("Choose one of the following options:\n1) Manage Floors \n2) Show Housing Details \n3) Edit Location \n4) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			FloorManager.manageFloors(housing);
			return createHousingDetails(housing);
		case 2:
			housing.viewHousingStatistics();
			return createHousingDetails(housing); 
		case 3:
			System.out.println("Enter New Location:");
			String location = SakancomApp.getScanner().nextLine();
			housing.setLocation(location);
			return createHousingDetails(housing);
		case 4:
			return housing;
		default:
			System.out.println("Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				return createHousingDetails(housing);
			}
		}
		return housing;
	}

	public static Housing editHousing(Housing housing) {
		return createHousingDetails(housing);
	}

	public static void showHousings(Admin admin) {
		Collection<Housing> collection = skankom.getHousings().values();
		Housing[] housings = collection.toArray(new Housing[0]);
		if (housings.length == 0) {
			System.out.println("No hosuings to show");
			AdminManager.enterAsAdmin(admin);
			return;
		}
		int counter = 1;
		for (Housing housing: housings) {
			System.out.println(counter + ") " + housing.toString());
			String ownerId = housing.getOwnerId();
			Owner owner = skankom.getOwner(ownerId);
			if (owner != null) {
				System.out.println(owner.toString());
			}
			counter++;
		}
		System.out.println("Choose one of the following options:\n1) Edit Housing \n2) Remove Housing \n3) Return Back \n4) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			editHousing(housings);
			AdminManager.enterAsAdmin(admin);
			break;
		case 2:
			removeHousing(housings);
			AdminManager.enterAsAdmin(admin);
		case 3:
			AdminManager.enterAsAdmin(admin);
			break;
		default:
			SakancomApp.entrance();
		}
	}
	
	public static void showHousings(Owner owner) {
		ArrayList<Housing> arrayList = skankom.getHousings(owner);
		Housing[] housings = arrayList.toArray(new Housing[0]);
		if (housings.length == 0) {
			System.out.println("No hosuings to show");
			OwnerManager.enterAsOwner(owner);
			return;
		}
		int counter = 1;
		for (Housing housing: housings) {
			System.out.println(counter + ") " + housing.toString());
			counter++;
		}
		System.out.println("Choose one of the following options:\n1) Edit Housing \n2) Remove Housing \n3) Return Back \n4) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			editHousing(housings);
			OwnerManager.enterAsOwner(owner);
			break;
		case 2:
			removeHousing(housings);
			OwnerManager.enterAsOwner(owner);
		case 3:
			OwnerManager.enterAsOwner(owner);
			break;
		default:
			SakancomApp.entrance();
		}
	}

	public static void editHousing(Housing[] housings) {
		System.out.println("Enter Housing Number:");
		int housingNumber = SakancomApp.scanInt();
		if (housingNumber <= housings.length) {
			Housing housing = housings[housingNumber - 1];
			housing = editHousing(housing);
			if (housing != null) {
				System.out.println("Housing with: " + housing.toString() + " is edited." );
			}
		} else {
			System.out.println("The entered number is not exist.\n1) Try Again 2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				editHousing(housings);
			}
		}
	}

	public static void removeHousing(Housing[] housings) {
		System.out.println("Enter Housing Number:");
		int housingNumber = SakancomApp.scanInt();
		if (housingNumber > housings.length) {
			System.out.println("The entered number is not exist.\n1) Try Again 2) Cancel");	
			if (SakancomApp.scanInt() == 1) {
				removeHousing(housings);
			}
			return;
		}
		System.out.println("1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return; }
		Housing housing = housings[housingNumber - 1];
		Owner owner = skankom.getOwner(housing.getOwnerId());
		if (owner != null) {
			owner.removeHousing(housing);
		}
		skankom.removeHousing(housing);
		System.out.println(housing.toString() + " is deleted");
	}
}