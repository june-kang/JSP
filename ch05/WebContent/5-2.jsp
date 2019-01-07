<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>5-2</title>
</head>
<body>
	<h3>2.내장객체 범위값 확인</h3>
	<%
		String pName = (String)pageContext.getAttribute("name");
		String rName = (String)request.getAttribute("name");
		String sName = (String)session.getAttribute("name");
		String aName = (String)application.getAttribute("name");
	%>
	<h4>pageContext Name 값 : <%= pName %></h4>
	<h4>request Name 값 : <%= rName %></h4>
	<h4>session Name 값 : <%= sName %></h4>
	<h4>application Name 값 : <%= aName %></h4>
</body>
</html>