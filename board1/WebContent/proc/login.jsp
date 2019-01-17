<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	String redirectUrl = ""; // null값으로 초기화

	Connection conn = DBConfig.getConnection(); // 싱글톤 객체, 단하나의 인스턴스로 사용
		
	PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_lOGIN);
	
	pstmt.setString(1,uid);
	pstmt.setString(2,pass);
	
	ResultSet rs = pstmt.executeQuery();
		
	if(rs.next()){
		// 아이디와 비밀번호가 일치하는 회원이 테이블에 있을 경우
		// 세션 저장
		MemberVO vo = new MemberVO();
		vo.setSeq(rs.getInt(1));
		vo.setUid(rs.getString(2));
		vo.setPass(rs.getString(3));
		vo.setName(rs.getString(4));
		vo.setNick(rs.getString(5));
		vo.setEmail(rs.getString(6));
		vo.setHp(rs.getString(7));
		vo.setGrade(rs.getInt(8));
		vo.setZip(rs.getString(9));
		vo.setAddr1(rs.getString(10));
		vo.setAddr2(rs.getString(11));
		vo.setRegip(rs.getString(12));
		vo.setRdate(rs.getString(13));
		
		session.setAttribute("member",vo);
		
		redirectUrl = "./list.jsp";
	
		
	} else{
		// 아이디와 비밀번호가 일치하는 회원이 없을경우
		redirectUrl = "../login.jsp?result=fail"; // 결과값 들고가라
	}
	
	rs.close();
	pstmt.close();
	conn.close();

	response.sendRedirect(redirectUrl); // 결과값에 따라 다른 url 페이지로 이동함
	
%>