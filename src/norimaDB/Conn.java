package norimaDB;

/**
 * @author DonAmbrocio
 * @Description : Norima Java Developer Course Capstone Project Instruction(Supporting Class)
 * This class connects the program with the database.
 * Created Date: 07/29/2022
 */

import java.sql.*;

public class Conn {

	// Set the variables needed for the database connection.
    static Connection conn = null;
    static String databaseName = "norima";
    static String dbUrl  = "jdbc:mysql://localhost:3306/" + databaseName;
    static String dbUser = "root";
    static String dbPass = "root";
    
    // Connecting the database to program.
    public static void dbConnect() throws SQLException {
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);                //connection for the database.
    }
    
    // Connecting to database and running a query command to the database.
	public static void prepareStatement(String query) throws SQLException {
		dbConnect();                                                              //calling the method that connects the program to the database.
		PreparedStatement create = conn.prepareStatement(query);                  //prepare the query command to be process with sql.
		create.executeUpdate();                                                   //running the command in sql.
	}

	// Connecting to database and running query to get result sets for display purposes.
	public static ResultSet createStatement(String query) throws SQLException {
		dbConnect();                                                              //calling the method that connects the program to the database.
		Statement stmt = conn.createStatement();                                  //runs the select query
		ResultSet rset = stmt.executeQuery(query);                                //receives the result from the select query.
		return rset;                                                              //returns the data received from the query.
	}
}// End of Code