$(function(){
  var li = $('.slider > ul > li');
  var i = 0;

  setInterval(function(){
    li.eq(i).animate({'left':'-100%'}, 1000); /*li객체의 첫번째*/
    i++;

    if(i==3){
      i = 0;
    }
    li.eq(i).css('left','100%').animate({'left':'0'}, 1000);
  }, 1000*3);

});
