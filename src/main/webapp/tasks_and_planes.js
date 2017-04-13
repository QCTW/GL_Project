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
	console.log(result[1].plane.planeType);
	for(var i=0; i<result.length; i++){
		for(var j=0 ; j<result[i].tasklist.length ; j++ ){
		//console.log(result[i].tasklist[j].id);
			idt=JSON.stringify(result[i].tasklist[j].id);
			tab.row.add( [
			idt,
			JSON.stringify(result[i].tasklist[j].ataCategory),
			JSON.stringify(result[i].tasklist[j].description),
			formatDate(new Date(result[i].tasklist[j].endTime)),
			formatDate(new Date(result[i].flight.departureTime)),
			JSON.stringify(result[i].plane.planeType),
			
			(result[i].tasklist[j].taskStatus == 1)?
					"<button onclick='getSTask("+idt+")' data-toggle='modal' data-target='#myModal' " +
					"class='btn icon-btn btn-success'>  " +
					"<span class='glyphicon btn-glyphicon glyphicon-plus img-circle text-success'></span>Add " +
					"</button>"
					:
						"<button onclick='getSTask("+idt+")' data-toggle='modal' data-target='#myModal' " +
						"class='btn icon-btn btn-info'>  " +
						"<span class='glyphicon btn-glyphicon glyphicon-edit img-circle text-info'></span>Edit " +
						"</button>"
				
			//'<button onclick="getSTask('+idt+')" class="btn icon-btn btn-success btn-md" data-toggle="modal" data-target="#myModal"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span></button>'
		] ).draw( false );
		
		
	}
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
	$('#myModal').modal('show')
	//$("#content").load('viewTask.html');
	//document.location.href="taskView_mro.html?id="+x;
}

function getAllPlanes(result){
	
	var planes =$('#planeslist').DataTable( {
	} );
	
	//console.log(result[1].plane.planeType);
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




$(function(){
		getServerData("ws/task/all2",getAllTasks);
});
$(function(){
	getServerData("ws/plane/all",getAllPlanes);
});
