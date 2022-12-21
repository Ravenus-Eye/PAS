package norimaDB;

/**
 * @author DonAmbrocio
 * @Description : Norima Java Developer Course Capstone Project Instruction(Claim Object)
 * This class is responsible for creating the claim for the program.
 * Created Date: 08/24/2022
 */

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

public class Claim extends PolicyHolder {
	
	// Initialize the variables used on different methods.
	Select   select  = new Select();
	Scanner  scan    = new Scanner(System.in);
	private LocalDate accidentDate;
	private static LocalDate effectiveDate, expirationDate;
	private static String accidentLoc, accidentDesc, damageDesc;
	private double repairCost;
	
	// Getter for effectiveDate.
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	// Inquire for the claim information.
	public void inquireClaim() throws ParseException, SQLException{
		// Create new objects for the helping classes.
		Inquire  inquire = new Inquire();
		Insert   insert  = new Insert();
		Display  display = new Display();
		try {
			String policyNumber = inquire.inquireWithPolicyNumber();                                          //inquiring the policy number.
			if (policyNumber != null && !policyNumber.equalsIgnoreCase("q")) {                                //checking if policy is present on the database.
				System.out.println("\n**********************Claim Information***********************\n");     
				Boolean active = enterAccidentDate(policyNumber);                                             //checking if policy is active or not.
				if (active) {
					accidentLoc  = enterString("Address where the accident happened\t: ", 30, 3);             //inquire for accident location.
					accidentDesc = enterString("Description of the accident\t\t: "      , 50, 3);             //inquire for accident description.
					damageDesc   = enterString("Description of damage to vehicle\t: "   , 50, 3);             //inquire for vehicle damage.
					enterRepairCost();                                                                        //inquire for the repair cost.
					insert.insertClaim(policyNumber, accidentDate, accidentLoc,                               //inserting all the info gathered to claim table.
							accidentDesc, damageDesc, repairCost);
					ResultSet rset = select.selectClaimWithPolicyNumber();                                    //pulling the claim details from the claim table.
					System.out.println("\n************************Claim Created!************************\n"); 
					while (rset.next()) {                                                                     //looping for the result set from the sql query.
						display.display(rset.getString("claim_no"), policyNumber, accidentDate,               //display the info from the query.
								accidentLoc, accidentDesc, damageDesc, repairCost);
					}
			    	Conn.conn.close();                                                                        //close the connection from database.
					System.out.println("\n**************************************************************\n");
				}
			}
		} catch (Exception e) {
			System.out.print("");
		}
	}
	
	// Inquire for the repair cost.
	private void enterRepairCost() {
		boolean done = false;                                                                                 //initiate boolean variable.
		while (!done) {                                                                                       //loop to get the necessary input.
			try {
				System.out.print("Estimated cost of repairs\t\t: ");                                          //prompt for entering the repair cost.
				String strCost = scan.nextLine();	                                                          //string to receive the input.
				if (strCost.equals("")) {                                                                     //checking if the input is empty
					System.out.println("Empty entry is unaccepted.");                                         //error message once satisfies above condition.
				} else {
					Double rCost = Double.valueOf(strCost);
					if (rCost < 0) {                                                                          //repair cost can't be a negative number.
						System.out.println("Negative value is unaccepted.");                                  //error message once satisfies above condition.
					} else if (rCost == 0) {                                                                  //repair cost can't be zero.
						System.out.println("Zero is unaccepted.");                                            //error message once satisfies above condition.
					} else {                                                                                  // if all the above condition was not satisfied.
						this.repairCost = rCost;                                                              //setting the repair cost to the input value.
						done = true;                                                                          //setting the loop to done
					}
				}
			} catch (Exception e) {                                                                           //catching the program when there is any error in program.
				System.out.println("Invalid input.");                                                         //error message once any error from above was detected.
			}
		}
	}

	// Inquire for accident date.
	private boolean enterAccidentDate(String policyNum) 
			throws NumberFormatException, SQLException, ParseException {
		// Create new object for the helping class.
		PolicyHolder pHolder = new PolicyHolder();
		// initialize the variables needed for the method.
		String effDate = null, expDate = null;               
		boolean done = false;
		ResultSet rset  = select.selectPolicy(Integer.valueOf(policyNum));                                    //receives the data from the select query.
		while (rset.next()) {                                                                                 //loop while there is still a row from the set.
			effDate = rset.getString("effective_date");                                                       //transferring value of effective date.
			expDate = rset.getString("expiration_date");                                                      //transferring value of expiration date.
		}
    	Conn.conn.close();                                                                                    //close the connection from database.
		effectiveDate  = LocalDate.parse(effDate);                                                            //converting value from string to local date.
		expirationDate = LocalDate.parse(expDate);                                                            //converting value from string to local date.         
		if (expirationDate.compareTo(LocalDate.now()) < 0) {                                                  //comparing if expiration date is earlier than today's date.
			System.out.println(" You cannot use this policy anymore, it has already expired on "              //error message for the condition above. 
					+ expirationDate.getMonth() + " " + expirationDate.getDayOfMonth() + ", " 
					+ expirationDate.getYear() + ".\n");
		} else if (effectiveDate.compareTo(LocalDate.now()) <= 0) {                                           //comparing if effective date is today or earlier date.
			accidentDate = pHolder.enterDate("Date of accident(yyyy-MM-dd)\t\t: ", 4);                        //setting the value of accident date
			done = true;                                                                                      //setting the loop to done
		} else {                                                                                              //if the effective is inactive.
			System.out.println(" You cannot use an inactive policy yet, it will be available on "             //error message for the condition above. 
					+ effectiveDate.getMonth() + " " + effectiveDate.getDayOfMonth() + ", " 
					+ effectiveDate.getYear() + ".\n");
		}
		return done;                                                                                          //returns the value to check if the policy is active.
	}
}// End of Code.