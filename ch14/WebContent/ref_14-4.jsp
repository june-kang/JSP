<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	request.getParameter("seq");
	
	final String HOST = "jdbc:mysql://192.168.0.156:3306/ksw";
	final String USER = "ksw";
	final String PASS = "1234";
	
	// 객체 선언
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try{
		// 1단계
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2단계 - DB 접속
		conn = DriverManager.getConnection(HOST, USER, PASS);
		
		// 3단계 - 쿼리실행 객체 생성
		stmt = conn.createStatement();
		
		// 4단계 - 쿼리실행
		String sql = "DELETE FROM `USER` WHERE seq="+seq;
		stmt.executeUpdate(sql);
		
	}catch(Exception e){
		e.printStackTrace();
	} finally{
		// 6단계
		stmt.close();
		conn.close();
	}

%>
</body>
</html>