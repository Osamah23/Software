package sakancomMain;

import java.io.IOException;
import java.util.*;
import owner.*;

public class ServiceManager {
	private static Skankom skankom = Skankom.getInstance();
	
	public static void manageServices(Announcement announcement) throws IOException {
		System.out.println("Choose one of the following options:\n1) Add Service \n2) Remove Service \n3) Show Services \n4) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			System.out.println("Enter Servcie:");
			String service = SakancomApp.getScanner().nextLine();
			announcement.addService(service);
			System.out.println("\"" + service + "\" service is added to " + announcement.toString());
			manageServices(announcement);
			break;
		case 2:
			String serviceInfo = deleteService(announcement);
			if (serviceInfo != null) {
				System.out.println("\"" + serviceInfo + "\" service is deleted from " + announcement.toString());
			}
			manageServices(announcement);
			break;
		case 3:
			showServices(announcement);
			manageServices(announcement);
			break;
		case 4:
			break;
		default:
			System.out.println("Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				manageServices(announcement);
			}
		}
	}

	public static void showServices(Announcement announcement) {
		if (announcement.getServices().isEmpty()) {
			System.out.println("No srevices added yet to " + announcement.toString());
			return;
		}
		System.out.println("Services in " + announcement.toString() + " are: [");
		for (String service: announcement.getServices()) {
			System.out.println(service);
		}
		System.out.println("]");
	}
	
	public static String removeService(List<String> list, Announcement announcement) throws IOException {
		System.out.println("Enter Service Number:");
		int serviceNumber = SakancomApp.scanInt();
		if (serviceNumber > list.size()) {
			System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				return removeService(list, announcement);
			}
			return null;
		}
		System.out.println("1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return null; }
		String service = list.get(serviceNumber - 1);
		String serviceInfo = service;
		announcement.removeService(service);
		return serviceInfo;
	}
	
	public static String deleteService(Announcement announcement) throws IOException {
		if (announcement.getServices().isEmpty()) {
			System.out.println("No services added yet to " + announcement.toString());
			return null;
		}
		int counter = 1;
		for (String service: announcement.getServices()) {
			System.out.println(counter + ") " + service);
			counter++;
		}
		return removeService(announcement.getServices(), announcement);
	}
}