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
     //   HousingDataProvider dataProvider = new HousingDataProvider();

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
                	Enter1.tenant();                	
                case "Y":
                    System.out.println("Which house u want to see its details?");
                    System.out.print("Enter the House ID :");
                    Scanner scannn = new Scanner(System.in);
                    String s211 = scannn.nextLine();
                    HousingDataProvider.displayHouseDetails(s211);
                    System.out.println("Press T if u want to go back.");
                    Scanner scann = new Scanner(System.in);
                    String s111 = scann.nextLine();
                    if(s111.equals("T")) {
                    HousingDataProvider.backing("0");
                    }
                }
                V=true;
                break;
            case "2":       
            	HousingDataProvider dataProvider = new HousingDataProvider();
                System.out.println("Here are the houses with it's details");

            	HousingDataProvider.displayAvailableHousingUnits();
               Scanner scanner = new Scanner(System.in);
                 System.out.print("Enter the House ID u want to book: ");
                 String houseId = scanner.nextLine();
                 Housing housingUnit = HousingDataProvider.getHousingUnitById(houseId);
                 if (housingUnit != null) {
                     String availability = housingUnit.getAvailable();
                     // Book the house based on its availability
                     HousingDataProvider.bookHouse(houseId, availability);
                     if (HousingDataProvider.book() == true) {
                         HousingDataProvider.displayBookedHousesInfo();
                         // Save the booked house information
                         HousingDataProvider. addBookedHouseInfo(
                             User1.getuser(),
                             houseId,
                             housingUnit.getOwnerName(),
                             housingUnit.getOwnerNumber()
                         );
                     }
                  
              
                     }
                 else{       {
                     System.out.println("House with ID " + houseId + " does not exist.");
                 }
                 System.out.println("Press T if u want to go back.");
                 Scanner scann = new Scanner(System.in);
                 String t111 = scann.nextLine();
                 if(t111.equals("T")) {
                     HousingDataProvider.backing("0");
                 }
            	 
                 } 
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
