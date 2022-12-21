package norimaDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
	
	public ResultSet selectCustomer(String accountNumber) {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(account_no, 4, \"0\") AS account_no, first_name, last_name, address "
					+ "from customer_account where account_no=" + accountNumber;
			rset = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}
	
	public ResultSet selectCustomer(String firstName, String lastName) {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(account_no, 4, \"0\") AS account_no, first_name, last_name, address "
					+ "from customer_account where first_name='" + firstName + "' and last_name='" + lastName + "'";
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}
	
	public ResultSet selectPolicy(String firstName, String lastName) {
		ResultSet rset = null;
		try {
			String query = "SELECT policy_no from policy_holder where first_name='" 
					+ firstName + "' and last_name='" + lastName + "'";
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}

	public ResultSet selectPolicy(String accountNumber) {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(policy_no, 6, \"0\") AS policy_no, effective_date, "
					+ "expiration_date from policy_list where account_no='" + accountNumber + "'";
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}
	
	public ResultSet selectPolicy(int policyNumber) {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(policy_no, 6, \"0\") AS policy_no, account_no, effective_date, "
					+ "expiration_date from policy_list where policy_no=" + policyNumber;
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}

	public ResultSet selectHolder(String policyNumber) {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(holder_no, 6, \"0\") AS holder_no, first_name, last_name, address, "
					+ "birth_date, license_no, issued_date from policy_holder where policy_no='" + policyNumber + "'";
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}
	
	public ResultSet selectVehicle(String policyNumber) {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(vehicle_no, 6, \"0\") AS vehicle_no, car_brand, car_model, car_type, car_color, fuel_type, "
					+ "year_bought, purchase_price, policy_premium from vehicle_list where policy_no='" + policyNumber + "'";
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}
	
	public ResultSet selectClaim(int claimNumber) {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(claim_no, 5, \"0\") AS claim_no, policy_no, accident_date, accident_loc, "
					+ "accident_desc, damage_desc, repair_cost from claim_list where claim_no=" + claimNumber;
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}

	public ResultSet selectClaimWithPolicyNumber() {
		ResultSet rset = null;
		try {
			String query = "SELECT LPAD(claim_no, 5, \"0\") AS claim_no, policy_no, accident_date, accident_loc, "
					+ "accident_desc, damage_desc, repair_cost from claim_list ORDER BY claim_no DESC LIMIT 1";
			rset  = Conn.createStatement(query);
		} catch (SQLException e) {
			System.out.println("  No record found...\n");
		}
		return rset;
	}
}
