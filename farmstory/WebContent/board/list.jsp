<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside_${gr}.jsp" />
<div id="board">
	<h3>글목록</h3>
	<!-- 리스트 -->
	<div class="list">
		<table>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>글쓴이</td>
				<td>날짜</td>
				<td>조회</td>
			</tr>
			<c:set var="count" value="${count+1}" />
			<c:forEach var="board" items="${requestScope.list }">
				<tr>
					<td>${count=count-1 }</td>
					<td><a href="/farmstory/board/view.do?seq=${board.seq}">${board.title }</a>&nbsp;[${board.comment}]</td>
					<td>${board.nick }</td>
					<td>${board.rdate.substring(2,10) }</td>
					<td>${board.hit }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 페이징 -->
	<nav class="paging">

		<span> <c:if test="${groupStart > 1}">
				<a href="/farmstory/board/list.do?pg=${groupStart-1}" class="prev">이전</a>
			</c:if> <c:forEach var="current" begin="${ groupStart}" end="${groupEnd}">
				<a href="/farmstory/board/list.do?pg=${current}" class="num">${current }</a>
			</c:forEach> <c:if test="${groupEnd < pageEnd }">
				<a href="/farmstory/board/list.do?pg=${groupEnd+1}" class="next">다음</a>
			</c:if>
		</span>
	</nav>
	<a href="/farmstory/board/write.do" class="btnWrite">글쓰기</a>
</div>
</article>
</div>
</section>

<%@ include file="../_footer.jsp"%>