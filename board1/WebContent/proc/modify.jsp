<%@page import="kr.co.board1.service.BoardService"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BoardService service = BoardService.getInstance();
	String seq = service.modify(request);
	
	response.sendRedirect("../view.jsp?seq="+seq);


%>
