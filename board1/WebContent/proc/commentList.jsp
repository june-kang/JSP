<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.co.board1.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.board1.service.BoardService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	request.setCharacterEncoding("UTF-8");
	String parent = request.getParameter("parent");
	
	//댓글 가져오기
	BoardService service = BoardService.getInstance();
	ArrayList<BoardVO> list = service.listComment(parent);
	
	// 
	Gson gson = new Gson(); // stak-overflow 사이트 gson 검색
	String json = gson.toJson(list); // json 포맷으로 만듬
	out.print(json); // json view-ajax로 넘김


%>