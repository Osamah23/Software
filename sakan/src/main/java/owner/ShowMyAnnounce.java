package owner;

import java.util.ArrayList;

public class ShowMyAnnounce {
	private static ShowMyAnnounce dbObject;
	public static ArrayList<Announce>my=new ArrayList<Announce>();
	public static Announce getTarget(int x) {
		return my.get(x);
	}
	private ShowMyAnnounce() {
		for(int i=0;i<ShowAnnounce.Announces.size();i++) {
			if(ShowAnnounce.Announces.get(i).getOwnerName()==User.getName())
				my.add(ShowAnnounce.Announces.get(i));
		}
	}
		public static ShowMyAnnounce getInstance() {

		      // create object if it's not already created
		      if(dbObject == null) {
		         dbObject = new ShowMyAnnounce();
		         }
		      return dbObject;
		      }
		
	}



