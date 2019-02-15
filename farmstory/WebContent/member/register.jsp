<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){ // 4자 이상 입력했을때 중복확인하는 키보드이벤트
				 $('input[name=uid]').keyup(function(){
					 
					 var tag = $(this);
					 var value = $(this).val();
					 // console.log('입력값 :'+ value); // chrome console 창에서 확인가능
					 
					 if(value.length >= 4){
						 // ajax 통신
						 // alert('실행!!!'); // 부분 테스트 하면서 개발하기
						 var api = '/farmstory/member/userCheck.do?uid='+value;
						 $.getJSON(api, function(data){ //json전용함수(요청주소, 핸들러)
							 if(data.result==1){
									$('.resultId').css('color','red').text('이미 사용중인 아이디 입니다.');
									tag.focus();
									isUidOk = false;
								}else{
									$('.resultId').css('color','green').text('사용 가능한 아이디 입니다.');
									isUidOk = true;
								}
							 
						 }); 
					 }
					 
				 });
			});
		
		</script>

		<div id="member">
			<section class="register">
				<form action="/farmstory/member/register.do" method="POST">
					<section>
						<table>
							<caption>사이트 이용정보 입력</caption>
							<tr>
								<td>아이디</td>
								<td>
									<input type="text" name="uid" placeholder="아이디를 입력" required />
									<span class="resultId"></span>
								</td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="pw1" placeholder="비밀번호를 입력" required /></td>
							</tr>
							<tr>
								<td>비밀번호확인</td>
								<td><input type="password" name="pw2" placeholder="비밀번호를 확인" required /></td>
							</tr>
						</table>
					</section>
					<section>
						<table>
							<caption>개인정보 입력</caption>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" placeholder="이름을 입력" required /></td>
							</tr>
							<tr>
								<td>별명</td>
								<td>
									<span class="info">공백없이 한글, 영문, 숫자만 입력가능</span>
									<div>
										<input type="text" name="nick" placeholder="별명을 입력" required />
									</div>
									<span class="resultNick"></span>
								</td>
							</tr>
							<tr>
								<td>EMAIL</td>
								<td><input type="email" name="email" placeholder="이메일을 입력" 	required /></td>
							</tr>
							<tr>
								<td>휴대폰</td>
								<td><input type="text" name="hp" placeholder="-포함 13자리를 입력" maxlength="13" required /></td>
							</tr>
							<tr>
								<td>주소</td>
								<td>
									<div>
										<input type="text" name="zip" id="zip" placeholder="우편번호" readonly />
										<button type="button" class="btnFind">주소검색</button>
									</div>
									<div>
										<input type="text" name="addr1" id="addr1" size="50" placeholder="주소를 검색하세요." readonly />
									</div>
									<div>
										<input type="text" name="addr2" id="addr2" size="50" placeholder="상세주소를 입력하세요." />
									</div>
								</td>
							</tr>
						</table>
			
					</section>
					<div>
						<a href="#" class="cancel">취소</a> 
						<input type="submit" class="join" value="회원가입" />
					</div>
				</form>
			</section>
		</div>
<%@ include file="../_footer.jsp" %>