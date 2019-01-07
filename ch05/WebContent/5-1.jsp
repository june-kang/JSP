<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>5-1</title>
</head>
<body>
	<h3>1.내장객체 범위</h3>
	<%
		pageContext.setAttribute("name","김유신");
		request.setAttribute("name","김춘추");
		session.setAttribute("name","장보고");
		application.setAttribute("name","이순신");
		
		pageContext.forward("./5-2.jsp");
	%>
	<h4>내장객체 값 셋팅완료!!</h4>
	<a href="./5-2.jsp">내장객체 셋팅 값 확인하기</a>
</body>
</html>