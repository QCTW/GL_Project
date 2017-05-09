$(function() {
	if (localStorage.getItem("mail") == null) {
		localStorage.setItem("needCo", "true");
		document.location.href = "login.html";
	}
});

function pseudo(result) {
	var pseudo = JSON.stringify(result[0]);
	var role = JSON.stringify(result[1]);
	localStorage.setItem("pseudo", pseudo);
	localStorage.setItem("role", role);
	$("#pseudo").html(localStorage.getItem("mail"));

}
function logout() {
	// localStorage.clear();
	localStorage.removeItem("mail");
	localStorage.removeItem("role");
	getServerData("ws/login/logout", null);
	document.location.href = "login.html";

}
/*
function uploadMPD() {
	var file = $("#uploadMPD")[0].files[0];
	console.log(file);

}
function uploadFlights() {
	var file = $("#uploadMPD")[0].files[0];
	console.log(file);

}
*/
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
				+ sub(JSON.stringify(result[i].task.startTime))
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
				+ "<td>"
				+ sub(JSON.stringify(result[i].task.mroId))
				+ "</td>"
				+ "<td><button onclick='getSTask("
				+ sub(JSON.stringify(result[i].task.id))
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
				+ sub(JSON.stringify(result[i]['planeId']))
				+ "</td>"
				+ "<td>"
				+ sub(JSON.stringify(result[i]['planeType']))
				+ "</td>"
				+ "<td><button onclick='getPlane("
				+ sub(JSON.stringify(result[i]['planeId']))
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


var planeList;
// CREATE TASK MENU JS

function callViewTask(id) {
	getServerData("ws/task/" + id, viewTask)
}
function viewTask(task) {
	console.log(task);
	$('#viewTaskGen')
			.html('Task n°' + sub(JSON.stringify(task.taskGeneric.id)));
	$('#ata').html(+sub(JSON.stringify(task.taskGeneric.ataCategory)));
	$('#description').html(sub(JSON.stringify(task.taskGeneric.description)));
	$('#periodicity').html(sub(JSON.stringify(task.taskGeneric.periodicity)));
	$('#hangerNeed').html(
			sub(JSON.stringify((task.taskGeneric.hangarNeed == true))) ? 'yes'
					: 'no');
	$('#length').html(sub(JSON.stringify(task.taskGeneric.duration)));
	$('#planeType2').html(sub(JSON.stringify(task.taskGeneric.planeType)));
	console.log(task.taskGeneric.planeType);

	// $('#viewTaskGen').html('Task n°'+taskId);
}
// ----------------------------------------

$(function() {
	getServerData("ws/task/all", getAllTasks);
	// getServerData("ws/plane/all",getAllPlanes);
	getServerData("ws/plane/all", showPlanes);
});
$(function() {

});
$(function() {
	$("#pseudo").html(localStorage.getItem("mail"));
	// getServerData("ws/login/getUser",pseudo);
});
