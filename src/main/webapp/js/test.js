$(function(){
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url: '/profile/login',
		data: {username:'admin',password:'admin'},
		success: function(data){
			console.log(data);
		}
	});
})
