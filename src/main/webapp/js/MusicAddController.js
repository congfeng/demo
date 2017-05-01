nsApp.controller('MusicAddController',function($scope,$routeParams) {
	var category = $routeParams.category;
	$scope.category = category;
	$scope.categoryName = ['佛歌','梵乐','经咒','赞偈','禅音'][category/10-1];
	$("#music").fileinput({
		language: "zh",
		showCaption: false,
        showUpload: false,
        showClose: false,
        browseClass: "btn btn-success",
		browseLabel: "请选择音乐",
		removeClass: "btn btn-danger",
		removeLabel: "删除",
		maxFileCount: 1,
		//maxFileSize: 1000,
		//allowedFileExtensions: ["mp3","mp4", "gif", "png" , "bmp"],
		allowedFileTypes: ["audio"]
    });
	$("#add-btn").click(function(){
		if(_.isEmpty($("#name").val())){
			layer.open({
				content : '歌曲名称不能为空',
				btn : [ '确定' ]
			});
			return;
		}
		$("#musicform").ajaxSubmit({
			type:'post',
            url:'/music/add',
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