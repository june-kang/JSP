<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>팜스토리::찾아오시는길</title>
    <link rel="stylesheet" href="../css/style.css" />
    <style>
      #sub > .introduction > article > .address {
        position: relative;
        border: 1px solid blue;
        width: 100%;
        height: 100px;
      }
      #sub > .introduction > article > .address > img {
        position: absolute;
        top:

      }
      #sub > .introduction > article > .map {
        width: 100%;
        height: 500px;
      }
    </style>
  </head>
  <body>
    <div id="wrapper">
      <header>
        <a href="../index.html" class="logo"><img src="../img/logo.png" alt="로고" /></a>
        <p>
          <a href="../index.html">HOME |</a>
          <a href="#">로그인 |</a>
          <a href="#">회원가입 |</a>
          <a href="#">고객센터</a>
        </p>
        <img src="../img/head_txt_img.png" alt="3만원 이상 무료배송" />
        <ul class="gnb"><!--대분류-->

          <li><a href="./hello.html">팜스토리소개</a></li>
          <li><a href="../market/market.html">장보기</a></li>
          <li><a href="../croptalk/story.html">농작물이야기</a></li>
          <li><a href="../event/event.html">이벤트</a></li>
          <li><a href="../community/notice.html">커뮤니티</a></li>
        </ul>
      </header>
      <section id="sub">
        <div>
          <img src="../img/sub_top_tit1.png" alt="INTRODUCTION" />
        </div>
        <div class="introduction">
          <aside>
            <img src="../img/sub_aside_cate1_tit.png" alt="팜스토리소개" />
            <ul>
              <li><a href="./hello.html">인사말</a></li>
              <li class="on"><a href="./direction.html">찾아오시는길</a></li>
            </ul>
          </aside>
          <article>
            <nav>
              <img src="../img/sub_nav_tit_cate1_tit2.png" alt="찾아오시는길" />
              <p>
                <img src="../img/sub_page_nav_ico.gif" alt="아이콘" />
                HOME > 팜스토리소개 > <strong>찾아오시는길</strong>
              </p>
            </nav>
            <!-- 내용 시작-->
            <div class="address">
              <img src="../img/sub_page_direction_ico.gif" alt="아이콘" align="left"><strong>팜스토리</strong><br />
              주소: 경기도 이천시 잘한다구 신난다동 123 <br />
              전화: 01-234-5678<br />
              <br/>
              <img src="../img/sub_page_direction_ico.gif" alt="아이콘" align="left"><strong>찾아오시는길</strong>
            </div>
            <div class="map">
              <!-- * Daum 지도 - 지도퍼가기 -->
              <!-- 1. 지도 노드 -->
              <div id="daumRoughmapContainer1550110135631" class="root_daum_roughmap root_daum_roughmap_landing"></div>

              <!--
	             2. 설치 스크립트
	              * 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
              -->
              <script charset="UTF-8" class="daum_roughmap_loader_script" src="http://dmaps.daum.net/map_js_init/roughmapLoader.js"></script>

              <!-- 3. 실행 스크립트 -->
              <script charset="UTF-8">
	             new daum.roughmap.Lander({
		               "timestamp" : "1550110135631",
		                 "key" : "s7md",
		                   "mapWidth" : "800",
		                     "mapHeight" : "500"
	                      }).render();
                      </script>
            </div>
            <!-- 내용 끝-->
          </article>
        </div>
      </section>
      <footer>
        <img src="../img/footer_logo.png" alt="로고" />
        <p>
          (주)팜스토리 / 사업자등록번호 123-45-67890 / 통신판매업신고 제 2013-팜스토리구-123호 / 벤처기업확인 서울지방중소기업청 제 012345678-9-01234호<br />
          등록번호 팜스토리01234 (2013.04.01) / 발행인 : 홍길동<br />
          대표 : 홍길동 / 이메일 : email@mail.mail / 전화 : 01) 234-5678 / 경기도 성남시 잘한다구 신난다동 345<br />
          <span>Copyright(C) <span>홍길동</span> All rights reserved.</span>
        </p>
      </footer>
    </div>
  </body>
</html>
