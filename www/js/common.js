
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        // return unescape(r[2]);
        return decodeURIComponent(r[2]);
    }
    return null;
}

document.addEventListener("deviceready", function(){
    
}, false);

$(function(){
    
});

window.onerror = function(msg, url, line) {  
   var idx = url.lastIndexOf("/");  
   if(idx > -1) {  
    url = url.substring(idx+1);  
   }  
   //alert("ERROR in " + url + " (line #" + line + "): " + msg);  
   return false;  
};  
