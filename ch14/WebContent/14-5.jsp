<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="sub1.USER" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setCharacterEncoding("UTF-8");
	String seq =request.getParameter("seq");
	
	final String HOST = "jdbc:mysql://192.168.0.156:3306/ksw";
	final String USER = "ksw";
	final String PASS = "1234";
	
	Connection conn = null; 
	Statement stmt = null;
	ResultSet rs = null;
	USER user = null;
	
	try{
				
	// 1단계
		Class.forName("com.mysql.jdbc.Driver");
	
	// 2단계
		conn = DriverManager.getConnection(HOST, USER, PASS);
	// 3단계
		stmt = conn.createStatement();
	// 4단계
		String sql = "SELECT * FROM `USER` WHERE seq="+seq;
		rs = stmt.executeQuery(sql); // rs로 결과반환
	// 5단계
		if(rs.next()){	  // while 대신 if 가능한 이유 - 한개의 인스턴스만 필요함(반복x)
			user=new USER();
			user.setSeq(rs.getInt(1));
			user.setUid(rs.getString(2));
			user.setName(rs.getString(3));
			user.setHp(rs.getString(4));
			user.setAddr(rs.getString(5));
			user.setPos(rs.getString(6));
			user.setDep(rs.getInt(7));
			user.setRdate(rs.getString(8));
	
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally{
		rs.close();
		stmt.close();
		conn.close();
	}



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14-5</title>
</head>
<body>
	<h3>직원수정</h3>
	<form action="./14-6.jsp">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" readonly="readonly" value="<%=user.getUid() %>" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="<%=user.getName() %>" /></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="hp" maxlength="13" value="<%=user.getHp() %>" /></td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<select name="addr">
					<option>서울</option>	
					<option>대전</option>	
					<option>대구</option>	
					<option>부산</option>	
					<option>광주</option>	
					</select>
				</td>
			</tr>
			<tr>
				<td>직급</td>
				<td>
					<select name="pos" >
					<option>사원</option>	
					<option>대리</option>	
					<option>차장</option>	
					<option>과장</option>	
					<option>부장</option>	
					</select>
				</td>
			</tr>
			<tr>
				<td>부서</td>
				<td>
					<select name="dep">
					<option value="101">경영지원부</option>	
					<option value="102">인사부</option>	
					<option value="103">개발부</option>	
					<option value="104">영업1부</option>	
					<option value="105">영업2부</option>	
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="수정" /></td>
			</tr>
		
		</table>
	
	</form>
</body>
</html>