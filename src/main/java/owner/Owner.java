package owner;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import sakancomMain.Skankom;

public class Owner extends Person implements Serializable {
	private String id;
    private ArrayList<String> ownedHousing;

    public Owner(String email, String phoneNumber) throws IOException {
    	this.id = UUID.randomUUID().toString();
        this.ownedHousing = new ArrayList<>();
        super.setEmail(email);
        super.setPhoneNumber(phoneNumber);
    }
    
    public String getId() {
    	return id;
    }
    
    public void addHousing(Housing housing) throws IOException {
        ownedHousing.add(housing.getId());
        save1();
    }
    
    public List<String> getOwnedHosuings() {
    	return ownedHousing;
    }
    
    public void removeHousing(Housing housing) throws IOException {
    	ownedHousing.remove(housing);
    	save1();
    }
    private static final Logger LOGGER = Logger.getLogger(Owner.class.getName());
    public void viewOwnedHousing() {
        if (ownedHousing.isEmpty()) {
        	LOGGER.log(Level.INFO,"You don't have any owned housing yet.");
        } else {
        	LOGGER.log(Level.INFO,"Your owned housing:");
            for (String housingId : ownedHousing) {
            	Housing housing = Skankom.getInstance().getHousing(housingId);
            	String m=housing.getLocation();
            	LOGGER.log(Level.INFO,m);
            }
        }
    }

    @Override
    public String toString() {
        return "Owner Name: " + super.getName() + ", " + super.toString();
    }
    
    private void save1() throws IOException {
		Skankom.getInstance().writeToFile();
	}
}
