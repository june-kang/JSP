<%@page import="kr.co.board1.service.BoardService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BoardService board = BoardService.getInstance();
	String parent = board.delete(request);
	
	
	response.sendRedirect("../view.jsp?seq="+parent);


%>