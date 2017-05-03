$(function (){
	if(localStorage.getItem("mail") == null){
		localStorage.setItem("needCo","true");
		document.location.href="login.html";
	}
});

w3IncludeHTML();

function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function sub(r){
	return r.toString().substring(1,r.toString().length-1);
}
var task;
var idt;

//GET ALL TASK
function getAllTasks(result){
	var tr="";
	console.log("in get all tasks function and tr = "+tr);
	for(var i=0 ; i<result.length; i++){
		tr+="<tr> " +
				"<td>"+sub(JSON.stringify(result[i].taskGeneric.ataCategory))+"</td>" +
				"<td>"+sub(JSON.stringify(result[i].task.startTime))+"</td>" +
				"<td>"+sub(JSON.stringify(result[i].task.endTime))+"</td>"  +
				"<td>"+sub(JSON.stringify(result[i].plane.planeType))+"</td>" +
				"<td>"+sub(JSON.stringify(result[i].plane.planeId))+"</td>" +
				"<td><button onclick='getSTask("+idt+")' " +
					"class='btn icon-btn btn-primary'>  " +
					"<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> " +
					"</button></td>" +
				"</tr>";
		console.log(tr);
	}
	$('#tbody').html(tr);
	/*var tab =$('#example').DataTable( {
	} );
	
	
	<th id="ataCategory">ATA category</th>
	<th id="startDate">Start Time</th>
	<th id="endTime">End Time</th>
	<th id="planeType">Plane Airbus</th>
	<th id="planeId">Plane</th>
	for(var i=0; i<result.length; i++){
			idt=JSON.stringify(result[i].task.id);
			tab.row.add( [
			idt,
			JSON.stringify(result[i].task.ataCategory),
			//JSON.stringify(result[i].task.description),
			formatDate(new Date(result[i].task.endTime)),
			formatDate(new Date(result[i].flight.departureTime)),
			JSON.stringify(result[i].plane.planeType),
			
			(result[i].task.taskStatus == 1)?
					"<button onclick='getSTask("+idt+")' " +
					"class='btn icon-btn btn-primary'>  " +
					"<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> " +
					"</button>"
					:
						"<button onclick='getSTask("+idt+")' data-toggle='modal' data-target='#myModal' " +
						"class='btn icon-btn btn-info'>  " +
						"<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> " +
						"</button>"
				
			//'<button onclick="getSTask('+idt+')" class="btn icon-btn btn-success btn-md" data-toggle="modal" data-target="#myModal"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span></button>'
		] ).draw( false );
		
		
	//}
	}
	*/
}

function getSTask(id){
	var x = parseInt(id,10);
	document.location.href="taskView.html?id="+x;
}

function getAllPlanes(result){
	
	var planes =$('#planeslist').DataTable( {
	} );
	
	for(var i=0; i<result.length; i++){
		planes.row.add( [
			JSON.stringify(result[i].planeId),
			JSON.stringify(result[i].planeType)
		] ).draw( false );
	}
}

function formatDate(date) {
	  var monthNames = [
	    "January", "February", "March",
	    "April", "May", "June", "July",
	    "August", "September", "October",
	    "November", "December"
	  ];

	  var day = date.getDate();
	  var monthIndex = date.getMonth();
	  var year = date.getFullYear();

	  return year + ' ' + monthNames[monthIndex] + ' ' + day;
	}


function pseudo(result){
	var pseudo = JSON.stringify(result[0]);
	var role = JSON.stringify(result[1]);
	localStorage.setItem("pseudo",pseudo);
	localStorage.setItem("role",role);
	$("#pseudo").html(localStorage.getItem("mail"));
	
}
function logout(){
	localStorage.clear();
	getServerData("ws/login/logout",null);
	document.location.href="login.html";
	
}


$(function(){
		getServerData("ws/task/all",getAllTasks);
});
$(function(){
	//getServerData("ws/plane/all",getAllPlanes);
});
$(function(){
	$("#pseudo").html(localStorage.getItem("mail"));
	//getServerData("ws/login/getUser",pseudo);
});

