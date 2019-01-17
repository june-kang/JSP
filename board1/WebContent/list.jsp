<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.board1.vo.BoardVO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="kr.co.board1.vo.MemberVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	MemberVO member = (MemberVO)session.getAttribute("member");	
	if(member == null){
		pageContext.forward("./login.jsp"); // sendredirect- 전체페이지가 먼저 실행되기때문에 nick값 오류 /  forward는 제어권이 login 페이지로 넘어감
	}
	
	Connection conn = DBConfig.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_LIST);
	ResultSet rs = pstmt.executeQuery();
	
	ArrayList<BoardVO> list = new ArrayList<>();
	
	
	while(rs.next()){
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt(1));
		board.setParent(rs.getInt(2));
		board.setComment(rs.getInt(3));
		board.setCate(rs.getString(4));
		board.setTitle(rs.getString(5));
		board.setContent(rs.getString(6));
		board.setFile(rs.getString(7));
		board.setHit(rs.getString(8));
		board.setUid(rs.getString(9));
		board.setRegip(rs.getString(10));
		board.setRdate(rs.getString(11));
		board.setNick(rs.getString(12));
		
		list.add(board);
	}
	rs.close();
	pstmt.close();
	conn.close();
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글목록</title> 
		<link rel="stylesheet" href="./css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글목록</h3>
			<!-- 리스트 -->
			<div class="list">
				<p class="logout"><%= member.getNick() %>님! 반갑습니다. <a href="./proc/logout.jsp">[로그아웃]</a><p>
				<table>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>글쓴이</td>
						<td>날짜</td>
						<td>조회</td>
					</tr>
					
					<% 
						for(BoardVO vo : list){
						%>
					<tr>
						<td><%= vo.getSeq() %></td>
						<td><a href="#"><%= vo.getTitle() %></a>&nbsp;[<%= vo.getComment() %>]</td>
						<td><%= vo.getNick() %></td>
						<td><%=vo.getRdate().substring(2,10) %></td>
						<td><%= vo.getHit() %></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<!-- 페이징 -->
			<nav class="paging">
				<span> 
				<a href="#" class="prev">이전</a>
				<a href="#" class="num">1</a>
				<a href="#" class="next">다음</a>
				</span>
			</nav>
			<a href="./write.jsp" class="btnWrite">글쓰기</a>
		</div>
	</body>

</html>










