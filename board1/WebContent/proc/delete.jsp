<%@page import="kr.co.board1.service.BoardService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BoardService board = BoardService.getInstance();
	board.delete(request);
	
	response.sendRedirect("../list.jsp");
%>
