package norimaDB;

import java.time.LocalDate;

public class Validate {
	
	public int validateString(String name, int limit, int method) {
		int counter = 0;
		if (name.isEmpty()) {
			System.out.println("Empty entry is unaccepted.");
			counter++;
		} else if (!name.matches("[a-zA-Z .,-]+") && method == 1) {
		    System.out.println("Only Alphabets, spaces and these symbols[.,-] are accepted.");
			counter++;
		} else if (!name.matches("[a-zA-Z ]+") && method == 2) {
		    System.out.println("Only Alphabets and spaces are accepted.");
			counter++;
		} else if (!name.matches("[0-9a-zA-Z -,.]+") &&  method == 3) {
		    System.out.println("Only Alphanumeric characters, spaces and these symbols[-,.] are accepted.");
			counter++;
		} else if (!name.matches("[0-9a-zA-Z -]+") && method == 4) {
		    System.out.println("Only Alphanumeric characters, spaces and this symbol[-] are accepted.");
			counter++;
		} else if (name.equalsIgnoreCase("q") && method == 5) {
		    System.out.print("");
		} else if (!name.matches("[0-9]+") && method == 5) {
		    System.out.println("Only numbers are accepted.");
			counter++;
		} else if (name.length() < limit && method == 5) {
	        System.out.println("It needs to be " + limit + " characters.");
			counter++;
		} else if (name.length() > limit) {
	        System.out.println("Only " + limit + " characters are accepted.");
			counter++;
		} else if (name.charAt(0) == ' ') {
			System.out.println("Remove space from the beginning.");
			counter++;;
		}
		return counter;
	}
	
	public int validateDate(LocalDate date, int method) {
		PolicyHolder pHolder = new PolicyHolder();
		Policy policy = new Policy();
		Claim claim = new Claim();
		int counter = 0;
		if (method == 1) {
			if (date.compareTo(LocalDate.now()) < 0) {
				System.out.println("Today's date is already "  + LocalDate.now().getMonth() 
				+ " " + LocalDate.now().getDayOfMonth() + ", " + LocalDate.now().getYear());
				counter++;
			}
		} else if (method == 2) {
			if (date.compareTo(LocalDate.now()) > 0) {
				System.out.println("Today's date is just "  + LocalDate.now().getMonth() + " " 
				+ LocalDate.now().getDayOfMonth() + ", " + LocalDate.now().getYear() + ".");
				counter++;
			} else if (date.compareTo(LocalDate.now().minusYears(70)) < 0) {
				System.out.println("Customer is over the age of 70.");
				counter++;
			} else if (date.compareTo(LocalDate.now().minusYears(18)) > 0) {
				System.out.println("Customer is still a minor.");
				counter++;
			}
		} else if (method == 3) {
			if (date.compareTo(LocalDate.now()) > 0) {
				System.out.println("Today's date is just "  + LocalDate.now().getMonth() + " " 
				+ LocalDate.now().getDayOfMonth() + ", " + LocalDate.now().getYear() + ".");
				counter++;
			} else if (date.compareTo(pHolder.getBirthDate()) < 0) {
				System.out.println("License is invalid due to issuance date. Holder is still not born on " 
						+ date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear() + ".");
				counter++;
			} else if (date.compareTo(pHolder.getBirthDate()) < 18) {
				System.out.println("License is invalid due to issuance date. Holder is still a minor on " 
						+ date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear() + ".");
				counter++;
			}
		} else if (method == 4){
			if (date.compareTo(LocalDate.now()) > 0) {
				System.out.println("You cannot file accident later than today's date, " 
				+ LocalDate.now().getMonth() + " " + LocalDate.now().getDayOfMonth() + ", " + LocalDate.now().getYear()
				+ ".");
				counter++;
			} else if (date.compareTo(claim.getEffectiveDate()) < 0) {
				System.out.println("Date entered is out of scope, policy effective date is  " 
			    + claim.getEffectiveDate().getMonth() + " " + claim.getEffectiveDate().getDayOfMonth() + ", " 
				+ claim.getEffectiveDate().getYear() + ".");
				counter++;
			}
		} else {
			if (date.compareTo(policy.getExpiryDate()) > 0) {
				System.out.println("Current cancelation date is " 
				+ policy.getExpiryDate().getMonth() + " " + policy.getExpiryDate().getDayOfMonth() + ", " 
				+ policy.getExpiryDate().getYear() + ", you can only change it from  " 
				+ LocalDate.now().getMonth() + " " + LocalDate.now().getDayOfMonth() + ", " 
				+ LocalDate.now().getYear() + " to " + policy.getExpiryDate().getMonth() + " " 
				+ (policy.getExpiryDate().getDayOfMonth()-1) + ", " + policy.getExpiryDate().getYear() + ".");
				counter++;
			} else if (date.compareTo(LocalDate.now()) < 0) {
				System.out.println("You can not enter date earlier than today's date, " 
			    + LocalDate.now().getMonth() + " " + LocalDate.now().getDayOfMonth() + ", " 
				+ LocalDate.now().getYear() + ".");
				counter++;
			}
		}
		return counter;
	}
}
