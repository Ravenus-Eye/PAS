package norimaDB;

/**
 * 
 * @author DonAmbrocio
 * @Description : Assignment 3: MySQL JDBC Connectivity (Main Class)
 * Created Date: 07/22/2022
 */

// import all java.sql utilities.
import java.sql.*;

public class UserPolicy {

	public static void main(String[] args) {
		// Initialize variable.
		try {
			// Prepare SQl queries that do not have return output.
			// SQL statement: deleting present table from the database 
			Conn.prepareStatement("drop table if exists user_policies");
			// SQL statement: creating table with set parameters.
			Conn.prepareStatement("create table user_policies\r\n"
					+ "(\r\n"
					+ "policy_no varchar(20) primary key,\r\n"
					+ "user_id int,\r\n"
					+ "date_registered date,\r\n"
					+ "policy_type_id varchar(10)\r\n"
					+ ")");
			// SQL statement: insert values to the user_policies table.
			Conn.prepareStatement("insert into user_policies values\r\n"
					+ "('689314',1111,'1994-4-18','6896'),('689316',1111,'2012-5-18','6895'),\r\n"
					+ "('689317',1111,'2012-6-20','6894'),('689318',2222,'2012-6-21','6894'),\r\n"
					+ "('689320',3333,'2012-6-18','6894'),('689420',4444,'2012-4-09','6896')");
			// Prepare SQl query with output result set.
			// SQL statement: to display all row and column values from the table.
			String query = "select * from user_policies";
			// Catching the data from the query.
			ResultSet rset  = Conn.createStatement(query);
			// Display query from terminal.
			System.out.print("\nThe SQL statement is : " + query + "\n");
			System.out.println("The records selected are:");
			// Loop to display table values.
			int rowCount = 0;
			while (rset.next()) {
				// Return the number of rows with display process.
				rowCount = display(rset.getString("policy_no"), rset.getInt("user_id"), rset.getDate("date_registered"), 
						rset.getString("policy_type_id"), rowCount);
			}
			System.out.println("Total number of records: " + rowCount);
			// SQL statement: to display all row and column values from the table with registration date less than '2012-01-01'.
			query = "select * from user_policies where date_registered<'2012-01-01'";
			// Catching the data from the query.
			rset  = Conn.createStatement(query);
			// Display query from terminal.
			System.out.print("\nThe SQL statement is : " + query + "\n");
			System.out.println("The records selected are:");
			// Loop to display table values.
			rowCount = 0;
			while (rset.next()) {
				// Return the number of rows with display process.
				rowCount = display(rset.getString("policy_no"), rset.getInt("user_id"), rset.getDate("date_registered"), 
						rset.getString("policy_type_id"), rowCount);
			}
			System.out.println("Total number of records: " + rowCount);
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}

	}
	
	// Display all the values in the terminal.
	private static int display(String policyNumber, int userID, Date dateregistered, String policyTypeID, int rowCount) {
		System.out.println(policyNumber + ", " + userID + ", " + dateregistered + ", " + policyTypeID);
		return ++rowCount;
	}
}