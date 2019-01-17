/**
 * 
 */
/**
* 아이디, 닉네임, 이메일, 휴대폰번호 중복체크 <script>태그 안들어감
*/

// 중복여부 상태변수. 전역변수로 사용해야 register.jsp 에서도 사용가능
var isUidOk = false;
var isNickOk = false;
var isEmailOk = false;
var isHpOk = false;
					

if(!isUidOk){
	alert('이미 사용중인 아이디입니다.');
	return false;
}

if(!isNickOk){
	alert('이미 사용중인 닉네임입니다.');
	return false;
}
						
if(!isEmailOk){
	alert('이미 사용중인 이메일입니다.');
	return false;
}

if(!isHpOk){
	alert('이미 사용중인 전화번호입니다.');
	return false;
}

$(function(){
									
	// 아이디 중복체크
	$('input[name=uid]').focusout(function(){
		
		var tag = $(this);
		var uid = $(this).val();
		
		$.ajax({
			url:'./proc/checkuid.jsp?uid='+uid,
			type:'get',
			dataType:'json', // 자바스크립트 객체
			success:function(data){ // checkuid.jsp 에서 data json 가져옴
				
				if(data.result==1){
					$('.resultId').css('color','red').text('이미 사용중인 아이디 입니다.');
					tag.focus();
					isUidOk = false;
				}else{
					$('.resultId').css('color','green').text('사용 가능한 아이디 입니다.');
					isUidOk = true;
				}
				
			} // 요청이 성공했을때 콜백함수
		});
		
	});
									
	// 닉네임 중복체크
	$('input[name=nick]').focusout(function(){
		var tag = $(this);
		var nick = tag.val();
		
		$.ajax({
			url:'./proc/checkNick.jsp?nick='+nick,
			type:'get',
			dataType:'json',
			success:function(data){
				
				
				if(data.result==1){
					$('.resultNick').css('color','red').text('이미 사용중인 닉네임입니다.');
					tag.focus();
					isNickOk = false;
				} else{
					$('.resultNick').css('color','green').text('사용가능한 닉네임입니다.');
					isNickOk = true;
				}	
				
			}
		
		});
	});
	
	
	// 이메일 중복체크
	$('input[name=email]').focusout(function(){
		var tag = $(this);
		var email = tag.val();
		
		$.ajax({
			url:'./proc/checkEmail.jsp?email='+email,
			type:'get',
			dataType:'json',
			success:function(data){
				if(data.result==1){
					$('.resultEmail').css('color','red').text('이미 사용중인 이메일입니다.');
					tag.focus();
					isEmailOk = false;
				} else{
					$('.resultEmail').css('color','green').text('사용가능한 이메일입니다.');
					isEmailOk = true;
				}
			}
		});
		
	});
	// 휴대폰 중복체크
	$('input[name=hp]').focusout(function(){
		var tag = $(this);
		var hp = $(this).val();
		
		$.ajax({
			url:'./proc/checkHp.jsp?hp='+hp,
			type:'get',
			dataType:'json',
			success:function(data){
				if(data.result==1){
					$('.resultHp').css('color','red').text('이미 사용중인 전화번호입니다.');
					tag.focus();
					isHpOk = false;
				} else{
					$('.resultHp').css('color','green').text('사용가능한 전화번호입니다.');
					isHpOk = true;
				}
			}
		
		
		});
	});
	
});						