package sakancomMain;

import java.io.IOException;
import java.util.*;
import owner.*;

public class FurnitureManager {
	private static Skankom skankom = Skankom.getInstance();

	public static void manageFurniture(Tenant tenant) throws IOException {
		System.out.println("Choose one of the following options:\n1) Add Furniture \n2) Remove Furniture \n3) Show Furniture \n4) Return Back");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			addFurniture(tenant);
			manageFurniture(tenant);
			break;
		case 2:
			String furnitureInfo = deleteFurniture(tenant);
			if (furnitureInfo != null) {
				System.out.println("Furniture with Info: " + furnitureInfo + " is deleted from " + tenant.toString());
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
			System.out.println("Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				manageFurniture(tenant);
			}
		}
	}

	public static void addFurniture(Tenant tenant) throws IOException {
		System.out.println("Enter name:");
		String name = SakancomApp.getScanner().nextLine();
		System.out.println("Enter price:");
		double price = SakancomApp.scanDouble();
		System.out.println("Adding Apartment Confirmation:\n1) Confirm \n2) Cancel");
		int choice = SakancomApp.scanInt();
		if (choice == 1) {
			Furniture furniture = new Furniture(name, price, tenant.getId());
			tenant.addFurnitureForSale(furniture);
			skankom.addFurniture(furniture);
			System.out.println("Furniture with Info: " + furniture.toString() + " is added to " + tenant.toString());
		}
	}

	public static ArrayList<Furniture> showFurniture(Tenant tenant) {
		if (tenant.getListedFurniture().isEmpty()) {
			System.out.println("No furnitures added for sale yet");
			return null;
		}
		int counter = 1;
		ArrayList<Furniture> furnitures = new ArrayList<>();
		for (String furnitureId: tenant.getListedFurniture()) {
			Furniture furniture = skankom.getFurniture(furnitureId);
			if (furniture != null) {
				furnitures.add(furniture);
				System.out.println(counter + ") " + furniture.toString());
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
		System.out.println("Enter Furniture Number:");
		int furnitureNumber = SakancomApp.scanInt();
		if (furnitureNumber > furnitures.size()) {
			System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				return removeFurniture(furnitures, tenant);
			}
			return null;
		}
		System.out.println("1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return null; }
		Furniture furniture = furnitures.get(furnitureNumber - 1);
		String furnitureInfo = furniture.toString();
		tenant.removeListedFurniture(furniture);
		skankom.removeFurniture(furniture);
		return furnitureInfo;
	}
}