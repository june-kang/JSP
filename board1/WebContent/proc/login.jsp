<%@page import="kr.co.board1.service.MemberService"%>
<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberService service = MemberService.getInstance();
	String redirectUrl = service.login(request, session);

	response.sendRedirect(redirectUrl); // 결과값에 따라 다른 url 페이지로 이동함
	
%>