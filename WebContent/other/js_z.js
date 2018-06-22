/*鼠标放在导航栏显示内容，否则隐藏起来*/

$(function(){
	var w_w=$(window).width();
	var m_l=(1920-w_w)/2;
	
	$('.new_l li:last-child').css({'background':'none','margin-right':0,'width':260+'px','padding-right':0});
	$('.sol_m li:last-child').css('padding-right',0);
	$('#sol_l .pro_l  dl:last-child').css('margin-right',0);
	
	
	$('.head .nav li:first-child').css('margin-left',0);
	$('.head .nav li').mouseenter(function(){
		$(this).children('.er').show();	
	});
	$('.head .nav li').mouseleave(function(){
		$(this).children('.er').hide();	
	});
	$('.lei li:last-child').css('margin-right',0+'px');
	$('.pro_l li:nth-child(4n)').css('width',301+'px');
	
})
