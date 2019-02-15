<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="/farmstory/css/style.css" />
    <link rel="stylesheet" href="/farmstory/css/board.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="./js/slider.js"></script>
  </head>
  <body>
    <div id="wrapper"> <!--페이지 전체 구조-->
      <header>
        <a href="/farmstory/index.do" class="logo"><img src="/farmstory/img/logo.png" alt="로고" /></a>
        <p>
          <a href="/farmstory/index.do">HOME |</a>
          <a href="/farmstory/member/login.do">로그인 |</a>
          <a href="/farmstory/member/terms.do">회원가입 |</a>
          <a href="/farmstory/community/qna.do">고객센터</a>
        </p>
        <img src="/farmstory/img/head_txt_img.png" alt="3만원 이상 무료배송" />
        <ul class="gnb"><!--대분류-->

          <li><a href="/farmstory/introduction/hello.do">팜스토리소개</a></li> <!-- 컨텍스트 루트로 시작함:액션주소라서 -->
          <li><a href="/farmstory/market/market.do">장보기</a></li>
          <li><a href="/farmstory/croptalk/story.do">농작물이야기</a></li>
          <li><a href="/farmstory/event/event.do">이벤트</a></li>
          <li><a href="/farmstory/community/notice.do">커뮤니티</a></li>
        </ul>
      </header>