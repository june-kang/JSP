<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String eName = request.getParameter("name");
	String eHp = request.getParameter("hp");
	String eAddr = request.getParameter("addr");
	String ePos = request.getParameter("pos");
	String eDep = request.getParameter("dep");
	
	final String HOST = "jdbc:mysql://192.168.0.156:3306/ksw";
	final String USER = "ksw";
	final String PASS = "1234";

	Connection conn = null;
	Statement stmt = null;


		// 1단계
		try{
			Class.forName("com.mysql.jdbc.Driver");
	
		// 2단계
			conn = DriverManager.getConnection(HOST, USER, PASS);
		// 3단계
			stmt = conn.createStatement();
		// 4단계
			String sql = "UPDATE `USER` SET name='"+eName+"',hp='"+eHp+"',addr='"+eAddr+"',pos='"+ePos+"',dep='"+eDep+"' WHERE uid='"+uid+"'";
			stmt.executeUpdate(sql); 

			} catch(Exception e){
				e.printStackTrace();
			} finally{
				stmt.close();
				conn.close();
			}
	
	// 리다이렉트 - 직원 목록으로 이동
	response.sendRedirect("./14-3.jsp");



%>