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

		stmt.executeUpdate("UPDATE students SET name = 'Scott' WHERE ssn = 746897816");

		ResultSet snumQ = stmt.executeQuery("SELECT  snum FROM students WHERE ssn = 746897816");
		int snum = 0;
		if (snumQ.next()) {
			snum = snumQ.getInt(1);
		}

		stmt.executeUpdate("UPDATE major SET name = 'Computer Science',level = 'MS' WHERE snum ="+snum);
		stmt.execute("DELETE FROM register WHERE regtime='Spring2015'");
		System.out.println("Modfiy Complete");
		

	};%> 
	
	
<body>
<%

String button = request.getParameter("button1");
out.println("Click Submit To Modify Records");

if("Submit".equals(button)){
	test(out);
	String redirectURL = "ModifyRecordsResult.jsp";
    response.sendRedirect(redirectURL);
}
%>
	<form  method="post">
    <input type="submit" name="button1" value="Submit" />
    
</form>
	

</body>
</html>