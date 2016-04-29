function log(data) {
	try{
		console.log(data);
	}catch(e){
		
	}
}

/**
 * 获取地址栏参数
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		// return unescape(r[2]);
		return decodeURIComponent(r[2]);
	}
	return null;
}

$.ajaxSetup({type: "POST",dataType:"json",success:function(data){
	log(data);
}});

$(function(){
	FastClick.attach(document.body);
	$(document).ajaxSuccess(function(e,xhr,c){
		if(!xhr.responseJSON){
			return ;
		}
		if(xhr.responseJSON.s == 0){
			if(xhr.responseJSON.t == 1){
				log('未登录');
			}else{
				log(xhr.responseJSON.m);
			}
		}
	});
})
