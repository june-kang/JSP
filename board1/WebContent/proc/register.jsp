<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 파라미터 수신
	request.setCharacterEncoding("UTF-8");
	String uid	 = request.getParameter("uid");
	String pass	 = request.getParameter("pw1");
	String name	 = request.getParameter("name");
	String nick	 = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp 	 = request.getParameter("hp");
	String zip 	 = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr();
	
	final String HOST = "jdbc:mysql://192.168.0.126:3306/ksw";
	final String USER = "ksw";
	final String PASS = "1234";
	
	Connection conn = null;
	PreparedStatement ptmt = null;
	String sql = null;
	// 1단계
		try {
			Class.forName("com.mysql.jdbc.Driver");
	// 2단계
		conn = DriverManager.getConnection(HOST,USER,PASS);
	// 3단계
		sql = "INSERT INTO `JSP_MEMBER` SET ";
		   sql += "uid=? ,";
		   sql += "pass=? ,";
		   sql += "name=? ,";
		   sql += "nick=? ,";
		   sql += "email=? ,";
		   sql += "hp=? ,";
		   sql += "zip=? ,";
		   sql += "addr1=? ,";
		   sql += "addr2=? ,";
		   sql += "regip=? ,";
		   sql += "rdate=NOW()";
		   
	
		ptmt = conn.prepareStatement(sql);
	   
	   ptmt.setString(1,uid);
	   ptmt.setString(2,pass);
	   ptmt.setString(3,name);
	   ptmt.setString(4,nick);
	   ptmt.setString(5,email);
	   ptmt.setString(6,hp);
	   ptmt.setString(7,zip);
	   ptmt.setString(8,addr1);
	   ptmt.setString(9,addr2);
	   ptmt.setString(10,regip);
	// 4단계
		ptmt.executeUpdate();
		   
	// 5단계
		} catch(Exception e){
			e.printStackTrace();
		} finally{
	// 6단계
		ptmt.close();
		conn.close();
		}
	// 리다이렉트
		response.sendRedirect("../login.jsp");
%>
