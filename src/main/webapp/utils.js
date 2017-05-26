$(function() {
	if (localStorage.getItem("mail") == null) {
		localStorage.setItem("needCo", "true");
		document.location.href = "login.html";
	}
});

function getServerData(url, success, failure) {
	$.ajax({
		dataType : "json",
		url : url,
		type: "GET",
		success : success,
		error : failure
	});
}
function postServerData(url, success, failure) {
	$.ajax({
		dataType : "json",
		url : url,
		type: "POST",
		success: success,
		error: failure
	});
}
function logout() {
	// localStorage.clear();
	localStorage.removeItem("mail");
	localStorage.removeItem("role");
	getServerData("ws/login/logout",document.location.href = "login.html",null);
	;
}
$(function() {
	$("#pseudo").html(localStorage.getItem("mail"));
});

/** UTILS FUNCTION **/
function stringToDate(dateStr){
	return new Date(dateStr.substring(0,4),dateStr.substring(5,7),
			dateStr.substring(8,10),dateStr.substring(11,13),
			dateStr.substring(14,16));
	
}
function dl(res,val){
	return '<dl class="row"><dt class="col-sm-3">'+res+'</dt><dd class="col-sm-9">'+val+'</dd></dl>';
}
function sub(r) {
	return r.toString().substring(1, r.toString().length - 1);
}
function dl(res,val){
	return '<dl class="row"><dt class="col-sm-3">'+res+'</dt><dd class="col-sm-9">'+val+'</dd></dl>';
}
function stringify(res){
return JSON.stringify(res);
}
function subFy(res){
return sub(stringify(res));
}
