$(document).ready(function(){
	
	$('.btnNext').click(function(){
		var rs1 = $('input[name=chk1]').is(':checked');
		var rs2 = $('input[name=chk2]').is(':checked');
		
		if(!rs1){
			alert('이용약관 동의를 하셔야 합니다.');
			return false;
				}else if(!rs2){
					alert('개인정보 동의를 하셔야 합니다.');
					return false;
				}else{
					return true;						
				}
		});
	});