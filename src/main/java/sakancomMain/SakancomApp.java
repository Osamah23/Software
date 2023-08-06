package sakancomMain;

import java.util.Scanner;
import java.io.*;
import owner.*;

public class SakancomApp {
	private static Skankom skankom = Skankom.getInstance();

	public static void main(String[] args) throws IOException {
		String sentence = "WELCOME TO Sakan Application";
		System.out.println(sentence);
		entrance();
	}

	public static Scanner getScanner() {
		return new Scanner(System.in);
	}

	public static int scanInt() {
		Scanner scanner = getScanner();
		while (!scanner.hasNextInt()) {
			scanner.next();
			System.out.print("Invalid integer input. Please enter an integer: ");
		}
		int number = scanner.nextInt();
		return number;
	}

	public static double scanDouble() {
		Scanner scanner = getScanner();

		while (!scanner.hasNextDouble()) {
			scanner.next();
			System.out.print("Invalid double input. Please enter a double: ");
		}
		double number = scanner.nextDouble();
		return number;
	}

	public static void entrance() throws IOException {
		System.out.println("1) Sign In\n2) Sign Up\n3) Quit");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1: 
			signIn();
			break;
		case 2: 
			signUp();
			break;
		case 3:
			break;
		default:
			entrance();
		}
	}

	public static void signIn() throws IOException {
		System.out.println("Enter userName:");
		String userName = SakancomApp.getScanner().nextLine();
		System.out.println("Enter password:");
		String password = SakancomApp.getScanner().nextLine();
		User user = skankom.getUser(userName);
		if (user == null) {
			System.out.println("There is no matched user name in the system."); 
			entrance();
		} else if (user.getPassword().equals(password)) {
			classifyUser(user);
		} else {
			System.out.println("Wrong password"); 
			entrance();
		}
	}

	public static void signUp() throws IOException {
		System.out.println("Enter userName:");
		String userName = SakancomApp.getScanner().nextLine();
		System.out.println("Enter password:");
		String password = SakancomApp.getScanner().nextLine();
		UserType userType = null;
		while (userType == null) {
			System.out.println("Choose userType:\n1) Admin 2) Tenant 3) Owner");
			int userTypeNumber = SakancomApp.scanInt();
			userType = getUserType(userTypeNumber); 
		}
		User user = new User(userName, password, userType);
		createSuitableUser(user);
	}

	public static UserType getUserType(int userTypeInt) {
		switch (userTypeInt) {
		case 1:
			return UserType.ADMIN;
		case 3:
			return UserType.OWNER;
		case 2:
			return UserType.TENANT;
		default:
			return null;
		}
	}

	public static void createSuitableUser(User user) throws IOException {
		switch (user.getUserType()) {
		case ADMIN:
			AdminManager.createAdmin(user);
			break;
		case OWNER:
			OwnerManager.createOwner(user);
			break;
		case TENANT:
			TenantManager.createTenant(user);
			break;
		default:
			System.out.println("User type" + user.getUserType() + " does not exist.");
			entrance();
		}
	}

	public static void classifyUser(User user) throws IOException {
		switch (user.getUserType()) {
		case ADMIN:
			Admin admin = skankom.getAdmin(user.getUserId());
			if (admin != null) {
				AdminManager.enterAsAdmin(admin);
			} else {
				System.out.println("Cannot find an admin with userName: " + user.getUserName());
			}
			break;
		case OWNER:
			Owner owner = skankom.getOwner(user.getUserId());
			if (owner != null) {
				OwnerManager.enterAsOwner(owner);
			} else {
				System.out.println("Cannot find an owner with userName: " + user.getUserName());
			}
			break;
		case TENANT:
			Tenant tenant = skankom.getTenant(user.getUserId());
			if (tenant != null) {
				TenantManager.enterAsTenant(tenant);
			} else {
				System.out.println("Cannot find a tenant with userName: " + user.getUserName());
			}
			break;
		default:
			System.out.println("User type" + user.getUserType() + " does not exist.");
			entrance();
		}
	}
}
