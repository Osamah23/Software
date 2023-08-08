package sakancommain;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import owner.*;

public class SakancomApp {
	private static Skankom skankom = Skankom.getInstance();
	private static final Logger LOGGER = Logger.getLogger(SakancomApp.class.getName());
	public static void main(String[] args) throws IOException {
		String sentence = "WELCOME TO Sakan Application";
		LOGGER.log(Level.INFO,sentence);
		entrance();
	}

	public static Scanner getScanner() {
		return new Scanner(System.in);
	}

	public static int scanInt() {
		Scanner scanner = getScanner();
		while (!scanner.hasNextInt()) {
			scanner.next();
			LOGGER.log(Level.INFO,"Invalid integer input. Please enter an integer: ");
		}
		
		return scanner.nextInt();
	}

	public static double scanDouble() {
		Scanner scanner = getScanner();

		while (!scanner.hasNextDouble()) {
			scanner.next();
			LOGGER.log(Level.INFO,"Invalid double input. Please enter a double: ");
		}
		return scanner.nextDouble();
	}

	public static void entrance() throws IOException {
		LOGGER.log(Level.INFO,"1) Sign In\n2) Sign Up\n3) Quit");
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
		LOGGER.log(Level.INFO,"Enter userName:");
		String userName = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter password:");
		String password = SakancomApp.getScanner().nextLine();
		User user = skankom.getUser(userName);
		if (user == null) {
			LOGGER.log(Level.INFO,"There is no matched user name in the system."); 
			entrance();
		} else if (user.getPassword().equals(password)) {
			classifyUser(user);
		} else {
			LOGGER.log(Level.INFO,"Wrong password"); 
			entrance();
		}
	}

	public static void signUp() throws IOException {
		LOGGER.log(Level.INFO,"Enter userName:");
		String userName = SakancomApp.getScanner().nextLine();
		LOGGER.log(Level.INFO,"Enter password:");
		String password = SakancomApp.getScanner().nextLine();
		UserType userType = null;
		while (userType == null) {
			LOGGER.log(Level.INFO,"Choose userType:\n1) Admin 2) Tenant 3) Owner");
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
			String t="User type" + user.getUserType() + " does not exist.";
			LOGGER.log(Level.INFO,t);
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
				LOGGER.log(Level.INFO,"Cannot find an admin with userName: " + user.getUserName());
			}
			break;
		case OWNER:
			Owner owner = skankom.getOwner(user.getUserId());
			if (owner != null) {
				OwnerManager.enterAsOwner(owner);
			} else {
				LOGGER.log(Level.INFO,"Cannot find an owner with userName: " + user.getUserName());
			}
			break;
		case TENANT:
			Tenant tenant = skankom.getTenant(user.getUserId());
			if (tenant != null) {
				TenantManager.enterAsTenant(tenant);
			} else {
				LOGGER.log(Level.INFO,"Cannot find a tenant with userName: " + user.getUserName());
			}
			break;
		default:
			LOGGER.log(Level.INFO,"User type" + user.getUserType() + " does not exist.");
			entrance();
		}
	}
}
