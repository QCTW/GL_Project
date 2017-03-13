
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}
var task;
function getAllTasks(result){
	var tab =$('#example').DataTable( {
    } );
	console.log(result[1].plane.planeType);
	for(var i=0; i<result.length; i++){
		for(var j=0 ; j<result[i].tasklist.length ; j++ ){
		console.log(result[i].tasklist[j].id);
		tab.row.add( [
			JSON.stringify(result[i].tasklist[j].id),
			JSON.stringify(result[i].tasklist[j].ataCategory),
			JSON.stringify(result[i].tasklist[j].description),
			new Date(result[i].tasklist[j].endTime),
			new Date(result[i].flight.departureTime),
			JSON.stringify(result[i].plane.planeType)
		] ).draw( false );
		//var templateExample = _.template($('#taskTemp').html());
		/*var html = templateExample({
			"starttimetask":JSON.stringify(result[i].startTime),
			"endtimetask":JSON.stringify(result[i].endTime),
			"descriptiontask":JSON.stringify(result[i].description),
			"periodicitytask":JSON.stringify(result[i].periodicity),
			"atatask":JSON.stringify(result[i].ataCategory),
			"hangartask":JSON.stringify(result[i].hangarNeed),
		
		});
		$('#tabb').append(html);*/
	}
	}
}





$(function(){
		getServerData("ws/task/all2",getAllTasks);
});
/*
function callDone(result){
	var templateExample = _.template($('#templateExample').html());

	var html = templateExample({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}

$(function(){
	$("#button").click(function(){
		getServerData("ws/example/aircraft",callDone);
	});
});
$(function(){
	$("#button2").click(function(){
		getServerData("ws/example/aircraft",callDone);
	});
});
*/