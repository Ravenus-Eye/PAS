package norimaDB;

import java.sql.*;

public class JDBCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/norima","root","root");
				Statement stmt = conn.createStatement();				
		) {
			String strSelect = "select title, price, qty from books";
			System.out.println("The SQL statement is : " + strSelect + "\n");
			ResultSet rset = stmt.executeQuery(strSelect);
			System.out.println("The records selected are:");
			int rowCount = 0;
			while (rset.next()) {
				String title = rset.getString("title");
				double price =rset.getDouble("price");
				int qty = rset.getInt("qty");
				System.out.println(title + ", " + price + ", " + qty);
				++rowCount;
			}
			System.out.println("Total number of records: " + rowCount);
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
			
		}

	}

}
