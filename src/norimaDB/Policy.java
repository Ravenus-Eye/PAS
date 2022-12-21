package norimaDB;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;


public class Policy extends Search {
	// Initiation of class variables.
	Scanner scan    = new Scanner(System.in);
	Inquire inquire = new Inquire();
	private LocalDate effectiveDate, cancelDate;
	private static LocalDate currExpiryDate;
	
	// Class to return current expiry date.
	public LocalDate getExpiryDate() {
		return currExpiryDate;
	}

	// Create Policy Option.
	public void createPolicy() throws SQLException, ParseException {
		// Declaration of method variables.
		PolicyHolder pHolder    = new PolicyHolder();
		Vehicle      vehicle    = new Vehicle();
		Insert       insert     = new Insert();
		// beginning action prompt.
		System.out.println("\n******************Search by Account Number********************");
		System.out.println("Enter 'q' if you want to quit.\n");
		String accountNumber = pHolder.enterString("Please enter customer's account number(XXXX): ", 4, 5);
		String policyNumber = ""; 
		if (accountNumber.equalsIgnoreCase("q")){
			System.out.println("\n Left policy creation...\n");
		} else {
			try {
				int checker = searchAccount(accountNumber);
				if (checker > 0) {
					String option = "";
					String policyHolder = null;
					while (!option.equalsIgnoreCase("Y") && !option.equalsIgnoreCase("N")) {
						System.out.print("Do you have a pre-existing policy you want to use?(Y/N) : ");
						String strOption = scan.nextLine();
						option = strOption.replaceAll("[\\p{Cntrl}^\r\n\t]", "");
					}
					if (option.equalsIgnoreCase("Y")) {
						if(searchAccountPolicyNumber(accountNumber, 1) == null) {
							System.out.println("The account you have selected does not have any policy yet.");
							createPolicy();
						} else {
							while (policyHolder == null && !policyNumber.equalsIgnoreCase("q")) {
								policyNumber = inquire.inquirePolicy("\n\n********************Search Policy Holder**********************\n");
								policyHolder = searchHolder(accountNumber, policyNumber);
								if (policyHolder == null) {
									System.out.println("\n  Policy not found from the list.");
								} else if (policyNumber.equalsIgnoreCase("q")) {
									System.out.println("  Policy creation aborted...\n");
								} else {
									double policyPremium = vehicle.inquireVehicle(policyNumber);
									searchAccount(policyNumber, policyHolder, policyPremium);
								}
							}
						}
					} else if (option.equalsIgnoreCase("N")) {
						System.out.print("\n**********************New Policy*********************\n\n");
						effectiveDate = pHolder.enterDate("Effective Date(yyyy-MM-dd): ", 1);
						insert.insertPolicy(accountNumber, effectiveDate);
						policyNumber = searchAccountPolicyNumber(accountNumber, 2);
						policyHolder = pHolder.inquirePolicyHolder(policyNumber);
						double policyPremium = vehicle.inquireVehicle(policyNumber);
						searchAccount(policyNumber, policyHolder, policyPremium);
					}
				} else {
					System.out.println("\n  Account number not found.\n");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("\nPolicy creation aborted111...\n");
			}
		}
	}
	
	public void deletePolicy(String accountNumber, String policyNumber, boolean doneHolder) throws SQLException {
		Conn.prepareStatement("DELETE FROM policy_list\r\n"
				+ "WHERE account_no='" + accountNumber + "'");
		if (doneHolder) {
			Conn.prepareStatement("DELETE FROM policy_holder\r\n"
					+ "WHERE policy_no='" + policyNumber + "'");
		}
	}	
		
	public void cancelPolicy() throws SQLException, ParseException {
		PolicyHolder pHolder = new PolicyHolder();
		Select select = new Select();
		String dateExpire = null;
		try {
	        String policyNumber = inquire.inquireWithPolicyNumber();
	        if (!policyNumber.equalsIgnoreCase("q")) {
				ResultSet rset  = select.selectPolicy(Integer.valueOf(policyNumber));
				while (rset.next()) {
					dateExpire = rset.getString("expiration_date");
				}
		    	Conn.conn.close();
				currExpiryDate = LocalDate.parse(dateExpire);
				if (currExpiryDate.compareTo(LocalDate.now()) > 0) {
					cancelDate = pHolder.enterDate("Please enter the cancelation date (yyyy-MM-dd): ", 5);
					System.out.println("***********************Warning***********************");
					String option = "";
					while (!option.equalsIgnoreCase("y")&&!option.equalsIgnoreCase("n")) {
						System.out.print("Are you sure you want to proceed with the changes?(y/n) : ");
						option = scan.nextLine();
					}
					if (option.equalsIgnoreCase("y")) {
						Conn.prepareStatement("UPDATE policy_list SET expiration_date='" + cancelDate
								+ "' WHERE policy_no=" + policyNumber);
						System.out.println("\n Expiration date changed to: " + cancelDate.getMonth() + " " 
								+ cancelDate.getDayOfMonth() + ", " + cancelDate.getYear() + ".\n");
					} else {
						System.out.println("\n No changes done on the policy.\n");
					}
				} else {
					System.out.println(" Cannot process cancelation, policy already expired or will expire today.\n");
				}
	        }
		} catch (Exception e) {
			System.out.println("");
		}
	}
}