package sakancommain;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;

import javax.imageio.*;
import owner.*;

public class PhotoManager {
	private static Skankom skankom = Skankom.getInstance();
	private PhotoManager() {}
	 private static final Logger LOGGER = Logger.getLogger(PhotoManager.class.getName());
	public static void managePhotos(Announcement announcement) throws IOException {
		LOGGER.log(Level.INFO,"Choose one of the following options:\n1) Add Photo \n2) Remove Photo \n3) Show Photos \n4) Finish");
		int choice = SakancomApp.scanInt();
		switch (choice) {
		case 1:
			addPhoto(announcement);
			managePhotos(announcement);
			break;
		case 2:
			deletePhoto(announcement);
			managePhotos(announcement);
			break;
		case 3:
			showPhotos(announcement);
			managePhotos(announcement);
			break;
		case 4:
			break;
		default:
			LOGGER.log(Level.INFO,"Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				managePhotos(announcement);
			}
		}
	}
	
	public static void addPhoto(Announcement announcement) {
		LOGGER.log(Level.INFO,"Enter Photo URL:");
		String url = SakancomApp.getScanner().nextLine();
		try {
			announcement.addPhoto(url);
			LOGGER.log(Level.INFO,"Your photo added successfully");
		} 
		 catch (IOException e) {
			LOGGER.log(Level.INFO,"error in adding the photo \n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				addPhoto(announcement);
			}
		} 
	}

	public static void showPhotos(Announcement announcement) {
		List<BufferedImage> photos = announcement.getPhotos();
		if (photos.isEmpty()) {
			LOGGER.log(Level.INFO,"No photos added yet to " + announcement.toString());
			return;
		}
		PhotoShower photoShower = new PhotoShower();
		photoShower.paintAllPhotos(photos);
	}
	
	public static void removePhoto(int photosCount, Announcement announcement) throws IOException {
		LOGGER.log(Level.INFO,"Enter Photo Number:");
		int photoNumber = SakancomApp.scanInt();
		if (photoNumber > photosCount) {
			LOGGER.log(Level.INFO,"The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				removePhoto(photosCount, announcement);
			}
			return;
		}
		LOGGER.log(Level.INFO,"1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return; }
		announcement.removePhoto(photoNumber - 1);
		LOGGER.log(Level.INFO,"Your Photo deleted successfully");
	}
	
	public static void deletePhoto(Announcement announcement) throws IOException {
		int photosCount = announcement.getPhotosCount();
		if (photosCount == 0) {
			LOGGER.log(Level.INFO,"No photos added yet to " + announcement.toString());
			return;
		}
		LOGGER.log(Level.INFO,"There is " + photosCount + " photos in the announcement.");
		removePhoto(photosCount, announcement);
	}
}