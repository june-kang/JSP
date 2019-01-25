<%@page import="java.util.ArrayList"%>
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
	BoardService service = BoardService.getInstance();
	BoardVO vo = service.view(request);
	
	MemberVO member = service.getMember(session);
	service.updateHit(vo.getSeq());
	
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>글보기</title> 
		<link rel="stylesheet" href="./css/style.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				var comments = $('.comments');
				var comment = $('.comments>.comment');
				var empty = $('.comments>.empty');
				var parent = $('#seq').val();
				
				
				$.ajax({
					url:'./proc/commentList.jsp?parent='+parent,
					type:'get',
					dataType:'json',
					success: function(result){ // result=[{...},{...}...] : json 객체
						if(result.length == 0){
							comment.remove();
						} else {
							empty.remove();
						}
						
						for(var i in result){
							//alert('content : '+result[i].content);
							
							var delUrl = "./proc/commentDelete.jsp?seq="+result[i].seq+"&parent="+result[i].parent;
							
							if(i > 0){ // 인덱스가 1이상, 두번째 댓글부터
								var commentCloned = comment.clone();
								commentCloned.find('span > .nick').text(result[i].nick);
								commentCloned.find('span > .date').text(result[i].rdate.toString().substring(2,10)); // comment 자식의 span의 date 클래스 찾아서 값입력
								commentCloned.find('textarea').text(result[i].content);
								commentCloned.find('.del').attr('href', delUrl);  // 속성함수
								
								comments.append(commentCloned);
							} else{
								comment.find('span > .nick').text(result[i].nick);
								comment.find('span > .date').text(result[i].rdate.toString().substring(2,10)); // comment 자식의 span의 date 클래스 찾아서 값입력
								comment.find('textarea').text(result[i].content);
								comment.find('.del').attr('href', delUrl);
												
							}
						}
					}						
				});
												
			});
				
		</script>
	</head>
	<body>
		<div id="board">
			<h3>글보기</h3>
			<div class="view">
				<form action="#" method="post">
				<input type="hidden" id="seq" value="<%=vo.getSeq() %>"/>
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
								<a href="./proc/filedown.jsp?seq=<%= vo.getSeq() %>&newName=<%= vo.getNewName()%>&oldName=<%=vo.getOldName()%>"><%=vo.getOldName() %></a>
								<span><%=vo.getDownload() %>회 다운로드</span>
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
						<span class="nick">닉네임</span>
						<span class="date">날짜</span>
					</span>
					<textarea>댓글내용</textarea>
					<div>
						<a href="./proc/commentDelete.jsp?seq=?&parent=?" class="del">삭제</a>
						<a href="#" class="mod">수정</a>
					</div>
				</div>
				
			
				
				<p class="empty">등록된 댓글이 없습니다.</p>
				
			</section>
			
			<!-- 댓글쓰기 -->
			<section class="comment_write">
				<h3>댓글쓰기</h3>
				<div>
					<form action="./proc/commentWrite.jsp" method="post">
					<input type="hidden" name="parent" value="<%=vo.getSeq() %>" />
					<input type="hidden" name="uid" value="<%=member.getUid() %>" />
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










