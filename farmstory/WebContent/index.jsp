<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./_header.jsp" %>
      <main>
        <div class="slider"><!--이미지 슬라이더 넣읗떄 목록or백그라운드로함-->
          <ul>
            <li><img src="./img/main_slide_img1.jpg" alt="슬라이드1"></li>
            <li><img src="./img/main_slide_img2.jpg" alt="슬라이드2"></li>
            <li><img src="./img/main_slide_img3.jpg" alt="슬라이드3"></li>
          </ul>
          <img src="./img/main_slide_img_tit.png" alt="사람과 자연을 사랑하는 팜스토리">
          <div class="grandOpen">
            <img src="./img/main_banner_txt.png" alt="GRAND OPEN">
            <img src="./img/main_banner_tit.png" alt="팜스토리 오픈 기념 30% 할인 이벤트">
            <img src="./img/main_banner_img.png" alt="과일 이미지">
          </div>


        </div>
        <div class="banner">
          <a href="/farmstory/board/list.do?gr=community&cate=menu"><img src="./img/main_banner_sub1_tit.png" /></a>
          <a href="/farmstory/board/list.do?gr=community&cate=chef"><img src="./img/main_banner_sub2_tit.png" /></a>
        </div>
        <div class="latest">
          <div>
            <img src="./img/main_latest1_tit.png" alt="텃밭가꾸기">
            <img src="./img/main_latest1_img.jpg" alt="이미지">
            <table>    
              <c:forEach var="vo" items="${latest1 }">
              
              <tr>
                <td>></td>
                <td><a href="/farmstory/board/view.do?gr=croptalk&cate=${vo.cate}&seq=${vo.seq}">${vo.title }</a></td> 
                <td>${vo.rdate }</td>
              </tr>
              
              </c:forEach>
            </table>
          </div>
          <div>
            <img src="./img/main_latest2_tit.png" alt="귀농학교">
            <img src="./img/main_latest2_img.jpg" alt="이미지">
            
            <table> 
              <c:forEach var="vo" items="${latest2 }">
              <tr>
                <td>></td>
                <td><a href="/farmstory/board/view.do?gr=croptalk&cate=${vo.cate}&seq=${vo.seq}">${vo.title }</a></td>  
                <td>${vo.rdate }</td>
              </tr>
              </c:forEach>
            </table>
          </div>
          <div>
            <img src="./img/main_latest3_tit.png" alt="농작물이야기">
            <img src="./img/main_latest3_img.jpg" alt="이미지">
            <table>
              
              <c:forEach var="vo" items="${latest3 }">
              <tr>
                <td>></td>
                <td><a href="/farmstory/board/view.do?gr=croptalk&cate=${vo.cate}&seq=${vo.seq}">${vo.title }</a></td> 
                <td>${vo.rdate }</td>
              </tr>
              </c:forEach>
            </table>
          </div>
        </div>
        <div class="info">
          <div class="service">
            <img src="./img/main_sub2_cs_tit.png" alt="고객센터안내" />
              <table>
                <tr>
                  <td><img src="./img/main_sub2_cs_img.png" alt="이미지" /></td>
                  <td><img src="./img/main_sub2_cs_txt.png" alt="전화번호" /></td>
                  <td>
                    평일: AM 09:00 ~ PM 06:00<br>
                    점심: PM 12:00 ~ PM 01:00<br>
                    토, 일요일, 공휴일 휴무
                  </td>
                </tr>
              </table>
              <div>
                  <a href="/farmstory/board/list.do?gr=community&cate=qna"><img src="./img/main_sub2_cs_bt1.png" alt="1:1고객문의" /></a>
                  <a href="/farmstory/board/list.do?gr=community&cate=faq"><img src="./img/main_sub2_cs_bt2.png" alt="자주묻는 질문" /></a>
                  <a href="#"><img src="./img/main_sub2_cs_bt3.png" alt="배송 조회" /></a>
              </div>
          </div>

          <div class="account">
            <img src="./img/main_sub2_account_tit.png" alt="계좌안내" />
            <p>
		   	 기업은행 123-456789-01-01-012<br />
		            국민은행 01-1234-56789<br />
		              우리은행 123-456789-01-01-012<br />
		              하나은행 123-456789-01-01<br />
		              예 금 주 (주)팜스토리
            </p>
          </div>
          <div class="notice"></div>

        </div>
      </main>
      <%@ include file="_footer.jsp" %>
      