package sakancomm;

import java.util.Scanner;
import sakancomm.SignUpSer;
import sakancomm.User1;
import sakancomm.HousingDataProvider;

public class Enter1 {
    private static Scanner scanner;
    private static Scanner scanner2;
    private static Scanner scanner3;
static boolean V=false;
static boolean B=false;

    public static void main(String[] args) {
        HousingDataProvider housingDataProvider = new HousingDataProvider();
        SignUpSer signUpSer = new SignUpSer();

        String sentence = "WELCOME TO Sakan Application";
        System.out.println(sentence);
        System.out.println("Do you want to sign-in or sign-up?");
        System.out.println("Press (1) for sign-in & (2) for sign-up");
        scanner = new Scanner(System.in);
        int num11 = scanner.nextInt();
        if (num11 == 1) {
            scanner3 = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = scanner3.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner3.nextLine();
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            boolean loginResult = User1.fun(username, password);
            System.out.println("Login result: " + loginResult);

            if (loginResult) {
                if (User1.t) {
                    tenant();
                } else if (User1.o) {
                    owner();
                } else if (User1.d) {
                    admin();
                }
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

     

        else if (num11 == 2) {
            scanner2 = new Scanner(System.in);
            System.out.println("Enter your username: ");
            String username = scanner2.nextLine();

            System.out.println("Enter your password: ");
            String password = scanner2.nextLine();

            System.out.println("Enter userType: ");
            System.out.println("1.TENANT ");
            System.out.println("2.OWNER ");
            String type = scanner2.nextLine();

            if (User1.fun(username, password) == true) {
                System.out.println("Pre-existed");
            } else if (User1.fun(username, password) == false) {
                boolean signUpResult = signUpSer.signUp(username, password, type);

                if (signUpResult) {
                    System.out.println("User registration successful!");
                    if (type.equals("1")) {
                        tenant();
                    } else if (type.equals("2")) {
                        owner();
                    } else {
                        System.out.println("An Error happened. Please try again");
                    }
                } else {
                    System.out.println("User registration failed. The username may already be taken.");
                }
            }
        } else {
            System.out.println("Invalid");
        }
    }

    public static void admin() {
    }
    public static boolean te( String s21) {
        switch (s21)
        {
        case "1":
return V;
	case "2":
          return  true;
   	case "3":
        return  true;
        }
		return false;
    	
   }
    public static void tenant() {
        System.out.println("1● View available housing");
        System.out.println("2● Book accommodation");
        System.out.println("3● A special window for furniture through which you can advertise your own used furniture for sale");
        System.out.print("Which service do you want?: ");

        Scanner s = new Scanner(System.in);
        String s21 = s.nextLine();
        switch (s21) {
            case "1":
                HousingDataProvider.printHousingUnits();
                System.out.println("Press Y if u want to see the details.");
                System.out.println("Press T if u want to go back.");
                Scanner s1 = new Scanner(System.in);
                String s11 = s1.nextLine();
                switch(s11) {
                
                case "T":
                	
                	
                	
                	
                	
                	
                case "Y":
                
                }
                V=true;
            case "2":
            	B=true;
              //  HousingDataProvider.printd();
                break;
            case "3":
                // Your furniture advertisement functionality here
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void owner() {
    }
}
