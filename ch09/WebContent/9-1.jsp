<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>9-1</title>
</head>
<body>
	<h3>1.쿠키</h3>
	<%
		// 쿠키 생성
		Cookie ck1 = new Cookie("NAME", "홍길동");
		ck1.setMaxAge(60*3); //3분 수명
		
		response.addCookie(ck1);	
	%>
	<h4>쿠키 생성해서 클라이언트로 전송완료!</h4>
</body>
</html>