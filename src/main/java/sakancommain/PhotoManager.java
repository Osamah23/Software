package sakancommain;

import java.io.*;
import java.util.List;
import java.awt.image.BufferedImage;

import javax.imageio.*;
import owner.*;

public class PhotoManager {
	private static Skankom skankom = Skankom.getInstance();
	private PhotoManager() {}

	public static void managePhotos(Announcement announcement) throws IOException {
		System.out.println("Choose one of the following options:\n1) Add Photo \n2) Remove Photo \n3) Show Photos \n4) Finish");
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
			System.out.println("Wrong choice.\n1) Try Again 2) Finish");
			if (SakancomApp.scanInt() == 1) {
				managePhotos(announcement);
			}
		}
	}
	
	public static void addPhoto(Announcement announcement) {
		System.out.println("Enter Photo URL:");
		String url = SakancomApp.getScanner().nextLine();
		try {
			BufferedImage source = ImageIO.read(new FileInputStream((url)));
			announcement.addPhoto(url);
			System.out.println("Your photo added successfully");
		} 
		 catch (IOException e) {
			System.out.println("error in adding the photo \n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				addPhoto(announcement);
			}
		} 
	}

	public static void showPhotos(Announcement announcement) {
		List<BufferedImage> photos = announcement.getPhotos();
		if (photos.isEmpty()) {
			System.out.println("No photos added yet to " + announcement.toString());
			return;
		}
		PhotoShower photoShower = new PhotoShower();
		photoShower.paintAllPhotos(photos);
	}
	
	public static void removePhoto(int photosCount, Announcement announcement) throws IOException {
		System.out.println("Enter Photo Number:");
		int photoNumber = SakancomApp.scanInt();
		if (photoNumber > photosCount) {
			System.out.println("The entered number is not exist.\n1) Try Again \n2) Cancel");
			if (SakancomApp.scanInt() == 1) {
				removePhoto(photosCount, announcement);
			}
			return;
		}
		System.out.println("1) Remove \n2) Cancel");
		Boolean remove = SakancomApp.scanInt() == 1;
		if (!remove) { return; }
		announcement.removePhoto(photoNumber - 1);
		System.out.println("Your Photo deleted successfully");
	}
	
	public static void deletePhoto(Announcement announcement) throws IOException {
		int photosCount = announcement.getPhotosCount();
		if (photosCount == 0) {
			System.out.println("No photos added yet to " + announcement.toString());
			return;
		}
		System.out.println("There is " + photosCount + " photos in the announcement.");
		removePhoto(photosCount, announcement);
	}
}