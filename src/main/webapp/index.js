
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}
$(function(){
	getServerData("ws/login/connectMcc",null);
});