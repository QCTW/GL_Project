w3IncludeHTML();

//Session["test"] = "blablabla";
//session.setAttribute("test", var);

function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}
var task;
var idt;

//GET ALL TASK
function getAllTasks(result){
	var tab =$('#example').DataTable( {
	} );
	
	for(var i=0; i<result.length; i++){
		//for(var j=0 ; j<result[i].tasklist.length ; j++ ){
		//console.log(result[i].tasklist[j].id);
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
						"<span class='glyphicon btn-glyphicon glyphicon-edit'></span> " +
						"</button>"
				
			//'<button onclick="getSTask('+idt+')" class="btn icon-btn btn-success btn-md" data-toggle="modal" data-target="#myModal"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span></button>'
		] ).draw( false );
		
		
	//}
	}
	/*$('#example tbody').on('click','tr', function(){
		var data = tab.row( this ).data();
		var x = parseInt(data[0],10);
		//$("#content").load('viewTask.html');
		document.location.href="taskView_mro.html?id="+x;
		//alert ('you clicked on '+data[0]+'\'s row');
	});*/
}

function getSTask(id){
	var x = parseInt(id,10);
	//$('#myModal').modal('show')
	//$("#content").load('viewTask.html');
	document.location.href="taskView_mro.html?id="+x;
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
	sessionStorage.setItem("pseudo",pseudo);
	sessionStorage.setItem("role",role);
	$("#pseudo").html(sessionStorage.getItem("pseudo"));
	
}


$(function(){
		getServerData("ws/task/all",getAllTasks);
});
$(function(){
	getServerData("ws/plane/all",getAllPlanes);
});
$(function(){
	getServerData("ws/login/getUser",pseudo);
});

