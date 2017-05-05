$(function() {
	if (localStorage.getItem("mail") == null) {
		localStorage.setItem("needCo", "true");
		document.location.href = "login.html";
	}
});
function getServerData(url, success) {
	$.ajax({
		dataType : "json",
		url : url
	}).done(success);
}

$(function() {
	getServerData("ws/task/all", getAllTasks);
});
 var taskList;
 
function getAllTasks(result){
	taskList = result;
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

function sub(r) {
	return r.toString().substring(1, r.toString().length - 1);
}

//<0
function taskToAssign(){
	var tr = "";
	for (var i = 0; i < taskList.length; i++) {
		if(taskList[i].task.mroId < 0 ){
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
			+ "<td><button onclick='getSTask("
			+ sub(JSON.stringify(result[i].task.id))
			+ ")' "
			+ "class='btn icon-btn btn-primary'>  "
			+ "<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> "
			+ "</button></td>" + "</tr>";
		
		}
	}
	$('#tbody').html(tr);
}
//>0 and dateNow < Enddate
function inProgress(){
	var tr = "";
	for (var i = 0; i < taskList.length; i++) {
		if(taskList[i].task.mroId > 0 ){
			var today = new Date();
			var endTime = new Date(taskList[i].task.endTime.substring(0,5),taskList[i].task.endTime.substring(6,8),
					taskList[i].task.endTime.substring(9,11),taskList[i].task.endTime.substring(12,14),
					taskList[i].task.endTime.substring(15,17));
			if(today < endTime){
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
				+ "<td><button onclick='getSTask("
				+ sub(JSON.stringify(result[i].task.id))
				+ ")' "
				+ "class='btn icon-btn btn-primary'>  "
				+ "<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> "
				+ "</button></td>" + "</tr>";
			}
		
		}
	}
	$('#tbody').html(tr);
}
	

//>0 and dateNow > EndDate
function done(){
	var tr = "";
	for (var i = 0; i < taskList.length; i++) {
		if(taskList[i].task.mroId > 0 ){
			var today = new Date();
			var endTime = new Date(taskList[i].task.endTime.substring(0,5),taskList[i].task.endTime.substring(6,8),
					taskList[i].task.endTime.substring(9,11),taskList[i].task.endTime.substring(12,14),
					taskList[i].task.endTime.substring(15,17));
			if(today >= endTime){
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
				+ "<td><button onclick='getSTask("
				+ sub(JSON.stringify(result[i].task.id))
				+ ")' "
				+ "class='btn icon-btn btn-primary'>  "
				+ "<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> "
				+ "</button></td>" + "</tr>";
			}
		
		}
	}
	$('#tbody').html(tr);
}