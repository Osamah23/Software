package sakancommain;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class HousingManager {
	private static Skankom skankom = Skankom.getInstance();
	private HousingManager() {}
	private static final Logger LOGGER = Logger.getLogger(HousingManager.class.getName());

	public static Housing createHousing(Owner owner) throws IOException {
		LOGGER.log(Level.INFO,"Enter Location:");
		String location = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Does it student apartment? \n1) Yes\n2) No");
		boolean isStudentApartment = SakancomApp.scanInt() == 1;
		Housing housing = new Housing(owner.getId(), location, isStudentApartment);
		housing = createHousingDetails(housing);
		LOGGER.log(Level.INFO,"Adding Housing Confirmation:\n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			owner.addHousing(housing);
			skankom.addHousing(housing);
			String c="Housing with: " + housing.toString() + " is added.";
			LOGGER.log(Level.INFO,c);
			return housing;
		}
		return null;
	}

	public static Housing createHousingDetails(Housing housing) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Manage Floors \n2) Show Housing Details \n3) Edit Location \n4) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			FloorManager.manageFloors(housing);
			return createHousingDetails(housing);
		case 2:
			housing.viewHousingStatistics();
			return createHousingDetails(housing); 
		case 3:
			LOGGER.log(Level.INFO,"Enter New Location:");
			String location = SakancomApp.getScanner().nextLine();
			housing.setLocation(location);
			return createHousingDetails(housing);
		case 4:
			return housing;
		default:
			LOGGER.log(Level.INFO,"Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				return createHousingDetails(housing);
			}
		}
		return housing;
	}

	public static Housing editHousing(Housing housing) throws IOException {
		return createHousingDetails(housing);
	}

	public static void showHousings(Admin admin) throws IOException {
		Collection<Housing> collection = skankom.getHousings().values();
		Housing[] housings = collection.toArray(new Housing[0]);
		if (housings.length == 0) {
			LOGGER.log(Level.INFO,"No hosuings to show");
			AdminManager.enterAsAdmin(admin);
			return;
		}
		int counter = 1;
		for (Housing housing: housings) {
			String t=counter + ") " + housing.toString();
			LOGGER.log(Level.INFO,t);
			String ownerId = housing.getOwnerId();
			Owner owner = skankom.getOwner(ownerId);
			if (owner != null) {
				String b=owner.toString();
				LOGGER.log(Level.INFO,b);
			}
			counter++;
		}
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Edit Housing \n2) Remove Housing \n3) Return Back \n4) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			editHousing(housings);
			AdminManager.enterAsAdmin(admin);
			break;
		case 2:
			removeHousing(housings);
			AdminManager.enterAsAdmin(admin);
			break;
		case 3:
			AdminManager.enterAsAdmin(admin);
			break;
		default:
			SakancomApp.entrance();
		}
	}
	
	public static void showHousings(Owner owner) throws IOException {
		List<Housing> arrayList = skankom.getHousings(owner);
		Housing[] housings = arrayList.toArray(new Housing[0]);
		if (housings.length == 0) {
			LOGGER.log(Level.INFO,"No hosuings to show");
			OwnerManager.enterAsOwner(owner);
			return;
		}
		int counter = 1;
		for (Housing housing: housings) {
			String m=counter + ") " + housing.toString();
			LOGGER.log(Level.INFO,m);
			counter++;
		}
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Edit Housing \n2) Remove Housing \n3) Return Back \n4) Exit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			editHousing(housings);
			OwnerManager.enterAsOwner(owner);
			break;
		case 2:
			removeHousing(housings);
			OwnerManager.enterAsOwner(owner);
			break;
		case 3:
			OwnerManager.enterAsOwner(owner);
			break;
		default:
			SakancomApp.entrance();
		}
	}

	public static void editHousing(Housing[] housings) throws IOException {
		LOGGER.log(Level.INFO,"Enter Housing Number:");
		int housingNumber = SakancomApp.scanInt();
		if (housingNumber <= housings.length) {
			Housing housing = housings[housingNumber - 1];
			housing = editHousing(housing);
			if (housing != null) {
				String h="Housing with: " + housing.toString() + " is edited.";
				LOGGER.log(Level.INFO,h );
			}
		} else {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again 2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				editHousing(housings);
			}
		}
	}

	public static void removeHousing(Housing[] housings) throws IOException {
		LOGGER.log(Level.INFO,"Enter Housing Number:");
		int housingNumber = SakancomApp.scanInt();
		if (housingNumber > housings.length) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again 2) Cancel");	
			if (SakancomApp.scanInt() == 1) {
				removeHousing(housings);
			}
			return;
		}
		LOGGER.log(Level.INFO,"1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (Boolean.TRUE.equals(!remove)) { return; }
		Housing housing = housings[housingNumber - 1];
		Owner owner = skankom.getOwner(housing.getOwnerId());
		if (owner != null) {
			owner.removeHousing(housing);
		}
		skankom.removeHousing(housing);
		String v=housing.toString() + " is deleted";
		LOGGER.log(Level.INFO,v);
	}
}