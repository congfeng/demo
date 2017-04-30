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
			var notice = data.notice;
			if(_.isEmpty(notice)){
				showAlert('公告不存在');
				return ;
			}
			$('#title').val(notice.title);
			$('#content').val(notice.content);
			createTimeFormat = notice.createTimeFormat;
			if(!_.isEmpty(notice.richText)){
				if(_.startsWith(data.UploadBasePath,'http')){
					$.ajax({
						url:'/demo/crossdomain/convert',
						data:{'remoteUrl':data.UploadBasePath+notice.richText},
						success:function(richText){
							ue.ready(function(){
								ue.setContent(richText);
						    });
						}
					});	
				}else{
					$.ajax({
						url:data.UploadBasePath+notice.richText,
						//dataType:'json',
						success:function(richText){
							ue.ready(function(){
								ue.setContent(richText);
						    });
						}
					});
				}
			}
		}
	});
	$(".noticeupdate-btn").click(function(){
		if(_.isEmpty($("#title").val())){
			layer.open({
				content : '公告标题不能为空',
				btn : [ '确定' ]
			});
			return;
		}
		$("#noticeupdateform").ajaxSubmit({
			type:'post',
            url:'/notice/update',
            success:function(data){
              	if(data&&data.s == 0){
					return;
				}
				showAlert('保存成功');
				window.location.href = "#/noticemanage?type="+type;
            }
		});
	});
	
}); 