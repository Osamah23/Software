package owner;

import java.util.ArrayList;

public class ShowAnnounce extends javax.swing.JFrame {
	private static ShowAnnounce dbObject;

	private ShowAnnounce() {}
	public static ShowAnnounce getInstance() {

	      // create object if it's not already created
	      if(dbObject == null) {
	         dbObject = new ShowAnnounce();
	         }
	      return dbObject;
	      }
	public static ArrayList <Announce> Announces=new ArrayList<Announce>();
                                            
}