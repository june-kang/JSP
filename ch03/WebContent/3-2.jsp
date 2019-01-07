<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3-2</title>
</head>
<body>
  <h3>2. 파라미터 수신</h3>
  <%
     String uid = request.getParameter("uid");
     String pass = request.getParameter("pass");
  %>
  <ul>
    <li>아이디 : <%= uid %></li>
    <li>비밀번호 : <%= pass %></li>  
  </ul>
  <a href="./3-1.jsp">로그인하기</a>
</body>
</html>