nsApp.controller('MusicUpdateController',function($scope,$routeParams) { 
	var id = $routeParams.id; 
	var category = $routeParams.category;
	$scope.id = id;
	$scope.category = category;
	$scope.categoryName = ['佛歌','梵乐','经咒','赞偈','禅音'][category/10-1];
	
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
			var musicUrl = "http://lx-music.oss-cn-beijing.aliyuncs.com/"+category+"/";
			$('audio').attr('src',musicUrl+music.filename);
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
		$("#musicform").ajaxSubmit({
			type:'post',
            url:'/music/update',
            success:function(data){
              	if(data&&data.s == 0){
					return;
				}
				showAlert('保存成功');
				window.location.href = "#/music?category="+category;
            }
		});
	});
	
}); 