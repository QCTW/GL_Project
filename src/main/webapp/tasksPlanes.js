$(function() {
	if (localStorage.getItem("mail") == null) {
		localStorage.setItem("needCo", "true");
		document.location.href = "login.html";
	}
});


function uploadMPD() {
	var file = $("#uploadMPD")[0].files[0];
	console.log(file);
	
}
function uploadFlights() {
	var file = $("#uploadMPD")[0].files[0];
	console.log(file);
	
}
function getServerData(url, success) {
	$.ajax({
		dataType : "json",
		url : url
	}).done(success);
}

function sub(r) {
	return r.toString().substring(1, r.toString().length - 1);
}
var task;
var idt;

// GET ALL TASK
function getAllTasks(result) {
	var tr = "";
	// console.log("in get all tasks function and tr = "+tr);
	for (var i = 0; i < result.length; i++) {
		tr += "<tr> "
				+ "<td>"
				+ sub(JSON.stringify(result[i].taskGeneric.ataCategory))
				+ "</td>"
				+ "<td>"
				+result[i].task.startTime
				+ "</td>"
				+ "<td>"
				+ sub(JSON.stringify(result[i].task.endTime))
				+ "</td>"
				+ "<td>"
				+ sub(JSON.stringify(result[i].plane.planeType))
				+ "</td>"
				+ "<td>"
				+ sub(JSON.stringify(result[i].plane.planeId))
				+ "</td>"
				+ "<td><button onclick='getSTask("
				+ idt
				+ ")' "
				+ "class='btn icon-btn btn-primary'>  "
				+ "<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> "
				+ "</button></td>" + "</tr>";
		// console.log(tr);
	}
	$('#tbody').html(tr);
}

function getSTask(id) {
	var x = parseInt(id, 10);
	document.location.href = "taskView.html?id=" + x;
}

function getAllPlanes(result) {
	console.log(result);
	//
	var tr = "";
	for (var i = 0; i < result.length; i++) {
		tr += "<tr> "
			+ "<td>"
			+ sub(JSON.stringify(result[i].planeType))
			+ "</td>"
			+ "<td>"
			+ sub(JSON.stringify(result[i].planeId))
			+ "</td>"
			+ "<td><button onclick='getPlane("
			+ idt
			+ ")' "
			+ "class='btn icon-btn btn-primary'>  "
			+ "<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> "
			+ "</button></td>" + "</tr>";
	}
	$('#planeTbody').html(tr);
	var planes = $('#planeslist').DataTable({});
}

function formatDate(date) {
	var monthNames = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];

	var day = date.getDate();
	var monthIndex = date.getMonth();
	var year = date.getFullYear();

	return year + ' ' + monthNames[monthIndex] + ' ' + day;
}

function pseudo(result) {
	var pseudo = JSON.stringify(result[0]);
	var role = JSON.stringify(result[1]);
	localStorage.setItem("pseudo", pseudo);
	localStorage.setItem("role", role);
	$("#pseudo").html(localStorage.getItem("mail"));

}
function logout() {
	localStorage.clear();
	getServerData("ws/login/logout", null);
	document.location.href = "login.html";

}

$(function() {
	getServerData("ws/task/all", getAllTasks);
});
$(function() {
	getServerData("ws/plane/all",getAllPlanes);
});
$(function() {
	$("#pseudo").html(localStorage.getItem("mail"));
	// getServerData("ws/login/getUser",pseudo);
});
