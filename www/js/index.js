
$(document).on("pageInit","#index",function(e, pageId, $page) {
    $('#logout').click(function(){
        window.localStorage.userId = '';
        window.localStorage.clientId = '';
        window.location.href = "login.html";
    });
    //var userId = window.localStorage.userId;
	//var clientId = window.localStorage.clientId;
	//if(!userId||!clientId){
	  //  window.location.href = "login.html";
	//}
});

$(document).on("pageInit",function(e, pageId, $page) {
    //alert(window.location.search);
    //getQueryString
	console.log(pageId);
	$page.append(pswpHTML());
	initPhotoSwipeFromDOM('.my-gallery');
});
