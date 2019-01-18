<%@page import="kr.co.board1.service.MemberService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	MemberService.getInstance().logout(session,response);
%>