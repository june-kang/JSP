<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11-3</title>
</head>
<body>
	<h3>3.표현언어 연산자</h3>
	<%
	String str1 = null;
	String str2 = "";
	String str3 = "Hello";
	
	int num1 = 1;
	int num2 = 2;
	
	request.setAttribute("str1", str1);
	request.setAttribute("str2", str2);
	request.setAttribute("str3", str3);
	
	request.setAttribute("num1", num1);
	request.setAttribute("num2", num2);
	%>
	<table border="1">
		<tr>
			<td>empty str1 결과</td>
			<td>${empty str1 }</td>
		</tr>
		<tr>
			<td>empty str2 결과</td>
			<td>${empty str2 }</td>
		</tr>
		<tr>
			<td>empty str3결과</td>
			<td>${empty str3 }</td>
		</tr>
		<tr>
			<td>eq str3결과</td>
			<td>${str3 eq "Hello" }</td>
		</tr>
	</table>
	
	<ul>
		<li>num1 > num2 : ${ num1 gt num2 }</li>
		<li>num1 >= num2 : ${ num1 ge num2 }</li>
		<li>num1 < num2 : ${ num1 lt num2 }</li>
		<li>num1 <= num2 : ${ num1 le num2 }</li>
		<li>num1 == num2 : ${ num1 eq num2 }</li>
		<li>num1 != num2 : ${ num1 ne num2 }</li>
		
	</ul>
</body>
</html>