<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	if(true){	// 로그인을 안했을 경우
		//response.sendRedirect("./login.jsp");
		pageContext.forward("./login.jsp");
		
	}else{	// 로그인 했을 경우
		//response.sendRedirect("./list.jsp");
		pageContext.forward("./list.jsp");
	}

%>