<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10-1</title>
</head>
<body>
	<h3>1.세션</h3>
	<%
		session.setAttribute("NAME","홍길동"); // key-value
		session.setMaxInactiveInterval(60*3);
	%>
	<h4>서버에 세션값 저장완료!</h4>
	<a href="./10-2.jsp">세션 정보 확인</a>
</body>
</html>