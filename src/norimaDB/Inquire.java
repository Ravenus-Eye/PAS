package norimaDB;

/**
 * @author DonAmbrocio
 * @Description : Norima Java Developer Course Capstone Project Instruction(Inquire Class)
 * This class is responsible for inquiring for the info needed for the database.
 * Created Date: 08/24/2022
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Inquire extends Search {

	// Initialize object used on multiple classes.
	PolicyHolder pHolder = new PolicyHolder();

	// Inquiring for first and last name to search account from the database.
	public void inquireWithName() throws SQLException {
		System.out.println("\n\n************************Search by Name*************************");
		System.out.println("Please enter the following information.\n");
		String firstName = pHolder.enterString("First Name\t\t : ", 20, 1);             //inquire for the customer's first name.
		String lastName  = pHolder.enterString("Last Name\t\t : " , 20, 2);             //inquire for the customer's last name.
		String accountNumber = searchAccount(firstName, lastName);                      //throwing first and last name to be searched from the database.
		if (accountNumber == null) {                                                    //display if the first and last name was not found.
			System.out.println("=====================================================================");
			System.out.println("|                          Account Details                          |");
			System.out.println("=====================================================================");
			System.out.format("%-10s%-20s%-20s%-20s", "Acct No.", "First Name", "Last Name", "Address");
			System.out.println("\n=====================================================================");
			System.out.println("\n No record found...");
			System.out.println("=====================================================================\n");
		} else {                                                                        //display if inquiry result is not null.
			searchAccountPolicyNumber(accountNumber, 1);
		}
	}

	// Inquiring for policy number to search account from the database. 
	public String inquireWithPolicyNumber() throws SQLException, ParseException {
		//inquiring method for policy number.
		String policyNumber  = inquirePolicy("\n\n******************Search by Policy Number*********************");
		if (policyNumber.equalsIgnoreCase("q")) {                                       //checking if user wants to quit from current transaction.
			System.out.println("\n You have exited search with policy number...\n");
		} else {                                                                        //if user wants to continue with current transaction. 
			String policyHolder = searchHolder(policyNumber, 1);                        //searching for the policy holder name.
			if (policyHolder == null) {                                                 //if there is no record found from the database.
				System.out.println("\n================================================================================"
						+ "==========================================================================================");
				System.out.println("|                                                                         Policy "
						+ "Summary                                                                                 |");
				System.out.println("================================================================================="
						+ "=========================================================================================");
				System.out.format("%-15s%-35s%-15s%-35s%-20s%-20s%-20s%-10s", "Acct No.", "Acct. Holder", "Policy No.", 
						"Policy Holder", "Policy Premium", "Effective Date", "Expiration Date", "Status");
				System.out.println("\n================================================================================"
						+ "==========================================================================================");	
				System.out.println("\n No record found...");
				System.out.println("=================================================================================="
						+ "========================================================================================\n");
				policyNumber = null;
			} else {                                                                    //if the record was found from  the database.
				double policyPremium = searchPremium(policyNumber);                     //searching for premium price from the database. 
				searchAccount(policyNumber, policyHolder, policyPremium);               //search and display policy details.
			}
		}
		return policyNumber;                                                            //return policy number value.
	}
	
	// Inquire method for policy number to search account from the database. 
	public String inquirePolicy(String header) throws SQLException {
		System.out.println(header);                                                     //display header for the inquiry.
		System.out.println("Enter 'q' if you want to quit.\n");                         //display for quit option.
		String policyNumber = pHolder.enterString
				("Please enter policy number (XXXXXX): ", 6, 5);                        //verification of input policy number.
		return policyNumber;                                                            //return policy number value.
	}
	
	// Inquire method for claim number to search account from the database. 
	public void inquireWithClaimNumber() throws SQLException, ParseException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);                                          //declaration of new Scan object.
		System.out.println                                                              //display  header for inquiry.
				("\n\n*******************Search by Claim Number*********************\n");
		boolean done = false;                                                           //declaration of boolean done for loop terminator condition.
		int claimNum = 0;                                                               //declaration of integer claim number.
		while (!done) {                                                                 //loop parameters until boolean done is true. 
			try {
				System.out.print("Please enter claim number you want to check (CXXXXX): ");
				String claimNumber = scan.nextLine();                                   //receives string input for claim number.
				if (claimNumber.charAt(0) != 'c' && claimNumber.charAt(0) != 'C') {     //checking if first character is not equal to letter C.
					System.out.println("Invalid input, please follow the format given (CXXXXX).");
				} else if (claimNumber.length() != 6) {                                 //checking if input has 6 characters. 
					System.out.println("Invalid input, please follow the format given (CXXXXX).");
				} else {                                                                //if all previous conditions were met.
					claimNumber  = claimNumber.substring(1);                            //removing the first character from the string.
					claimNum = Integer.valueOf(claimNumber);                            //converting string to integer.
					done = true;                                                        //changing boolean done to true, end of loop.
				}
			} catch (Exception e) {                                                     //error catcher.
				System.out.println(" Invalid input, please follow the format given (CXXXXX).");
			}
		}
		ResultSet rSet = select.selectClaim(claimNum);                                  //searching claim through claim number.
		System.out.println("\n************************Claim Details*************************\n");
		searchClaim(rSet);                                                              //display claim information. 
	}
}// End of Code.
