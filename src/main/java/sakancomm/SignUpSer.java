package sakancomm;
import java.util.ArrayList;
import java.util.List;

public class SignUpSer {
    private static List<UserData> users; // ArrayList to store user data
    
    public SignUpSer() {
        users = new ArrayList<>();
        users.add(new UserData("tasbeehh", "044", "1"));
        users.add(new UserData("osamahh", "0599", "2"));
        users.add(new UserData("haya", "22300", "3"));
    }

    public static boolean signUp(String username, String password, String userType) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false; 
        }

        for (UserData userData : users) {
            if (userData.getUsername().equals(username)) {
                return false; // sign-up failed
            }
        }

        users.add(new UserData(username, password, userType));
        return true; // Sign-up successful
    }

    public static boolean isUserRegistered(String username) {
        try {
			for (UserData userData : users) {
			    if (userData.getUsername().equals(username)) {
			        return true;
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }

    private static class UserData {
        private String u;
        private String password;
        private String ut;

        public UserData(String un, String password, String Ty) {
            this.u = un;
            this.password = password;
            this.setUt(Ty);
        }

        public String getUsername() {
            return u;
        }

		public String getUt() {
			return ut;
		}

		public void setUt(String ut) {
			this.ut = ut;
		}
    }
	
   
   

}
