nsApp.controller('MusicUpdateController',function($scope,$routeParams) { 
	var id = $routeParams.id; 
	var category = $routeParams.category;
	$scope.id = id;
	$scope.category = category;
	$scope.categoryName = ['佛歌','佛乐','经咒','梵呗','听书'][category/10-1];
	
	$.ajax({
		url:'/music/find',
		data:{'id':id},
		dataType:'json',
		success:function(data){
			if(data&&data.s == 0){
				return;
			}
			var music = data.music;
			$('#name').val(music.name);
			$('#author').val(music.author);
			$('#filename').val(music.filename);
			$('audio').attr('src',cosurl+'/'+category+"/"+music.filename);
		}
	});
	$("#update-btn").click(function(){
		if(_.isEmpty($("#name").val())){
			layer.open({
				content : '歌曲名称不能为空',
				btn : [ '确定' ]
			});
			return;
		}
		var index = layer.load('',{shade: [0.5, '#393D49']});
		$("#musicform").ajaxSubmit({
			type:'post',
            url:'/music/update',
            success:function(data){
              	if(data&&data.s == 0){
					return;
				}
				showAlert('保存成功');
				window.location.href = "#/music?category="+category;
            },
            complete:function(){
            	layer.close(index);
            }
		});
	});
	
}); 