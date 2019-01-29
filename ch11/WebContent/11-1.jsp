<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11-1</title>
</head>
<body>
	<h3>1.표현언어</h3>
	<%
		String name = "홍길동";
		int num1 = 1;
		int num2 = 2;
		
		// request영역에 변수를 저장
		request.setAttribute("name", name);
		request.setAttribute("num1", num1);
		request.setAttribute("num2", num2);
	%>
	<h4>표현식</h4>
	<ul>
		<li>name = <%= name %></li>
		<li>num1 + num2 = <%= num1 + num2 %></li>
	</ul>
	
	<h4>표현언어</h4>
	<ul>
		<li>name = ${name}</li>
		<li>num1 + num2 = ${num1 + num2}</li>
	</ul>
</body>
</html>