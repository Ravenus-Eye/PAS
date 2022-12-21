package norimaDB;

/**
 * @author DonAmbrocio
 * @Description : Norima Java Developer Course Capstone Project Instruction(Main Class)
 * This class is responsible in controlling the flow of the whole program.
 * Created Date: 08/24/2022
 */

import java.sql.SQLException;
import java.util.Scanner;

public class PASApp {

	// Main Controller of the program.
	public static void main(String[] args) throws SQLException {
		// Creation of new objects variables.
		@SuppressWarnings("resource")
		Scanner  scan        = new Scanner(System.in);
		Policy   policy      = new Policy();
		Inquire  inquire     = new Inquire();
		Claim    claim       = new Claim();
		Create   create      = new Create();
		CustomerAccount customer    = new CustomerAccount();
		String optString = null;                                                 //initialize string option.
		int option = 0;                                                          //initialize integer option.
		while(option != 8) {                                                     //loop until integer 8 was entered.
			// Creating all the tables needed for the system.
			create.createTableCustomer();
			create.createTablePolicy();
			create.createTableHolder();
			create.createTableVehicle();
			create.createTableClaim();
			try {                                                                //process while no error or option is not equal to 8.
				menuOption();                                                    //show system menu.
				optString = scan.nextLine();                                     //receive input option as string.
				String strOptionString = optString.replaceAll("\\p{Cntrl}", ""); //replacing all control button entered to empty.
				System.out.print("==============================================================\n");
				if(optString.length() == 1) {                                    //checking if there is only one character entered.
					option = Integer.valueOf(strOptionString);                   //converting string value to integer.
					if (option > 0 && option < 9 ) {                             //checking if input is included to the range (1-8).
						switch (option) {                                        //switch cases according to the number input.
							case 1:                                              //for case 1.
								customer.createAccount();                        //create customer account.
								break;
							case 2:                                              //for case 2.
								policy.createPolicy();                           //create policy for customer. 
								break;
							case 3:                                              //for case 3.
								policy.cancelPolicy();                           //cancel and delete the policy.
								break;
							case 4:                                              //for case 4.
								claim.inquireClaim();                            //create claim for customer.
								break;
							case 5:                                              //for case 5.
								inquire.inquireWithName();                       //search customer details using first and last name.
								break;
							case 6:                                              //for case 6.
								inquire.inquireWithPolicyNumber();               //search customer details using policy number.
								break;
							case 7:                                              //for case 7.
								inquire.inquireWithClaimNumber();                //search customer details using claim number.
								break;
							default:                                             //for default.
								break;
						}
					} else {                                                     //error message if number is not on the option.
						System.out.println("\nPlease choose from the options only.\n");  
					}
				} else {                                                         //error message if input is not a number.
					System.out.println("\nPlease choose from the options only.\n");
				}
			} catch (Exception e) {                                              //error message for any other reasons.
				System.out.print("\n==============================================================\n");
				System.out.println("\nPlease choose from the options only.\n");
			}
		}                                                                        
		System.out.println("\nExit session, program terminated...");             //notification that the program has been terminated.
	}
	
	public static void menuOption() {
		// PAS Main Menu.
		System.out.println("==============================================================");
		System.out.println("|Automobile Insurance Policy and Claims Administration System|");
		System.out.println("|                       Options Menu                         |");
		System.out.println("|                                                            |");
		System.out.println("| 1. Create a new Customer Account                           |");
		System.out.println("| 2. Get a policy quote and buy the policy.                  |");
		System.out.println("| 3. Cancel a specific policy                                |");
		System.out.println("| 4. File an accident claim against a policy                 |");
		System.out.println("| 5. Search for a Customer account                           |");
		System.out.println("| 6. Search for and display a specific policy                |");
		System.out.println("| 7. Search for and display a specific claim                 |");
		System.out.println("| 8. Exit the PAS System                                     |");
		System.out.print  ("| Please enter option here: ");		
	}
 
}// End of Program.
