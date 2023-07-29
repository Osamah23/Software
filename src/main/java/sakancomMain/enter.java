package sakancomMain;
import java.util.Scanner;

public class enter {

	 private static Scanner scanner;
	private static Scanner scanner2;

	public static void main(String[] args) {
	     String sentence = "WELCOME TO Sakan Application";
	     System.out.println(sentence);
	   
	     System.out.println("u want to sign-in or sign-up?"); 
	     System.out.println("Press (1) for sign-in & (2) for sign-up"); 
	     scanner = new Scanner(System.in);
          
	        int num11 = scanner.nextInt();
	        if (num11==1) {
	        	  Scanner scanner = new Scanner(System.in);

	              System.out.print("Enter your username: ");
	              String username = scanner.nextLine();

	              System.out.print("Enter your password: ");
	              String password = scanner.nextLine();
	       if(     User.fun(username, password)==true) {
	    	tenant();   
	       }   
	        }
	        
	        else if(num11==2) {
	        	 scanner2 = new Scanner(System.in);

	              System.out.print("Enter your username: ");
	              String username = scanner2.nextLine();

	              System.out.print("Enter your password: ");
	              String password = scanner2.nextLine();
	              System.out.print("Enter userType: ");
	              String type = scanner2.nextLine();

	        }
	        else {
	   	     System.out.println("Invalid"); 

	        }
	}
	public static void admin() {
		
	};
	public static void tenant() {
	   
		  System.out.println("1● view the available housing"); 
		  System.out.println("2●  book accommodation"); 
		  System.out.println("3● If the housing is a student");// general data can be found about the people who live in it,such as ages and university majors."); 
		  System.out.println("4● A special window for furniture through which the tenant can advertise his own used furniture for sale"); 
		  System.out.println("5● The presence of a tenant control panel after booking"); 
          System.out.print("which service u want?: ");

		   scanner = new Scanner(System.in);
		     int input = scanner.nextInt(); 
		     switch (input) {
		     case 1:
		     case 2:	
		     case 3:
		     case 4:
		     case 5:
		   
		     }
		
		
		
	};

	public static void owner() {
		
	};

	
}
