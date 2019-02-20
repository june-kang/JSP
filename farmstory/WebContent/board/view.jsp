<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside_${gr}.jsp" />
<div id="board">
	<h3>글보기</h3>
	<div class="view">
		<form action="#" method="post">
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="${vo.title }"
						readonly /></td>
				</tr>
				
				<c:if test="${vo.file==1}">
				<tr>
					<td>첨부파일</td>
					<td>
					<a href="/farmstory/board/filedown.do?seq=${vo.seq }&newName=${vo.newName }&oldName=${vo.oldName}"><img src="/farmstory/img/file_ico.png"/>${vo.oldName }</a>
					<span>${vo.download }회 다운로드</span>
					</td>
				</tr>
				</c:if>

				<tr>
					<td>내용</td>
					<td><textarea name="content" rows="20" readonly>${vo.content }</textarea>
					</td>
				</tr>
			</table>
			<div class="btns">
				<a href="#" class="cancel del">삭제</a>
				<a href="#" class="cancel mod">수정</a>
				<a href="#" class="cancel">목록</a>
			</div>
		</form>
	</div>
	<!-- view 끝 -->

	<!-- 댓글리스트 -->
	<section class="comments">
		<h3>댓글목록</h3>

		<div class="comment">
			<span> <span class="nick">홍길동</span> <span class="date">18-03-01</span>
			</span>
			<textarea>테스트 댓글입니다.</textarea>
			<div>
				<a href="#" class="del">삭제</a> <a href="#" class="mod">수정</a>
			</div>
		</div>

		<p class="empty">등록된 댓글이 없습니다.</p>

	</section>

	<!-- 댓글쓰기 -->
	<section class="comment_write">
		<h3>댓글쓰기</h3>
		<div>
			<form action="/farmstory/board/comment.do" method="post">
				<input type="hidden" name="parent" value="${vo.seq }" />
				<input type="hidden" name="uid" value="${sessionScope.member.uid }" />
				<input type="hidden" name="nick" value="${member.nick }" />

				<textarea name="comment" rows="5"></textarea>
				<div class="btns">
					<input type="reset" class="cancel" value="취소" />
					<input type="submit" class="submit" value="작성완료" />
				</div>
			</form>
		</div>
	</section>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
				$(function(){
					var btnComment = $('.comment_write .submit');
					btnComment.click(function(){
						
						var parent 	= $('.comment_write input[name=parent]').val();
						var uid		= $('.comment_write input[name=uid]').val();
						var nick	= $('.comment_write input[name=nick]').val();
						var content	= $('.comment_write textarea').val();
						
						var json = {"parent":parent, "uid":uid, "content":content, "nick":nick};
						/*
						var json = {
							parent : parent,
							uid : uid,
							content : content,
							nick:nick
						};
									 
						*/
						$.ajax({
							url: '/farmstory/comment.do',
							type:'POST',
							dataType:'json',
							data:json,
							success:function(result){
								
								var comments = $('.comments');
								var comment = $('.comments>.comment');
								
								var commentCloned = comment.clone();
								commentCloned.find('span > .nick').text(result.nick);
								commentCloned.find('span > .date').text(result.date);
								commentCloned.find('textarea').text(result.content);
								comments.append(commentCloned);
								
							}
						});
						
						return false // 클릭하면 form태그 액션 실행 "#"되어있어서 return이 제대로 안됨 
												
					});
				});
			
			</script>
</div>
<!-- board 끝 -->
</article>
</div>
</section>
<%@ include file="../_footer.jsp"%>

