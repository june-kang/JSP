<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14-1</title>
</head>
<body>
	<h3>직원등록</h3>
	<form action="./14-2.jsp">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="UID" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="hp" maxlength="13" /></td>
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
					<select name="pos">
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
				<td colspan="2" align="right"><input type="submit" value="등록" /></td>
			</tr>
		
		</table>
	
	</form>
</body>
</html>