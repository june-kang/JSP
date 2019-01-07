<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2-2</title>
</head>
<body>
<h2>구구단</h2>
<table border=1>
<tr>

<td>2단</td>
<td>3단</td>
<td>4단</td>
<td>5단</td>
<td>6단</td>
<td>7단</td>
<td>8단</td>
<td>9단</td>
</tr>
<% for(int i=1; i<10; i++){ %>
<tr>
<% for(int j=2; j<10; j++) { %>
<td><%=j %>x<%=i %>=<%=i*j %></td>
<%} %>
</tr>
<%} %>

</table>
</body>
</html>