<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="sub1.USER" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	final String HOST = "jdbc:mysql://192.168.0.156:3306/ksw";
	final String USER = "ksw";
	final String PASS = "1234";

	Connection conn = null; 
	Statement stmt = null;
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(HOST, USER, PASS);
		stmt = conn.createStatement();
		
		String sql = "DELETE FROM `USER` WHERE uid='"+uid+"';";
		stmt.executeUpdate(sql);
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			conn.close();
			stmt.close();
}

// 리다이렉트
	response.sendRedirect("./14-3.jsp");
%>
</body>
</html>