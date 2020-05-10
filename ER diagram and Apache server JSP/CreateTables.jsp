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
		out.println("mama");
		Statement stmt = connection.createStatement();

		String CreateStudentsTable = "create table students (" + "" + "  snum integer NOT NULL UNIQUE,"
				+ "  ssn integer," + "  name varchar(10)," + "  gender varchar(10)," + "  dob datetime,"
				+ "  c_addr varchar(20)," + "  c_phone varchar(10)," + "  p_addr varchar(20),"
				+ "  p_phone varchar(10)," + "  primary key(ssn)" + "  " + "  );";
		stmt.executeUpdate(CreateStudentsTable);

		String CreateTabledept = "create table departments (" +

				"  code integer," + "  name varchar(50) NOT NULL UNIQUE," + "  phone varchar(10),"
				+ "  college varchar(20)," +

				"  primary key(code)" + "  " + "  );";
		stmt.executeUpdate(CreateTabledept);

		String CreateTabledegrees = "create table degrees (" + "" + "  name varchar(50) ," + "  level varchar(5),"
				+ "  department_code integer," + "  primary key(name,level),"
				+ "  foreign key(department_code) references departments(code)" + "  " + "  );";
		stmt.executeUpdate(CreateTabledegrees);

		String CreateTableCourses = "create table courses (" + "  number integer,"
				+ "  name varchar(50) NOT NULL UNIQUE," + "  description varchar(50)," + "  credithours integer,"
				+ "  level varchar(20)," + "  department_code integer," + "  primary key(number),"
				+ "  foreign key(department_code) references departments(code)" + "  " + "  );";
		stmt.executeUpdate(CreateTableCourses);

		String CreateTableRegister = " create table register (" + "  snum integer," + "  course_number integer,"
				+ "  regtime varchar(20)," + "  grade integer," + "  primary key(snum, course_number),"
				+ "  foreign key(snum) references students(snum),"
				+ "  foreign key(course_number) references courses(number)" + "  " + "  );";

		stmt.executeUpdate(CreateTableRegister);

		String CreateTableMajor = "create table major (" + "  snum integer," + "  name varchar(50),"
				+ "  level varchar(5)," + "  primary key(snum, name,level),"
				+ "  foreign key(name,level) references degrees(name,level),"
				+ "  foreign key(snum) references students(snum)" + "  " + "  " + "  );";
		stmt.executeUpdate(CreateTableMajor);
		String CreateTableMinor = "create table minor (" + "  snum integer," + "  name varchar(50),"
				+ "  level varchar(5)," + "  primary key(snum, name,level),"
				+ "  foreign key(snum) references students(snum),"
				+ "  foreign key(name,level) references degrees(name,level)" + "  " + "  );";
		stmt.executeUpdate(CreateTableMinor);
		out.println("Created table in given database...");
	};%> 
	
	
<body>
<%

String button = request.getParameter("button1");
out.println("Click Submit To Make Tables");

if("Submit".equals(button)){
	test(out);
	String redirectURL = "CreateTablesResult.jsp";
    response.sendRedirect(redirectURL);
}
%>
	<form  method="post">
    <input type="submit" name="button1" value="Submit" />
    
</form>
	

</body>
</html>