package norimaDB;

/**
 * 
 * @author DonAmbrocio
 * @Description : Module 3 Practice Activity
 * Created Date: 07/14/2022
 */

// import all java.sql utilities.
import java.sql.*;

public class JDBCPerson {

	public static void main(String[] args) {
		// Using try-catch for the SQL errors. 
		try (
				// Connecting database to java program.
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/norima","root","root");
				// Initialize query variable.
				Statement stmt = conn.createStatement();				
		) {
			// Desired query to String.
			String strSelect = "select distinct first_name from person";
			// To display the query to the terminal.
			System.out.println("The SQL statement is : " + strSelect + "\n");
			// Throwing query result to result set variable. 
			ResultSet rset = stmt.executeQuery(strSelect);
			System.out.println("The records selected are:");
			// Initialize row count value.
			int rowCount = 0;
			// Looping while another row with value/s exist.
			while (rset.next()) {
				// Getting value from result set and displaying it
				String fname = rset.getString("first_name");
				System.out.println(fname);
				// Row count increment, to check the number of entries taken by the program
				++rowCount;
			}
			// Displaying row count.
			System.out.println("Total number of records: " + rowCount);
		} catch (SQLException ex) {
			// Exception Handling
			ex.printStackTrace();
		}

	}

}
