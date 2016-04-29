$(function(){
	var unslider = $('.banner').unslider({
	 	dots: true,
	 	fluid: true
	});
	unslider_data = unslider.data('unslider');
	$('.unslider-arrow').click(function() {
       	var fn = this.className.split(' ')[1];
       	unslider_data[fn]();
    });
})
