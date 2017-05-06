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
var planeList;
// CREATE TASK MENU JS
function showPlanes(result) {
	console.log(result);
	planeList = result;
	//
	var tr = "";
	for (var i = 0; i < result.length; i++) {
		var str = sub(JSON.stringify(result[i].planeType)); console.log("str : "+str);
		tr += "<tr> " 
				+ "<td>"+ sub(JSON.stringify(result[i]['planeId']))+ "</td>" 
				+ "<td>"+ sub(JSON.stringify(result[i].planeType)) + "</td>"
				+ "<td><button onclick='chooseTasksByPlane(\""+sub(JSON.stringify(result[i].planeType))+"\")' class='btn icon-btn btn-success'>  "
				+ "<span class='glyphicon glyphicon-ok'></span> "
				+ "</button></td>" + "</tr>";
	}
	$('#planeTbody2').html(tr);
	$('#planeslist2').DataTable({});
}
function choosePlane() {

}
var idPlane;
function chooseTasksByPlane(planetype) {
	console.log(planetype);
	idPlane=1;
	getServerData("ws/task/genericByPlane/"+planetype, chooseTasksByPlaneAux);
	$('#returnCreateTask').append('<li><a onclick="showPlanes('+planeList+')">'+planetype+'</a></li>');
}

var tab;

function chooseTasksByPlaneAux(result) {
	
	var tr = "";
	// console.log("in get all tasks function and tr = "+tr);
	for (var i = 0; i < result.length; i++) {
		tr += "<tr> "
				+ "<td>"
				+ sub(JSON.stringify(result[i].id))
				+ "</td>"
				+ "<td>"
				+ sub(JSON.stringify(result[i].ataCategory))
				+ "</td>"
				+ "<td>"
				+ sub(JSON.stringify(result[i].planeType))
				+ "</td>"
				+ "<td><button onclick='callViewTask("
				+ result[i].id
				+ ")' class='btn icon-btn btn-primary' data-toggle='modal' data-target='#myModal'><span class='glyphicon btn-glyphicon glyphicon-eye-open'></span></button></td>"
				+ "<td><button onclick='chooseStartDate(" + sub(JSON.stringify(result[i].id)) + ")' "
				+ "class='btn icon-btn btn-success'>  "
				+ "<span class='glyphicon glyphicon-ok'></span> "
				+ "</button></td>" + "</tr>";
		// console.log(tr);
	}

	$('#createTaskBody')
			.html(
					"<table class='table table-striped'>"
							+ "<thead><tr><th>Generic task Id</th>  <th >ATA category</th> <th >Plane type</th> <th> show tasks </th> <th>Choose Task</th> </tr></thead>"
							+ "<tbody id='tbody'>" + tr + "</tbody>"
							+ "</table>");
}
var genericTaskId;
function chooseStartDate(taskId) {
	$('#returnCreateTask').append('<li><a> task n°'+taskId+'</a></li>');
	//genericTaskId = taskId;
	//var res = '<input type="date" data-date-format="DD MMMM YYYY" placeholder="dd-MM-dd HH:mm:ss" size="16" ng-model="data.action.date" />';
	//res+='<input type=time'
	var res = '<div class="control-group">';
    res+='<label class="control-label">Choose a date</label>'; 
    res+='<div class="controls input-append date form_datetime" data-date-format="yyyy MM dd - HH:ii p"  data-link-field="dtp_input1">';
    res+='<input class="form-control" size="20" id="textDate" type="text" value="" readonly>';
    res+='<span class="add-on"><i class="icon-remove"></i></span>';
	res+='<span class="add-on"><i class="icon-th"></i></span>';
    res+='</div>';
	res+='<input type="hidden" id="dtp_input1" value="" /><br/></div>';
	res+='<button type="submit" onclick="validTask()" class="btn btn-primary">Submit</button>'
	$("#createTaskBody").html(res);
	$('.form_datetime').datetimepicker({
	    //language:  'fr',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
	    showMeridian: 1
	});
	//$("#createTaskBody").load("chooseStartDate.html");
	
}


function validTask() {
	//var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
	
	alert('date : '+$('#textDate').val());
	//alert('task id Validate'+'\n plane id = '+planeId+'\n taskId = '+genericTaskId);
	document.location.href = "tasksPlanes.html";
}
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
	$('#length').html(sub(JSON.stringify(task.taskGeneric.duree)));
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
