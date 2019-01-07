<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10-2</title>
</head>
<body>
	<h3>2.세션값 출력</h3>
	<%
		String name = (String)session.getAttribute("NAME");	
	%>
	<h4>세션 name : <%=name %></h4>
	

</body>
</html>