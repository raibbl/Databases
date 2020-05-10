<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<%!

public void test(JspWriter out)
			throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

		String userName = "root";
		String password = "NewPassword";
		String dbServer = "jdbc:mysql://localhost:3306/demo";
		Connection connection = null;
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(dbServer, userName, password);
		
		Statement stmt = connection.createStatement();

		ResultSet ms = stmt.executeQuery("SELECT snum FROM students WHERE ssn = 123097834");
		
		int snum = 0;
		if (ms.next()) {
			snum = ms.getInt(1);
		}

		ms = stmt.executeQuery("SELECT name,level From major Where snum= " + snum);
		out.println("Name and Level:");
		while (ms.next()) {
			out.println(""+ms.getString(1) + " " + ms.getString(2));
		}

				
	};%> 
	
	
<body>
<%


	test(out);
	
%>
	<form  method="post">
   
    
</form>
	

</body>
</html>