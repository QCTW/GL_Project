function showPlanes(result) {
	console.log(result);
	planeList = result;
	//
	var tr = "";
	for (var i = 0; i < result.length; i++) {
		var str = sub(JSON.stringify(result[i].planeType)); 
		//console.log("str : "+str);
		var id = sub(JSON.stringify(result[i].planeId));
		tr += "<tr> " 
				+ "<td>"+id+ "</td>" 
				+ "<td>"+str+ "</td>"
				+ "<td><button onclick='chooseTasksByPlane(\""+str+"\","+id+")' class='btn icon-btn btn-success'>  "
				+ "<span class='glyphicon glyphicon-ok'></span> "
				+ "</button></td>" + "</tr>";
	}
	$('#planeTbody2').html(tr);
	$('#planeslist2').DataTable({});
}
function choosePlane() {

}
var idPlane;

function chooseTasksByPlane(planetype,planeId) {
	console.log("plane type "+planetype+", plane id "+planeId);
	idPlane=planeId;
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
	genericTaskId = taskId;
	$('#returnCreateTask').append('<li><a> task n°'+taskId+'</a></li>');
	//genericTaskId = taskId;
	//var res = '<input type="date" data-date-format="DD MMMM YYYY" placeholder="dd-MM-dd HH:mm:ss" size="16" ng-model="data.action.date" />';
	//res+='<input type=time'
	var res = '<div class="control-group">';
    res+='<label class="control-label">Choose a date</label>'; 
    res+='<div class="controls input-append date form_datetime" data-date-format="yyyy MM dd - HH:mm p"  data-link-field="dtp_input1">';
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
	var d = $('#textDate').val();
	//" 2017 May 07 - 03:49 pm "
	var year = d.split(" ")[0];
	var month = d.split(" ")[1];
	var day = d.split(" ")[2];
	var pm = (d.split(" ")[5].localeCompare("am") == 0)?true:false;
	var h = parseInt((d.split(" ")[4]).split(":")[0]);
	console.log("h "+ h);
	var hour = (pm)?(d.split(" ")[4]).split(":")[0]:h+12;
	var min = (d.split(" ")[4]).split(":")[1];
	var d2 = year+"/"+month+"/"+day+" "+hour+":"+min;
	 $.ajax({
			contentType: "application/json",
			dataType : "json",
			url: "ws/task/add",
			data: JSON.stringify({
			    "id": -1, "idTaskGeneric": genericTaskId, "startTime": d2,
			    "endTime": d2,"planeId": planeId,"taskStatus": 1, 
			    "mroId": -1,"mccId":-1}),
			type: "PUT"
		    }).done(function() {
		    	document.location.href = "tasksPlanes.html";
				
		    }).fail(function() {
				alert( "An error has occurred. Thank you for re-verifying the information." );
		    });
	/*//console.log(d.split(" ")[0]);
	alert('date : '+d2+"\n plane id : "+idPlane+"\ngenericTaskId : "+genericTaskId);
	//alert('task id Validate'+'\n plane id = '+planeId+'\n taskId = '+genericTaskId);
	//document.location.href = "tasksPlanes.html";*/
}