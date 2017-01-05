
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
