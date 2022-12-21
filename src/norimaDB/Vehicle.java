package norimaDB;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Vehicle extends Insert {

	private String make, model, type, fuelType, color;
	private double purchasePrice;
	protected static double premiumCharge;
	private int year;
	Scanner scan = new Scanner(System.in);
	Validate valid = new Validate();
		
	public double inquireVehicle(String policyNumber) throws SQLException {
		PolicyHolder pHolder = new PolicyHolder();
		RatingEngine rate = new RatingEngine();
		System.out.print("\n******************Vehicle Information******************\n\n");
		make  = pHolder.enterString("Brand of Car\t\t : ", 20, 2);
		model = pHolder.enterString("Model of Car\t\t : ", 20, 4);		
		enterYear();
		enterType();
		enterFuelType();
		enterPrice();
		color = pHolder.enterString("Color of Car\t\t : ", 20, 2);	
		rate.calculatePremium(purchasePrice, year);
		insertVehicle(policyNumber, make, model, type, color, 
				fuelType, year, purchasePrice, premiumCharge);
		return premiumCharge;
	}
	
	public void enterYear() {
		boolean done = false;
		while (!done) {
			try {
				System.out.print("Year it was bought\t : ");
				String strYear = scan.nextLine();
				if (strYear.equals("")) {
					System.out.println("Empty entry is unaccepted.");
				} else {
					int year = Integer.valueOf(strYear);
					if (year <= LocalDate.now().getYear()-40) {
						System.out.println("We only accept cars below 40 years old. ");
					} else if (year > LocalDate.now().getYear()) {
						System.out.println("It's only " + LocalDate.now().getYear() + " this year.");
					} else {
						this.year = year;
						done = true;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
		}
	}

	public void enterType() {
		String[] type = new String[] {"4-Door Sedan", "2-Door Sports Car", "SUV", "Truck"}; 
		boolean done = false;
		while (!done) {
			try {
				System.out.println("Car Type Option: ");
				for (int i=0; i<4; i++) {
					System.out.println("\t[" + (i+1) + "] " + type[i]);
				}
				System.out.print("Enter car type\t\t : ");
				String strOption = scan.nextLine();
				if (strOption.equals("")) {
					System.out.println("Empty entry is unaccepted.");
				} else if (strOption.length() > 1) {
					System.out.println("Invalid input, Please only choose from 1-4.");
				} else {
					int option = Integer.valueOf(strOption);
					if (option < 1 || option > 4) {
						System.out.println("Invalid input, Please only choose from 1-4.");
					} else {
						this.type = type[option-1];
						done = true;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input, Please only choose from 1-4.");
			}
		}
	}
	
	public void enterFuelType() {
		String[] fuelType = new String[] {"Diesel", "Electric", "Petrol"}; 
		boolean done = false;
		while (!done) {
			try {
				System.out.println("Fuel Type Option: ");
				for (int i=0; i<3; i++) {
					System.out.println("\t[" + (i+1) + "] " + fuelType[i]);
				}
				System.out.print("Enter fuel type\t\t : ");
				String strOption = scan.nextLine();
				if (strOption.equals("")) {
					System.out.println("Empty entry is unaccepted.");
				} else if (strOption.length() > 1) {
					System.out.println("Invalid input, Please only choose from 1-3.");
				} else {
					int option = Integer.valueOf(strOption);
					if (option < 1 || option > 3) {
						System.out.println("Invalid input, Please only choose from 1-3.");
					} else {
						this.fuelType = fuelType[option-1];
						done = true;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input, Please only choose from 1-3.");
			}
		}
	}
	
	private void enterPrice() {
		boolean done = false;
		while (!done) {
			try {
				System.out.print("Purchased Price\t\t : ");
				String strPurchasePrice = scan.nextLine();
				if (strPurchasePrice.equals("")) {
					System.out.println("Empty entry is unaccepted.");
				} else {
					Double pPrice = Double.valueOf(strPurchasePrice);
					if (pPrice < 0) {
						System.out.println("Negative value is unaccepted.");
					} else if (pPrice == 0) {
						System.out.println("Zero is unaccepted.");
					} else if (pPrice < 20000) {
						System.out.println("Car is bellow the minimum purchased price of $20,000.00.");
					} else {
						purchasePrice = pPrice;
						done = true;
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
		}
	}
	
}
