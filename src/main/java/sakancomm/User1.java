package sakancomm;

import java.io.File;
import java.util.ArrayList;

    public class User1 {

        public static boolean t = false;
        public static boolean o = false;
        public static boolean d = false;
        private String userName;
        private String password;
        private String userType;

        public User1(String userName, String password, String userType) {
            this.userName = userName;
            this.password = password;
            this.userType = userType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public static boolean fun(String name, String pass) {
            ArrayList<User1> userList = new ArrayList<>();
            userList.add(new User1("tasbeehh", "0444", "1"));
            userList.add(new User1("shahad", "789", "1"));
            userList.add(new User1("haya", "22300", "3"));
            userList.add(new User1("osamahh", "0599", "2"));

            for (User1 user : userList) {
                System.out.println("User Name: " + user.getUserName());
                System.out.println("Password: " + user.getPassword());
                System.out.println("User Type: " + user.getUserType());
                System.out.println("--------------------------");

                if (name.equals(user.getUserName()) && pass.equals(user.getPassword())) {
                    if (user.getUserType().equals("1")) {
                        t = true;
                        return true;
                    } else if (user.getUserType().equals("2")) {
                        o = true;
                        return true;
                    } else if (user.getUserType().equals("3")) {
                        d = true;
                        return true;
                    }
                }
            }
            return false;
        }
    }

