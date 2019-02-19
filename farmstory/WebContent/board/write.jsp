<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<section id="sub">
        <div>
          <img src="../img/sub_top_tit3.png" alt="CROPTALK" />
        </div>
        <div class="croptalk">
          <aside>
            <img src="../img/sub_aside_cate3_tit.png" alt="장보기" />
            <ul>
              <li class="on"><a href="/farmstory/croptalk/story.do">농작물이야기</a></li>
              <li><a href="/farmstory/croptalk/grow.do">텃밭가꾸기</a></li>
              <li><a href="/farmstory/croptalk/school.do">귀농학교</a></li>
            </ul>
          </aside>
          <article>
            <nav>
              <img src="../img/sub_nav_tit_cate3_tit1.png" alt="농작물이야기" />
              <p>
                <img src="../img/sub_page_nav_ico.gif" alt="아이콘" />
                HOME > 농작물이야기 > <strong>농작물이야기</strong>
              </p>
            </nav>
            <!-- 내용 시작-->

		<div id="board">
			<h3>글쓰기</h3>
			<div class="write">
				<form action="#" method="post">
					<table>
						<tr>
							<td>제목</td>
							<td><input type="text" name="subject" placeholder="제목을 입력하세요." required /></td>
						</tr>				
						<tr>
							<td>내용</td>
							<td>
								<textarea name="content" rows="20" required></textarea>
							</td>
						</tr>
						<tr>
							<td>첨부</td>
							<td>
								<input type="file" name="file" />
							</td>
						</tr>
					</table>
					<div class="btns">
						<a href="/farmstory/board/list.do" class="cancel">취소</a>
						<input type="submit" class="submit" value="작성완료" />
					</div>
				</form>
			</div>
		</div>
		</article>
	</div>
</section>
<%@ include file="../_footer.jsp" %>


