<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");
	
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("subject");
	String content = request.getParameter("content");
	String uid = member.getUid();
	String regip = request.getRemoteAddr();
		
	Connection conn = DBConfig.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(SQL.INSERT_BOARD);
	
	pstmt.setString(1,title);
	pstmt.setString(2,content);
	pstmt.setString(3,uid);
	pstmt.setString(4,regip);
	
	pstmt.executeUpdate();
	
	pstmt.close();
	conn.close();
	
	response.sendRedirect("../list.jsp");
	
%>
