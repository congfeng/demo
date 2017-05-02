nsApp.controller('MusicController',function($scope,$routeParams) {  
	var category = $routeParams.category;
	$scope.category = category;
	$scope.categoryName = ['佛歌','梵乐','经咒','赞偈','禅音'][category/10-1];
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
					table_data += "<tr><td>"+(pageNo*10-10+i+1)+"</td>"
						+"<td><a href='javascript:void(0);' class='name-btn' data-index='"+i+"'>"+music.name+"</a></td>"
						+"<td>"+music.author+"</td>"
						+"<td>"+music.size+"</td>"
						+"<td>"+music.collects+"</td>"
						+"<td><a class='musicupdate-btn' data-musicid='"+music.id+"'><i style='font-size:30px;' class='iconfont'>&#xe641;</i></a></td>"
						+"<td><a class='musicdelete-btn' data-musicid='"+music.id+"'><i style='font-size:30px;' class='iconfont'>&#xe642;</i></a></td>"
				        +"</tr>";
				});
				$(".table_data").html(table_data);
				$(".table_datas_count").html('共有'+data.pager.count+'首音乐');
				page.refresh(data.pager);
				$('.name-btn').click(function(){
					var musicUrl = "http://lx-music.oss-cn-beijing.aliyuncs.com/"+category+"/";
					var index = $(this).data('index');
					$('audio').attr('src',musicUrl+data.musics[index].filename)[0].play();
				});
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
