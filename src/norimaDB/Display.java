package norimaDB;

/**
 * @author DonAmbrocio
 * @Description : Norima Java Developer Course Capstone Project Instruction(Display Class)
 * This class is responsible for displaying the data taken from the sql query.
 * Created Date: 08/24/2022
 */

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Display {
	
	// Initialize the number format use to display monetary values.
	NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

	// Display for account details taken from sql query.
	protected void display(String accountNumber, String fName, String lName, String address) {
		//displaying output in a table.
		System.out.println("=====================================================================");
		System.out.println("|                          Account Details                          |");
		System.out.println("=====================================================================");
		System.out.format("%-10s%-20s%-20s%-20s", "Acct No.", "First Name", "Last Name", "Address");
		System.out.println("\n=====================================================================");
		System.out.format("%-10s%-20s%-20s%-20s", accountNumber, fName.toUpperCase(), lName.toUpperCase(), address.toUpperCase());
		System.out.println("\n=====================================================================\n");
	}


	// Display for policy details taken from sql query.
	protected void display(String accountNum, String accountHolder, String policyNumber, String policyHolder, 
			Double policyPremium, LocalDate effectiveDate, LocalDate expirationDate) {
		String  status = "ACTIVE";
		if(effectiveDate.compareTo(LocalDate.now()) > 0) {
			    status = "INACTIVE";
		} else if (expirationDate.compareTo(LocalDate.now()) < 0) {
			    status = "EXPIRED";
		}
		//converting local date to string.
		String effDate = effectiveDate.getMonth()  + " " + String.format("%02d", effectiveDate.getDayOfMonth())  + "," + effectiveDate.getYear(),
			   expDate = expirationDate.getMonth() + " " + String.format("%02d", expirationDate.getDayOfMonth()) + "," + expirationDate.getYear();
		//displaying output in a table.
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
		System.out.format("%-15s%-35s%-15s%-35s%-20s%-20s%-20s%-10s", accountNum, accountHolder.toUpperCase(), 
				policyNumber, policyHolder.toUpperCase(), formatter.format(policyPremium), effDate, expDate, status);
		System.out.print("\n");
		System.out.println("=================================================================================="
				+ "========================================================================================\n");
	}

	// Display for policy details taken from sql query.
	protected void displayPolicy(String policyNumber, String policyHolder, 
			LocalDate effectiveDate, LocalDate expirationDate) {
		String  status = "ACTIVE";
		if(effectiveDate.compareTo(LocalDate.now()) > 0) {
			    status = "INACTIVE";
		} else if (expirationDate.compareTo(LocalDate.now()) < 0) {
			    status = "EXPIRED";
		}
		//converting local date to string.
		String effDate = effectiveDate.getMonth()  + " " + String.format("%02d", effectiveDate.getDayOfMonth())  + "," + effectiveDate.getYear(),
			   expDate = expirationDate.getMonth() + " " + String.format("%02d", expirationDate.getDayOfMonth()) + "," + expirationDate.getYear();
		System.out.format("%-15s%-40s%20s%20s%-10s", policyNumber, policyHolder, effDate + "  ", expDate + "  ", status);
		System.out.print("\n");
	}
	
	// Display for policy holder details taken from sql query.
	protected void display(String holderNum, String address, LocalDate birthDate, 
			String licenseNum, LocalDate licenseExpiryDate) {
		//converting local date to string.
		String bDate = birthDate.getMonth() + " " + String.format("%02d", birthDate.getDayOfMonth()) + "," + birthDate.getYear(),
			   lDate = licenseExpiryDate.getMonth() + " " + String.format("%02d", licenseExpiryDate.getDayOfMonth()) + "," + licenseExpiryDate.getYear();
		//displaying output in bullets.
		System.out.println("\n************************Holder Details************************\n");
		System.out.println("\t• Holder ID\t\t: "    + holderNum);
		System.out.println("\t• Address\t\t: "      + address.toUpperCase());
		System.out.println("\t• Birth Date\t\t: "   + bDate);
		System.out.println("\t• License Number\t: " + licenseNum.toUpperCase());
		System.out.println("\t• License Expiry\t: " + lDate);
	}

	// Display for vehicle details taken from sql query.
	protected void display(String vehicleNum, String carBrand, String carModel, String carType, 
			String carColor, String fuelType, int yearBought, double purchasePrice, double premium) {
		//displaying output in bullets.
		System.out.println("\t• Vehicle ID\t\t: "   + vehicleNum);
		System.out.println("\t• Car Brand\t\t: "    + carBrand.toUpperCase());
		System.out.println("\t• Car Model\t\t: "    + carModel.toUpperCase());
		System.out.println("\t• Car Type\t\t: "     + carType.toUpperCase());
		System.out.println("\t• Car Color\t\t: "    + carColor.toUpperCase());
		System.out.println("\t• Fuel Type\t\t: "    + fuelType.toUpperCase());
		System.out.println("\t• Year Bought\t\t: "  + yearBought);
		System.out.println("\t• Purchase Price\t: " + formatter.format(purchasePrice));
		System.out.println("\t• Premium Charge\t: " + formatter.format(premium));
		System.out.print("\n");
	}

	// Display for claim details taken from sql query.
	protected void display(String claimNum,String policyNum, LocalDate accidentDate, String accidentLoc, 
			String accidentDesc, String damageDesc, double repairCost) {
		//converting local date to string.
		String accDate = accidentDate.getMonth() + " " + String.format("%02d", accidentDate.getDayOfMonth()) + "," + accidentDate.getYear();
		//displaying output in bullets.
		System.out.println("\t• Claim ID\t\t: C"          + claimNum);
		System.out.println("\t• Policy Number\t\t: "      + policyNum);
		System.out.println("\t• Accident Date\t\t: "      + accDate);
		System.out.println("\t• Accident Location\t: "    + accidentLoc.toUpperCase());
		System.out.println("\t• Accident Description\t: " + accidentDesc.toUpperCase());
		System.out.println("\t• Damage Description\t: "   + damageDesc.toUpperCase());
		System.out.println("\t• Repair Cost\t\t: "        + formatter.format(repairCost));
		System.out.print("\n");
	}
}// End of Code.