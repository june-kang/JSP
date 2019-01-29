<%@tag import="java.util.Calendar"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%
	Calendar cal = Calendar.getInstance();  // Calendar는 싱글톤 클래스 - 객체생성이 아니라 객체를 구해옴
%>
<h4>현재날짜 : <%=cal.get(Calendar.YEAR) %>년 <%=cal.get(Calendar.MONTH)+1 %>월 <%=cal.get(Calendar.DATE)%>일 </h4>