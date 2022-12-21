package norimaDB;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

public class PolicyHolder extends Insert {
	
	Scanner scan = new Scanner(System.in);
	Validate valid = new Validate();
	String firstName, lastName, address, licenseNumber;
	private static LocalDate birthDate;
	private LocalDate issuedDate;

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String inquirePolicyHolder(String policyNumber) throws ParseException, SQLException {
		System.out.print("\n******************New Policy Holder******************\n\n");
		firstName = enterString("First Name\t\t : ", 20, 1);
		lastName  = enterString("Last Name\t\t : " , 20, 2);
		address   = enterString("Address\t\t\t : " , 40, 3);
		birthDate = enterDate("Date of Birth(yyyy-MM-dd): ", 2);
		licenseNumber = enterString("License Number\t\t : ", 10, 4);
		issuedDate = enterDate("Issuance Date(yyyy-MM-dd): ", 3);
		insertHolder(policyNumber, firstName, lastName, address, birthDate, licenseNumber, issuedDate);
		return firstName + " " + lastName;
	}
	
	public String enterString(String inquiry, int maxChar, int method) {
		boolean done = false;
		String stringInput = null;
		while (!done) {
			try {
				System.out.print(inquiry);
				stringInput = scan.nextLine();
				stringInput = stringInput.replaceAll("[\\p{Cntrl}]", "");
				int checker = valid.validateString(stringInput, maxChar, method);
				if (checker == 0) {
					done = true;
				} else {
					continue;
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		}
		return stringInput;
	}
	
	public LocalDate enterDate(String inquiry, int method) {
		LocalDate date = null;
		boolean done = false;
		while (!done) {
			try {
				System.out.print(inquiry);
				String strDate = scan.nextLine();
				if (strDate == "") {
					System.out.println("Empty entry is unaccepted.");
				} else {
					date = LocalDate.parse(strDate);
					int checker = valid.validateDate(date, method);
					if (checker == 0) {
						done = true;
					} else {
						continue;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input");
			}
		}		
		return date;
	}
}
