<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	
	final String HOST = "jdbc:mysql://192.168.0.126:3306/ksw";
	final String USER = "ksw";
	final String PASS = "1234";
	
	
		
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection conn = DriverManager.getConnection(HOST,USER,PASS);
	
	Statement stmt = conn.createStatement();
	
	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `JSP_MEMBER` WHERE uid='"+uid+"'");
	int count = 0;
	if(rs.next()){
		count = rs.getInt(1);
	}
	
	rs.close();
	stmt.close();
	conn.close();
	
	// JSON 데이터 생성 및 출력
	JSONObject json = new JSONObject(); // simple json 다운받아서 라이브러리 저장, 알아서 json 형태로 만둘어줌
	json.put("result", count);	
	out.println(json);
%>
