$(document).ready(function(){
	var comments = $('.comments');
	var comment = $('.comments>.comment');
	var empty = $('.comments>.empty');
	var parent = $('#seq').val();


	$.ajax({
		url:'farmstory/board/comment.do?parent='+parent,
		type:'GET',
		dataType:'json',
		success: function(result){ // result=[{...},{...}...] : json 객체
			if(result.length == 0){
				comment.remove();
			} else {
				empty.remove();
			}

			for(var i in result){
				//alert('content : '+result[i].content);

				var delUrl = "./proc/commentDelete.jsp?seq="+result[i].seq+"&parent="+result[i].parent;

				if(i > 0){ // 인덱스가 1이상, 두번째 댓글부터
					var commentCloned = comment.clone();
					commentCloned.find('span > .nick').text(result[i].nick);
					commentCloned.find('span > .date').text(result[i].rdate.toString().substring(2,10)); // comment 자식의 span의 date 클래스 찾아서 값입력
					commentCloned.find('textarea').text(result[i].content);
					commentCloned.find('.del').attr('href', delUrl);  // 속성함수

					comments.append(commentCloned);
				} else{
					comment.find('span > .nick').text(result[i].nick);
					comment.find('span > .date').text(result[i].rdate.toString().substring(2,10)); // comment 자식의 span의 date 클래스 찾아서 값입력
					comment.find('textarea').text(result[i].content);
					comment.find('.del').attr('href', delUrl);

				}
			}
		}						
	});

});


$(function(){
	var btnComment = $('.comment_write .submit');
	btnComment.click(function(){

		var parent 	= $('.comment_write input[name=parent]').val();
		var uid		= $('.comment_write input[name=uid]').val();
		var nick	= $('.comment_write input[name=nick]').val();
		var content	= $('.comment_write textarea').val();

		var json = {"parent":parent, "uid":uid, "content":content, "nick":nick};
		/*
		var json = {
			parent : parent,
			uid : uid,
			content : content,
			nick:nick
		};

		 */
		$.ajax({
			url: '/farmstory/comment.do',
			type:'POST',
			dataType:'json',
			data:json,
			success:function(result){

				var comments = $('.comments');
				var comment = $('.comments>.comment');

				var commentCloned = comment.clone();
				commentCloned.find('span > .nick').text(result.nick);
				commentCloned.find('span > .date').text(result.date);
				commentCloned.find('textarea').text(result.content);
				comments.append(commentCloned);

			}
		});

		return false // 클릭하면 form태그 액션 실행 "#"되어있어서 return이 제대로 안됨 

	});
});