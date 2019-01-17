<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");

	final String HOST = "jdbc:mysql://192.168.0.126:3306/ksw";
	final String USER = "ksw";
	final String PASS = "1234";


	
	Class.forName("com.mysql.jdbc.Driver");

	Connection conn = DriverManager.getConnection(HOST,USER,PASS);

	Statement stmt = conn.createStatement();

	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `JSP_MEMBER` WHERE email='"+email+"'");
	int count = 0;
	if(rs.next()){
		count = rs.getInt(1);
	}
	rs.close();
	stmt.close();
	conn.close();

	JSONObject json = new JSONObject();
	json.put("result", count);

	out.println(json);

%>