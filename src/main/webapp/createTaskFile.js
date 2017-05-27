/** DECLARE VARIABLE **/
var planeSelected;
var genericTaskSelected;

/** END DECLARE VARIABLE **/

$(function() {
	getServerData("ws/plane/allPlaneInfos",getPlanesForCreateTask,null);
});

/** FUNCTIONS **/
function td(res){
	return "<td>"+res+"</td>";
}
function option(res){
	return '<option>'+res+'</option>';
}
function ok(){
	return '<span class="glyphicon btn-glyphicon glyphicon-ok"></span> ';
}
function getPlanesForCreateTask(planes){
	//console.log(planes);
	var lines= '<select id="selectPLanes" class="form-control">';
	for(var i=0; i<planes.length; i++){
		lines+= option(planes[i].plane.planeId);
	}
	lines+='</select>';
	var but = '<button class="btn icon-btn btn-primary" onclick="getGenericTask('+JSON.stringify(planes[i])+')"> ok </button>';
	$('#createChose').html(dl2("Chose a plane",lines,but));
	//$('#planeslist2').DataTable({});
}

function getGenericTask(p){
	//console.log(p.plane.id);
	alert(p);
	//alert($('#selectPLanes option:selected').val());
	//getServerData("ws/task/genericByPlane/"+planetype, getGenericTaskAux,null);
}
function getGenericTaskAux(genericTasks){
	console.log(genericTasks);
}



/** END FUNCTIONS **/