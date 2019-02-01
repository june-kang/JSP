<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글목록</title> 
		<link rel="stylesheet" href="/board2/css/style.css" />
	</head>
	<body>
		<div id="board">
			<h3>글목록</h3>
			<!-- 리스트 -->
			<div class="list">
				<p class="logout">${sessionScope.member.nick}님! 반갑습니다.
					<a href="/board2/member/logout.do">[로그아웃]</a>
				<p>
				<table>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>글쓴이</td>
						<td>날짜</td>
						<td>조회</td>
					</tr>
					<c:set var="count" value="${count+1}" />
					<c:forEach var="board" items="${requestScope.list }" >
						<tr>
							<td>${count=count-1 }</td>
							<td><a href="/board2/view.do?seq=${board.seq}">${board.title }</a>&nbsp;[${board.comment}]</td>
							<td>${board.nick }</td>
							<td>${board.rdate.substring(2,10) }</td>
							<td>${board.hit }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 페이징 -->
			<nav class="paging">
				
					<span> 
						<c:if test="${groupStart > 1}">
							<a href="/board2/list.do?pg=${groupStart-1}" class="prev">이전</a>
						</c:if>
						<c:forEach var="current" begin="${ groupStart}" end="${groupEnd}">
							<a href="/board2/list.do?pg=${current}" class="num">${current }</a>
						</c:forEach>
						<c:if test="${groupEnd < pageEnd }">
							<a href="/board2/list.do?pg=${groupEnd+1}" class="next">다음</a>
						</c:if>
					</span>
			</nav>
			<a href="/board2/write.do" class="btnWrite">글쓰기</a>
		</div>
	</body>

</html>










