<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.vo.BoardVO"%>
<%@page import="kr.co.board1.service.BoardService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("member");	
	if(member == null){
		pageContext.forward("./login.jsp"); // sendredirect- 전체페이지가 먼저 실행되기때문에 nick값 오류 /  forward는 제어권이 login 페이지로 넘어감
	}

	BoardService service = BoardService.getInstance();
	BoardVO vo = service.view(request);
	service.updateHit(vo.getSeq());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글보기</title> 
		<link rel="stylesheet" href="./css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글보기</h3>
			<div class="view">
				<form action="#" method="post">
					<table>
						<tr>
							<td>제목</td>
							<td><input type="text" name="subject" value="<%=vo.getTitle() %>" readonly />
							</td>
						</tr>
						
						<%if(vo.getFile() == 1){ %>
						<tr>
							<td>첨부파일</td>
							<td>
								<a href="#">테스트.hwp</a>
								<span>3회 다운로드</span>
							</td>
						</tr>
						<%} %>
						
						<tr>
							<td>내용</td>
							<td>
								<textarea name="content" rows="20" readonly><%=vo.getContent() %></textarea>
							</td>
						</tr>
					</table>
					<div class="btns">
						<a href="./proc/delete.jsp?seq=<%= vo.getSeq() %>" class="cancel del">삭제</a>
						<a href="./modify.jsp?seq=<%= vo.getSeq() %>" class="cancel mod">수정</a>
						<a href="./list.jsp" class="cancel">목록</a>
					</div>
				</form>
			</div><!-- view 끝 -->
			
			<!-- 댓글리스트 -->
			<section class="comments">
				<h3>댓글목록</h3>
				
				<div class="comment">
					<span>
						<span>홍길동</span>
						<span>18-03-01</span>
					</span>
					<textarea>테스트 댓글입니다.</textarea>
					<div>
						<a href="#" class="del">삭제</a>
						<a href="#" class="mod">수정</a>
					</div>
				</div>
			
				<p class="empty">
					등록된 댓글이 없습니다.
				</p>
				
			</section>
			
			<!-- 댓글쓰기 -->
			<section class="comment_write">
				<h3>댓글쓰기</h3>
				<div>
					<form action="#" method="post">
						<textarea name="comment" rows="5"></textarea>
						<div class="btns">
							<a href="#" class="cancel">취소</a>
							<input type="submit" class="submit" value="작성완료" />
						</div>
					</form>
				</div>
			</section>
		</div><!-- board 끝 -->
	</body>

</html>










