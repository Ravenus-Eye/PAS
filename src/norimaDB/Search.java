package norimaDB;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;



public class Search extends Display {
	
	Select select = new Select();
	
	public int searchAccount(String accountNumber) throws SQLException {
		ResultSet rset  = select.selectCustomer(accountNumber);
		int counter = 0;
		while (rset.next()) {
			display(accountNumber, rset.getString("first_name"), rset.getString("last_name"), rset.getString("address"));
			counter++;
		}
    	Conn.conn.close();
		return counter;
	}

	public String searchAccount(String firstName, String lastName) throws SQLException {
		ResultSet rset  = select.selectCustomer(firstName, lastName);
		String accountNumber = null;
		while (rset.next()) {
			accountNumber = rset.getString("account_no");
			display(accountNumber, rset.getString("first_name"), rset.getString("last_name"), rset.getString("address"));
		}
    	Conn.conn.close();
		return accountNumber;
	}
	
	public void searchAccount(String policyNumber, String policyHolder, Double policyPremium) throws SQLException {
		ResultSet rset  = select.selectPolicy(Integer.valueOf(policyNumber));
		int counter = 0;
		while (rset.next()) {
			ResultSet rset1 = select.selectCustomer(rset.getString("account_no"));
			String accountHolder = null;
			while (rset1.next()) {
				accountHolder = rset1.getString("first_name") + " " + rset1.getString("last_name");
			}
			if (accountHolder != null) {
				display(rset.getString("account_no"), accountHolder, rset.getString("policy_no"), policyHolder, 
						policyPremium, rset.getDate("effective_date").toLocalDate(), rset.getDate("expiration_date").toLocalDate());
				counter++;
			}
		}
		if (counter == 0) {
			System.out.println("  No policy found...");
		}
    	Conn.conn.close();
	}
	
	public String searchAccountPolicyNumber(String accountNumber, int method) throws SQLException {
		if (method == 1) {
			System.out.println("===========================================================================================================");
			System.out.println("|                                              Policy List                                                |");
			System.out.println("===========================================================================================================");	
			System.out.format("%-15s%-40s%-20s%-20s%-10s", "Policy No.", "Policy Holder", "  Effective Date", "  Expiration Date", "Status");
			System.out.println("\n===========================================================================================================");	
		}
		ResultSet rset  = select.selectPolicy(accountNumber);
		String policyNumber = null;
		String policyHolder = null;
		int counter = 0;
		while (rset.next()) {
			if (rset.getDate("expiration_date").compareTo(Date.valueOf(LocalDate.now())) >= 0) {
				policyNumber = rset.getString("policy_no");
				if (method == 1) {
					policyHolder = searchHolder(policyNumber, 2);
				}
			} else {continue;}
			if (policyHolder == null) {
				continue;
			} else {
				displayPolicy(rset.getString("policy_no"), policyHolder,  
						rset.getDate("effective_date").toLocalDate(), rset.getDate("expiration_date").toLocalDate());
				counter++;
			}
		}
		if (method == 1) {
			if (counter == 0) {
				System.out.println("\n  No Active Policy...");
				policyNumber = null;
			}
			System.out.println("===========================================================================================================\n");
		}
		if (counter > 0) {
			System.out.println(" Total number of policy: " + counter);
			System.out.print("\n");
		}
    	Conn.conn.close();
		return policyNumber;
	}
	
	public String searchHolder(String policyNumber, int method) throws SQLException {
		ResultSet rset  = select.selectHolder(policyNumber);
		String policyHolder = null;
		while (rset.next()) {
			policyHolder = rset.getString("first_name") + " " + rset.getString("last_name");
			if (method == 1) {
				display(rset.getString("holder_no"), rset.getString("address"), LocalDate.parse(rset.getString("birth_date")), 
						rset.getString("license_no"), LocalDate.parse(rset.getString("issued_date")));
			}
		}
    	Conn.conn.close();
		return policyHolder;
	}

	public String searchHolder(String accountNumber, String policyNumber) throws SQLException {
		ResultSet rset  = select.selectPolicy(Integer.valueOf(policyNumber));
		String policyHolder = null;
		while (rset.next()) {
			if (accountNumber.equalsIgnoreCase(rset.getString("account_no"))) {
				policyHolder = searchHolder(policyNumber, 1);
			} else {continue;}
		}
    	Conn.conn.close();
		return policyHolder;
	}
	
	public Double searchPremium(String policyNumber) throws SQLException {
		System.out.println("\n************************Vehicle Details************************\n");
		ResultSet rset  = select.selectVehicle(policyNumber);
		double premium = 0;
		int counter = 0;
		while (rset.next()) {
			premium = premium + rset.getDouble("policy_premium");
			display(rset.getString("vehicle_no"), rset.getString("car_brand"), rset.getString("car_model"), 
					rset.getString("car_type"), rset.getString("car_color"), rset.getString("fuel_type"), 
					rset.getInt("year_bought"), rset.getDouble("purchase_price"), rset.getDouble("policy_premium"));
			counter++;
		}
		System.out.println("***************************************************************");
		System.out.println(" Total number of vehicle: " + counter);
		System.out.print("\n");
    	Conn.conn.close();
		return premium;
	}

	public void searchClaim(ResultSet rset) throws SQLException {
		int counter = 0;
		while (rset.next()) {
			display(rset.getString("claim_no"), rset.getString("policy_no"), LocalDate.parse(rset.getString("accident_date")), 
					rset.getString("accident_loc"), rset.getString("accident_desc"), 
					rset.getString("damage_desc"), rset.getDouble("repair_cost"));	
			counter++;
		}
		if (counter == 0) {
			System.out.println(" No record found...\n");
		} 	
    	Conn.conn.close();
	}
}
