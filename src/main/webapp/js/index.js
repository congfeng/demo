$(function(){
	$('.p_line').click(function(){
		var pt = $(this).find('.p_t').text();
		window.location.href = 'product.html?pt='+pt;
	});
	$('.msgboard_link').click(function(){
		window.location.href = 'msgboard.html';
	});
})
