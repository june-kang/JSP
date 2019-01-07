<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta charset="UTF-8">
<title>3-3</title>
</head>
<body>
	<h3>3.회원가입처리</h3>
	
	<table border="1">
    <tr>
      <td>이름</td>
      <td><%= request.getParameter("name") %></td> 
    </tr>
    <tr>
      <td>성별</td>
      <td>
        <%= request.getParameter("gender") %>
      </td>
      </tr>
      <tr>
        <td>취미</td>
        <td>
        	<%
        		String[] hobbies = request.getParameterValues("hobby");
        		for(String hobby : hobbies){
        			out.print(hobby+", ");
        		}
        	%>
        </td>
      </tr>
      <tr>
        <td>지역</td>
        <td>
          <%= request.getParameter("addr") %>
        </td>
      </tr>
    </table>
</body>
</html>