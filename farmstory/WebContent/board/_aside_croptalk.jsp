<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="sub">
	<div>
		<img src="../img/sub_top_tit3.png" alt="CROPTALK" />
	</div>
	<div class="croptalk">
		<aside>
			<img src="../img/sub_aside_cate3_tit.png" alt="장보기" />
			<ul>
				<li class="${cate=='story'?'on':''}"><a href="/farmstory/board/list.do?gr=croptalk&cate=story">농작물이야기</a></li>
				<li class="${cate=='grow'?'on':''}"><a href="/farmstory/board/list.do?gr=croptalk&cate=grow">텃밭가꾸기</a></li>
				<li class="${cate=='school'?'on':''}"><a href="/farmstory/board/list.do?gr=croptalk&cate=school">귀농학교</a></li>
			</ul>
		</aside>
		<article>
			<nav>
				<img src="../img/sub_nav_tit_cate3_${cate}.png" alt="농작물이야기" />
				<p>
					<img src="../img/sub_page_nav_ico.gif" alt="아이콘" /> HOME > 농작물이야기 >
					<c:if test="${cate=='story' }">
					<strong>농작물이야기</strong>
					</c:if>
					<c:if test="${cate=='grow'}">
					<strong>텃밭가꾸기</strong>
					</c:if>
					<c:if test="${cate=='school'}">
					<strong>귀농학교</strong>
					</c:if>
				</p>
			</nav>
			<!-- 내용 시작-->