/** DECLARE VARIABLE **/
var taskList;
var mroChosen;
var listMros;
/** END DECLARE VARIABLE **/

$(function() {
	if(localStorage.getItem("role") == "mcc"){
		getServerData("ws/task/all", allTasks,null)
	}
	else {
		$("#createTaskToggle").hide();
		$("#uploadTaskToggle").hide();
		$("#tasksToAssign").hide();
		getServerData("ws/task/all", allTasks,null)
		//$("#body").html('vous êtes un mro');
	}
	getServerData("ws/mro/all", allMros,null)
	
});


/** FUNCTIONS **/

function allTasks(result){
	taskList = result;
	getAllTasks();
}

function allMros(result){
	listMros = result;
	//getAllTasks();
}


/** SORT TASKS FUNCTION **/
function getAllTasks(){
	var line = "";
	for (var i = 0; i < taskList.length; i++) {
		line += printTask(taskList, i);
	}
	$('#tbody').html(line);
}

function getTaskToAssign(){
	var line;
	for (var i = 0; i < taskList.length; i++) {
		if(taskList[i].task.taskStatus == 1 ){
            line += printTask(taskList, i);
		}
	}
	$('#tbody').html(line);
}

function getTasksInProgress(){
	var line;
	for (var i = 0; i < taskList.length; i++) {
		if(taskList[i].task.mroId > 0 ){
			var today = new Date();
			var endTime = stringToDate(taskList[i].task.endTime);
			if(today < endTime && taskList[i].task.taskStatus == 2)
                line += printTask(taskList, i);
        }
    }
	$('#tbody').html(line);
}

function getTasksdone(){
	var tr = "";
	for (var i = 0; i < taskList.length; i++) {
		if(taskList[i].task.mroId > 0 ){
			var today = new Date();
			//var endTime = stringToDate(taskList[i].task.endTime);
			if(taskList[i].task.taskStatus == 3)
                tr += printTask(taskList, i);
		}
	}
	$('#tbody').html(tr);
}
/** END SORT TASKS FUNCTIONS **/

/** PRINT **/
function printTask(taskList, i){
    return "<tr> "
	+ "<td>"
	+ sub(JSON.stringify(taskList[i].taskGeneric.ataCategory))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(taskList[i].task.startTime))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(taskList[i].task.endTime))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(taskList[i].taskGeneric.planeType))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(taskList[i].task.planeId))
	+ "</td>"
	+ "<td><button onclick='viewTask("
	+  JSON.stringify(taskList[i])//sub(JSON.stringify(taskList[i].task.id))
	+ ")' "
	+ "class='btn icon-btn btn-primary'>  "
	+ "<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> "
	+ "</button></td>" + "</tr>";
}

/** VIEW AND VALID TASKS **/
function callGetlistMros(){
	getServerData("ws/mro/all",getlistMros,null);
}
function getlistMros(){
	//listMros = res;
	console.log('rf');
	var mroOption='<select id="sel" class="form-control">';
	for(var j = 0; j < listMros.length; j++){
		mroOption+="<option>"+listMros[j].id+"</option>";
	}
	mroOption+='</select>';
	console.log(mroOption);
	return mroOption;
	
}
function choseMro(id){
	mroChosen = id;
	console.log(id);
}
function viewTask(taskSelected){
	
	var task;
	task = dl("Ata Category",subFy(taskSelected.taskGeneric.ataCategory) );
	task+= dl("Description ",subFy(taskSelected.taskGeneric.description) );
	task+= dl("Periodicity", subFy(taskSelected.taskGeneric.periodicity) );
	task+= dl("Hangar Need", stringify(taskSelected.taskGeneric.hangarNeed) );
	task+= dl("Duration"   , taskSelected.taskGeneric.duration );
	task+= dl("Plane Type",subFy(taskSelected.taskGeneric.planeType    ) );
	console.log("status "+taskSelected.task.taskStatus);
	if(taskSelected.task.taskStatus == 1){
		task+= dl("Mro list", getlistMros());
		task+='<center><button class="btn icon-btn btn-primary" onclick="validTask('+taskSelected.task.id+')"> submit </button></center>';
	}
	else if(taskSelected.task.taskStatus == 2) {
		task+= dl("Mro ", taskSelected.task.mroId);
		task+= dl("Mro list", getlistMros());
		task+='<center><button class="btn icon-btn btn-primary" onclick="validTask('+taskSelected.task.id+')"> edit </button></center>';
	}
	else {
		task+= dl("Mro ", taskSelected.task.mroId);
		task+='<center><button class="btn icon-btn btn-primary" onclick="location.reload()"> Return </button></center>';
	}
	
	
	
	/** add Panel **/  
	var panel;
	panel = '<div class="panel panel-primary">';
	panel += '<div class="panel-heading">Task n° '+ sub(JSON.stringify(taskSelected.task.id));
	panel += '<span class="navbar-right " onclick="location.reload()" style ="margin-right : 10px; cursor: pointer">TaskList</span></div>';
	panel += '<div class="panel-body">'+task+'</div></div>';

	$('#tasksmenu').html(panel);
}



function validTask(id){
	var m = $('#sel option:selected').val();
	getServerData("ws/task/mro/"+m+"/"+id,alert('success'),null);
	console.log("mroChosen : "+$('#sel option:selected').val() +" id : "+id);
}
/** END FUNCTIONS **/