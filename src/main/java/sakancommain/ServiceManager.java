package sakancommain;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import owner.*;

public class ServiceManager {
	private static Skankom skankom = Skankom.getInstance();
	private ServiceManager() {}
	private static final Logger LOGGER = Logger.getLogger(ServiceManager.class.getName());
	
	public static void manageServices(Announcement announcement) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Add Service \n2) Remove Service \n3) Show Services \n4) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			LOGGER.log(Level.INFO,"Enter Servcie:");
			String service = SakancomApp.getScanner().nextLine();
			announcement.addService(service);
			String j="\"" + service + "\" service is added to " + announcement.toString();
			LOGGER.log(Level.INFO,j);
			manageServices(announcement);
			break;
		case 2:
			String serviceInfo = deleteService(announcement);
			if (serviceInfo != null) {
				String i="\"" + serviceInfo + "\" service is deleted from " + announcement.toString();
				LOGGER.log(Level.INFO,i);
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
			LOGGER.log(Level.INFO,"Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				manageServices(announcement);
			}
		}
	}

	public static void showServices(Announcement announcement) {
		if (announcement.getServices().isEmpty()) {
			String h="No srevices added yet to " + announcement.toString();
			LOGGER.log(Level.INFO,h);
			return;
		}
		String l="Services in " + announcement.toString() + " are: [";
		LOGGER.log(Level.INFO,l);
		for (String service: announcement.getServices()) {
			LOGGER.log(Level.INFO,service);
		}
		LOGGER.log(Level.INFO,"]");
	}
	
	public static String removeService(List<String> list, Announcement announcement) throws IOException {
		LOGGER.log(Level.INFO,"Enter Service Number:");
		int serviceNumber = SakancomApp.scanInt();
		if (serviceNumber > list.size()) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				return removeService(list, announcement);
			}
			return null;
		}
		LOGGER.log(Level.INFO,"1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return null; }
		String service = list.get(serviceNumber - 1);
		String serviceInfo = service;
		announcement.removeService(service);
		return serviceInfo;
	}
	
	public static String deleteService(Announcement announcement) throws IOException {
		if (announcement.getServices().isEmpty()) {
			LOGGER.log(Level.INFO,"No services added yet to " + announcement.toString());
			return null;
		}
		int counter = 1;
		for (String service: announcement.getServices()) {
			LOGGER.log(Level.INFO,counter + ") " + service);
			counter++;
		}
		return removeService(announcement.getServices(), announcement);
	}
}