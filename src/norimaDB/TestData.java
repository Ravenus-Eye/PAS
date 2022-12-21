package norimaDB;

import java.sql.SQLException;
import java.time.LocalDate;

public class TestData {
	
	public void insertCustomer(String firstName, String lastName, String address) throws SQLException {
		Conn.prepareStatement("insert into customer_account (first_name, last_name, address)values\r\n"
				+ "('" + firstName + "','" + lastName + "','" + address + "')");
		Conn.prepareStatement(address);
	}
	
	public void insertPolicy(String accountNum, LocalDate effectiveDate) throws SQLException {
		Conn.prepareStatement("insert into policy_list (account_no, effective_date, expiration_date) values\r\n"
				+ "('" + accountNum + "','" + effectiveDate + "','" + effectiveDate.plusMonths(6) + "')");	
	}

	public void insertHolder(String policyNumber, String firstName, String lastName, String address, LocalDate dateOfBirth, 
			String licenseNumber, LocalDate issuedDate) throws SQLException {
		Conn.prepareStatement("insert into policy_holder (policy_no, first_name, last_name, address, birth_date, license_no,\r\n"
				+ "issued_date) values ('"+ policyNumber + "','" + firstName + "','" + lastName + "','" + address + "','" 
				+ dateOfBirth + "','" + licenseNumber + "','" + issuedDate +  "')");
	}
	
	public void insertVehicle(String policyNumber, String make, String model, String type, String color, 
			String fuelType, int year, double purchasePrice, double premiumCharge) throws SQLException {
		Conn.prepareStatement("insert into vehicle_list (policy_no, car_brand, car_model, car_type, "
				+ "car_color,fuel_type, year_bought, purchase_price, policy_premium)values\r\n"
				+ "('"+ policyNumber + "','" + make + "','" + model + "','" + type + "','" + color + "','" + fuelType 
				+ "'," + year + "," + purchasePrice + "," + premiumCharge + ")");
	}

	public void insertClaim(String policyNumber, LocalDate accidentDate, String accidentLocation, 
			String accidentDescription, String damageDescription, double repairCost) throws SQLException {
		Conn.prepareStatement("insert into claim_list (policy_no, accident_date, accident_loc, "
				+ "accident_desc, damage_desc ,repair_cost)values\r\n"
				+ "('" + policyNumber + "','" + accidentDate + "','" + accidentLocation + "','" 
				+ accidentDescription + "','" + damageDescription + "'," + repairCost + ")");
	}
}
