package coms363proj1b;

import java.sql.*;
import java.util.ArrayList;

public class Query {

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


			//// Q1

			ResultSet rs = stmt.executeQuery("SELECT snum, ssn FROM students WHERE name = 'Becky'");
			while (rs.next()) {
				System.out.println("SNUM and SSN of Becky: "+rs.getInt(1) + " " + rs.getInt(2));
			}

/////q2
			ResultSet ms = stmt.executeQuery("SELECT snum FROM students WHERE ssn = 123097834");
			
			int snum = 0;
			if (ms.next()) {
				snum = ms.getInt(1);
			}

			ms = stmt.executeQuery("SELECT name,level From major Where snum= " + snum);

			while (ms.next()) {
				System.out.println("Name and Level: "+ms.getString(1) + " " + ms.getString(2));
			}

////////q3
			ms = stmt.executeQuery("SELECT  code FROM departments WHERE name = 'Computer Science'");
		
			int code = 0;

			while (ms.next()) {
				code = ms.getInt(1);
			}
		

			ms = stmt.executeQuery("SELECT name from courses Where department_code=" + code);

			while (ms.next()) {
				System.out.println("Course in computer Science: " +ms.getString(1));
			}
////q4

			ms = stmt.executeQuery("SELECT name,level From degrees Where department_code=" + code);

			while (ms.next()) {
				System.out.println("Name and level from CS: " +ms.getString(1) + " " + ms.getString(2));
			}

			//// q5
			// SELECT name From students Where snum =;
			ms = stmt.executeQuery("SELECT  snum FROM minor");
			ArrayList<Integer> studentsSnum = new ArrayList<Integer>(); // Create an ArrayList object
			while (ms.next()) {
				studentsSnum.add(ms.getInt(1));
			}

			for (int i = 0; i < studentsSnum.size(); i++) {
				ms = stmt.executeQuery("SELECT name From students Where snum ="+studentsSnum.get(i));
				
				if (ms.next()) {
					System.out.println( "students with a minor: "+ms.getString(1));
				}
				
			}
			
			System.out.println("did all Queries");


			connect.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
