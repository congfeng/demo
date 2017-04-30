Messenger.options = {
    extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
    theme: 'air'
}
$(function(){
	var im4socketio;
	$.ajax({
		url:'/profile',
		dataType:'json',
		success:function(data){
			var profile = data.profile;
			$("#username-text").text(profile.name);
			$('body').show();
		}
	});

	$("#logout-button").click(function(){
		$.ajax({
			url:'/profile/logout',
			dataType:'json',
			success:function(data){
				window.location.href = "login.html";
			}
		})
	});
	
	$("#resetpassword-button").click(function(){
		if($("#newpassword").val() == ""){
			layer.open({
				content : '密码不能为空',
				btn : [ '确定' ]
			});
			return;
		}
		if($("#renewpassword").val() !== $("#newpassword").val()){
			layer.open({
				content : '两次输入密码不一致',
				btn : [ '确定' ]
			});
			return;
		}
		$.ajax({
			url:'/admin/resetpassword',
			data:{'newpassword':$("#newpassword").val()},
			dataType:'json',
			success:function(data){
				if(data&&data.s == 0){
					layer.open({
						content : data.m,
						btn : [ '确定' ]
					});	
					return;
				}
				$('#resetpassword-modal').modal('hide');
				layer.open({
					content : '修改成功',
					btn : [ '确定' ]
				});
			}
		})
	});
	
})
