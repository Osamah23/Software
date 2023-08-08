package sakancommain;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class FurnitureManager {
	private static Skankom skankom = Skankom.getInstance();
	private FurnitureManager() {}
	private static final Logger LOGGER = Logger.getLogger(FurnitureManager.class.getName());

	public static void manageFurniture(Tenant tenant) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Add Furniture \n2) Remove Furniture \n3) Show Furniture \n4) Return Back");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			addFurniture(tenant);
			manageFurniture(tenant);
			break;
		case 2:
			String furnitureInfo = deleteFurniture(tenant);
			if (furnitureInfo != null) {
				String h="Furniture with Info: " + furnitureInfo + " is deleted from " + tenant.toString();
				LOGGER.log(Level.INFO,h);
			}
			manageFurniture(tenant);
			break;
		case 3:
			showFurniture(tenant);
			manageFurniture(tenant);
			break;
		case 4:
			break;
		default:
			LOGGER.log(Level.INFO,"Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				manageFurniture(tenant);
			}
		}
	}

	public static void addFurniture(Tenant tenant) throws IOException {
		LOGGER.log(Level.INFO,"Enter name:");
		String name = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter price:");
		double price = SakancomApp.scanDouble();
		LOGGER.log(Level.INFO,"Adding Apartment Confirmation:\n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			Furniture furniture = new Furniture(name, price, tenant.getId());
			tenant.addFurnitureForSale(furniture);
			skankom.addFurniture(furniture);
			String b="Furniture with Info: " + furniture.toString() + " is added to " + tenant.toString();
			LOGGER.log(Level.INFO,b);
		}
	}

	public static ArrayList<Furniture> showFurniture(Tenant tenant) {
		if (tenant.getListedFurniture().isEmpty()) {
			LOGGER.log(Level.INFO,"No furnitures added for sale yet");
			return null;
		}
		int counter = 1;
		ArrayList<Furniture> furnitures = new ArrayList<>();
		for (String furnitureId: tenant.getListedFurniture()) {
			Furniture furniture = skankom.getFurniture(furnitureId);
			if (furniture != null) {
				furnitures.add(furniture);
				LOGGER.log(Level.INFO,counter + ") " + furniture.toString());
				counter++;
			}
		}
		return furnitures;
	}

	public static String deleteFurniture(Tenant tenant) throws IOException {
		ArrayList<Furniture> tenants = showFurniture(tenant);
		if (tenants == null) { return null; }
		return removeFurniture(tenants, tenant);
	}

	public static String removeFurniture(ArrayList<Furniture> furnitures, Tenant tenant) throws IOException {
		LOGGER.log(Level.INFO,"Enter Furniture Number:");
		int furnitureNumber = SakancomApp.scanInt();
		if (furnitureNumber > furnitures.size()) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				return removeFurniture(furnitures, tenant);
			}
			return null;
		}
		LOGGER.log(Level.INFO,"1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return null; }
		Furniture furniture = furnitures.get(furnitureNumber - 1);
		String furnitureInfo = furniture.toString();
		tenant.removeListedFurniture(furniture);
		skankom.removeFurniture(furniture);
		return furnitureInfo;
	}
}