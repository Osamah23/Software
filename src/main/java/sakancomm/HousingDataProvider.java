package sakancomm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HousingDataProvider {
    private static List<List<String>> availableHousingUnits;
    private static List<String> bookedHouses = new ArrayList<>();
	private static boolean back=false;
	private static boolean booked;

    // Constructor to initialize availableHousingUnits with sample data
    public HousingDataProvider() {
        availableHousingUnits = new ArrayList<>();
        List<List<String>> housingUnitData = Arrays.asList(
                Arrays.asList("A", "Albathan", "1000", "Wi-Fi, Parking, Gym", "https://example.com/house1/pic1", "yes", "3 rooms", "tasbeeh", "05111"),
                Arrays.asList("B", "Beita", "1000", "Washer/Dryer, Balcony, Pool", "https://example.com/house2/pic1", "yes", "2 rooms", "osamah", "054322"),
                Arrays.asList("C", "Nablus", "200", "Parking, Gym, Pet-friendly", "https://example.com/house3/pic1", "no", "1 room", "hayaa", "055322")
        );
        availableHousingUnits.addAll(housingUnitData);
    }

    // Methods to get data and details of available housing units
    public List<List<String>> getAvailableHousingUnits() {
        return availableHousingUnits;
    }

    public static void printHousingUnits() {
        for (List<String> housing : availableHousingUnits) {
            System.out.println("--------------------------");
            System.out.println("House ID: " + housing.get(0));
            System.out.println("Location: " + housing.get(1));
            System.out.println("Price: $" + housing.get(2));
            System.out.println("Pictures: " + housing.get(4));

            /*    System.out.println("Services: " + housing.get(3));
            System.out.println("Available: " + housing.get(5));
            System.out.println("Number Of Rooms: " + housing.get(6));
            System.out.println("Owner Name: " + housing.get(7));
            System.out.println("Owner Number: " + housing.get(8));
            System.out.println("--------------------------");*/
        }
    }

    public static boolean displayHouseDetails(String houseId) {
        Housing housingUnit = getHousingUnitById(houseId);
        if (housingUnit != null) {
            System.out.println("--------------------------");
            System.out.println("House ID: " + housingUnit.getHouseId());
            System.out.println("Location: " + housingUnit.getLocation());
            System.out.println("Price: $" + housingUnit.getPrice());
            System.out.println("Services: " + housingUnit.getServices());
            System.out.println("Pictures: " + housingUnit.getPictures());
            System.out.println("Available: " + housingUnit.getAvailable());
            System.out.println("Number Of Rooms: " + housingUnit.getNumberOfRooms());
            System.out.println("Owner Name: " + housingUnit.getOwnerName());
            System.out.println("Owner Number: " + housingUnit.getOwnerNumber());
            System.out.println("--------------------------");
            return true;
        } else {
            System.out.println("House ID not found. Please enter a valid House ID.");
            return false;
        }
    }

    public static Housing getHousingUnitById(String houseId) {
        for (List<String> housingUnit : availableHousingUnits) {
            if (housingUnit.get(0).equalsIgnoreCase(houseId)) {
                return new Housing(
                        housingUnit.get(0),
                        housingUnit.get(1),
                        Double.parseDouble(housingUnit.get(2)),
                        housingUnit.get(3),
                        housingUnit.get(4),
                        housingUnit.get(5),
                        housingUnit.get(6),
                        housingUnit.get(7),
                        housingUnit.get(8)
                );
            }
        }
        return null;
    }

    // Methods for booking a house
    public static void bookHouse(String houseId, String availability) {
        Housing housingUnit = getHousingUnitById(houseId);
        if (housingUnit != null) {
            if (availability.equalsIgnoreCase("yes")) {
                housingUnit.setAvailable("no");
                // bookedHouses.add("Username: " + User1.getUserName() + ", House ID: " + houseId);
                System.out.println("House with ID " + houseId + " has been successfully booked.");
                booked = true;
            } else if (availability.equalsIgnoreCase("no")) {
                System.out.println("House with ID " + houseId + " is not available for booking.");
                booked = false;
            } else {
                System.out.println("Invalid availability data for House with ID " + houseId);
                booked = false;
            }
        } else {
            System.out.println("House with ID " + houseId + " does not exist.");
            booked = false;
        }
    }


    // Method to display booked houses
    public static void displayBookedHouses() {
        System.out.println("Booked Houses:");
        for (String booking : bookedHouses) {
            System.out.println(booking);
        }
    }

    // Methods for navigating back to tenant page
    public static boolean isBackRequested(String input) {
        return input.equalsIgnoreCase("T");
    }

    public static void navigateToTenantPage() {
        Enter1.tenant();
    }

    // Method to update available housing units with new data
    public static void setAvailableHousingUnits(List<Map<String, String>> housingData) {
        if (availableHousingUnits == null) {
            availableHousingUnits = new ArrayList<>();
        } else {
            availableHousingUnits.clear();
        }

        for (Map<String, String> data : housingData) {
            String houseId = data.get("House ID");
            String location = data.get("Location");
            String price = data.get("Price");
            String services = data.get("Services");
            String pictures = data.get("Pictures");
            String available = data.get("Available");
            String numberOfRooms = data.get("Number of Rooms");
            String ownerName = data.get("Owner Name");
            String ownerNumber = data.get("Owner Number");

            availableHousingUnits.add(Arrays.asList(
                    houseId, location, price, services, pictures, available, numberOfRooms, ownerName, ownerNumber
            ));
        }
    }
    public static boolean book(){
    	
		return booked;
    	
    }
    public static boolean backing(String string) {
        if (string.equals("0")) { // Use equals() for string comparison
            Enter1.tenant();
            back = true;
        }
        return back;
    }
	}

