nsApp.controller('MusicController',function($scope,$routeParams) {  
	var id = $routeParams.id; 
	var category = $routeParams.category;
	$scope.id = id;
	$scope.category = category;
	$scope.categoryName = ['佛歌','梵乐','经咒','赞偈','禅音'][category/10-1];
	console.log('id='+id);
	console.log('category='+category);
	var page;
	var query = function(pageNo){
		$.ajax({
			url:'/music/list',
			data:{'pageNo':pageNo,'category':category},
			dataType:'json',
			success:function(data){
				$(".table_data").html("");
				$(".table_datas_count").html("");
				page.clear();
				if(data.musics ==""){
					$(".table_datas_count").html("此条件下没有数据");
					$(".pagination").hide();
					return;
				}
				$(".pagination").show();
				var table_data = "";
				$.each(data.musics,function(i,music){
					table_data += "<tr><td>"+(i+1)+"</td>"
						+"<td>"+music.name+"</td>"
						+"<td>"+music.author+"</td>"
						+"<td>"+music.collects+"</td>"
						+"<td><a class='musicupdate-btn' data-musicid='"+music.id+"'><i style='font-size:30px;' class='iconfont'>&#xe641;</i></a></td>"
						+"<td><a class='musicdelete-btn' data-musicid='"+music.id+"'><i style='font-size:30px;' class='iconfont'>&#xe642;</i></a></td>"
				        +"</tr>";
				});
				$(".table_data").html(table_data);
				$(".table_datas_count").html('共有'+data.pager.count+'首音乐');
				page.refresh(data.pager);
				$('.musicupdate-btn').click(function(){
					window.location.href = "#/music/update?category="+category+"&id="+$(this).data('musicid');
				});
				$('.musicdelete-btn').click(function(){
					var id = $(this).data('musicid');
					toIF("确定需要删除么？",function(){
						$.ajax({
							url:'/music/delete',
							data:{'id':id},
							dataType:'json',
							success:function(data){
								if(data&&data.s == 0){
									return;
								}
								showAlert('删除成功');
								query(1);
							}
						});	
					});
				});
			}
		});
	}
	page = new Pagination(query);
	query(1);
}); 
