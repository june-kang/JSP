<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	final String HOST = "jdbc:mysql://192.168.0.156:3306/ksw";
	final String USER = "ksw";	
	final String PASS = "1234";

	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String addr = request.getParameter("addr");
	String pos = request.getParameter("pos");
	String dep = request.getParameter("dep");
	
	// 문자열 dep int로 타입변환 String이 클래스타입이라서
	int dp = Integer.parseInt(dep);
	
	
	// 객체선언
		Connection conn = null;
		PreparedStatement psmt = null;
		
	try{
	// 1단계
		Class.forName("com.mysql.jdbc.Driver");
	// 2단계
		conn = DriverManager.getConnection(HOST, USER, PASS);
	// 3단계
		String sql = "UPDATE `USER` SET name=?, hp=?, addr=?, pos=?, dep=? ";// 뛰어쓰기 유념하기!!!!!
			   sql += "WHERE uid=?";
		psmt = conn.prepareStatement(sql); // ?표로 쉽게 준비해놓음 - prepareStatement : 14-6보다 보기좋음
	
		// 데이터 맵핑(사상)
		psmt.setString(1,name);
		psmt.setString(2,hp);
		psmt.setString(3,addr);
		psmt.setString(4,pos);
		psmt.setInt(5,dp); // setString해도 자동형변환됨
		psmt.setString(6,uid);
	// 4단계
		psmt.executeUpdate();
	// 5단계
	
	}catch(Exception e){
		e.printStackTrace();
	} finally{
		// 6단계
		psmt.close();
		conn.close();
	}
	
	response.sendRedirect("/14-3.jsp");
	
%>
