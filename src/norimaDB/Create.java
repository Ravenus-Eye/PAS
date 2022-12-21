package norimaDB;

/**
 * @author DonAmbrocio
 * @Description : Norima Java Developer Course Capstone Project Instruction(Create Class)
 * This class is responsible for creating the table for all the data needed to save to the database.
 * Created Date: 08/24/2022
 */

import java.sql.SQLException;

public class Create extends Display {
	
	// Create the Table for Customer Account.
	public void createTableCustomer() throws SQLException {
		Conn.prepareStatement("create table if not exists customer_account\r\n"  
				+ "(\r\n"
				+ "account_no int auto_increment primary key,\r\n"
				+ "first_name varchar(20),\r\n"
				+ "last_name  varchar(20),\r\n"
				+ "address    varchar(40)\r\n"
				+ ")");                                                          //transfer the string query to create the table.
    	Conn.conn.close();                                                       //close the connection to the database.
	}
	
	// Create the Table for Policy Info.
	public void createTablePolicy() throws SQLException {
		Conn.prepareStatement("create table if not exists policy_list\r\n"       
				+ "(\r\n"
				+ "policy_no       int auto_increment primary key,\r\n"
				+ "account_no      varchar(10),\r\n"
				+ "effective_date  Date,\r\n"
				+ "expiration_date Date\r\n"
				+ ")");                                                          //transfer the string query to create the table.
    	Conn.conn.close();                                                       //close the connection to the database.
	}
	
	// Create the Table for the Policy Holder.
	public void createTableHolder() throws SQLException {
		Conn.prepareStatement("create table if not exists policy_holder\r\n"     
				+ "(\r\n"
				+ "holder_no   int auto_increment primary key,\r\n"
				+ "policy_no   varchar(10),\r\n"
				+ "first_name  varchar(20),\r\n"
				+ "last_name   varchar(20),\r\n"
				+ "address     varchar(40),\r\n"
				+ "birth_date  Date,\r\n"
				+ "license_no  varchar(20),\r\n"
				+ "issued_date Date\r\n"
				+ ")");                                                          //transfer the string query to create the table.
    	Conn.conn.close();                                                       //close the connection to the database.
	}
	
	// Create the Table for the Vehicle Info.
	public void createTableVehicle() throws SQLException {
		Conn.prepareStatement("create table if not exists vehicle_list\r\n"      
				+ "(\r\n"
				+ "vehicle_no     int auto_increment primary key,\r\n"
				+ "policy_no      varchar(10),\r\n"
				+ "car_brand      varchar(20),\r\n"
				+ "car_model      varchar(20),\r\n"
				+ "car_type       varchar(20),\r\n"
				+ "car_color      varchar(20),\r\n"
				+ "fuel_type      varchar(20),\r\n"
				+ "year_bought    int,\r\n"
				+ "purchase_price DOUBLE(20,2),\r\n"
				+ "policy_premium DOUBLE(20,2)\r\n"
				+ ")");                                                          //transfer the string query to create the table.
    	Conn.conn.close();                                                       //close the connection to the database.
	}

	//Create the Table for the Claim Info.
	public void createTableClaim() throws SQLException {
		Conn.prepareStatement("create table if not exists claim_list\r\n"        
				+ "(\r\n"
				+ "claim_no      int auto_increment primary key,\r\n"
				+ "policy_no     varchar(6),\r\n"
				+ "accident_date Date,\r\n"
				+ "accident_loc  varchar(30),\r\n"
				+ "accident_desc varchar(50),\r\n"
				+ "damage_desc   varchar(50),\r\n"
				+ "repair_cost   DOUBLE(10,2)\r\n"
				+ ")");                                                          //transfer the string query to create the table.
    	Conn.conn.close();                                                       //close the connection to the database.
	}
}