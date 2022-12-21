package norimaDB;

/**
 * @author DonAmbrocio
 * @Description : Norima Java Developer Course Capstone Project Instruction(Customer Account Object)
 * This class is responsible for creating new account for customers.
 * Created Date: 08/24/2022
 */

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerAccount extends PolicyHolder{

	// Initialize object variables used to multiple methods. 
	Scanner  scan  = new Scanner(System.in);
	Validate valid = new Validate();
	
	// Inquire for account info and creates the account for the customer.
	public void createAccount() throws SQLException {
		// Initialize object variables.
		Search search = new Search();
		Insert insert = new Insert();
		System.out.print("\n******************New Customer Information******************\n\n"); 
		firstName = enterString("First Name\t\t : ", 20, 1);                                     //inquire for the customer's first name.
		lastName  = enterString("Last Name\t\t : " , 20, 2);                                     //inquire for the customer's last name.
		address   = enterString("Address\t\t\t : " , 40, 3);                                     //inquire for the customer's address.
		String accountNumber = search.searchAccount(firstName,lastName);                         //checking if first and last name is unique from the account table.
		if (accountNumber == null) {                                                             //checking if first and last name is unique.
			insert.insertCustomer(firstName, lastName, address);                                 //insert the data to insert a new row to account table.
			System.out.println("\n*********************************************************************");
			System.out.println("*                          Account Created!                         *");
			System.out.println("*********************************************************************");
			accountNumber = search.searchAccount(firstName,lastName);                            //query to look for the newly created account number.
			search.searchAccountPolicyNumber(accountNumber, 1);                                  //query to look and display account and policy info.
			System.out.println("  Thank you for signing up to our service! ^_^\n");
		} else {                                                                                 //if the first and last name was already in use.
			search.searchAccountPolicyNumber(accountNumber, 1);                                  //query to look and display account and policy info.
			System.out.println("Name already exist. Please check if this is your account.");     //error message for the condition above.
			System.out.println("If not, please use another name.\n");
			String  option = "";                                                                 //set option to empty.
			while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {             //loop until either "y/Y/n/N" was entered.
				System.out.print("Do you want to continue creating a new account? (y/n): ");     //prompts user in he/she wants to continue creating account or not.
				option = scan.nextLine();                                                        //receives the input option. 
			}
			if (option.equalsIgnoreCase("y")) {                                                  //if option is "y" or "Y".
				createAccount();                                                                 //going back from the start in  creating the new account.
			} else {                                                                             //if option is "n" or "N".
				System.out.println("\n Account creation aborted...\n");                          //prompts user that the creation of account was aborted.
			}
		}
	}	
}// End of Code
