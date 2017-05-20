nsApp.controller('MusicController',function($scope,$routeParams) {  
	var category = $routeParams.category;
	$scope.category = category;
	$scope.categoryName = ['佛歌','佛乐','经咒','梵呗','听书'][category/10-1];
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
						+"<td>"+music.filesize+"</td>"
						+"<td>"+music.soundsize+"</td>"
						+"<td>"+music.collects+"</td>"
						+"<td>"+music.plays+"</td>"
						+"<td><a class='musicupdate-btn' data-musicid='"+music.id+"'><i style='font-size:30px;' class='iconfont'>&#xe641;</i></a></td>"
						+"<td><a class='musicdelete-btn' data-musicid='"+music.id+"'><i style='font-size:30px;' class='iconfont'>&#xe642;</i></a></td>"
				        +"</tr>";
				});
				$(".table_data").html(table_data);
				$(".table_datas_count").html('共有'+data.pager.count+'首音乐');
				page.refresh(data.pager);
				$('.name-btn').click(function(){
					var index = $(this).data('index');
					var music = data.musics[index];
					var filename = music.filename;
					var audioObj = $('audio').attr('src',cosurl+'/'+category+"/"+filename)[0];
					audioObj.onplaying = function(){
						$.ajax({
							url:'/music/update/soundsize',
							data:{
								'id':music.id,
								'soundsize':audioObj.duration
							},
							dataType:'json'
						});	
					}
					audioObj.play();
				});
				$('.musicupdate-btn').click(function(){
					window.location.href = "#/music/update?category="+category+"&id="+$(this).data('musicid');
				});
				$('.musicdelete-btn').click(function(){
					var id = $(this).data('musicid');
					toIF("确定需要删除么？",function(){
						var index = layer.load('',{shade: [0.5, '#393D49']});
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
							},
							complete:function(){
				            	layer.close(index);
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
