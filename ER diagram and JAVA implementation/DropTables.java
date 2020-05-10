package coms363proj1b;

import java.sql.*;
import java.util.ArrayList;

public class DropTables {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Connection connect = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");// Set up connection parameters
			String userName = "root";
			String password = "NewPassword";
			String dbServer = "jdbc:mysql://localhost:3306/demo";// Set up connection
			connect = DriverManager.getConnection(dbServer, userName, password);

			Statement stmt = connect.createStatement();


			stmt.executeUpdate("drop table register");
			stmt.executeUpdate("drop table major");
			stmt.executeUpdate("drop table courses");
			stmt.executeUpdate("drop table minor");
			stmt.executeUpdate("drop table degrees");
			stmt.executeUpdate("drop table departments");
			stmt.executeUpdate("drop table students");
			
			System.out.println("Dropped ALl tables");

			connect.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
