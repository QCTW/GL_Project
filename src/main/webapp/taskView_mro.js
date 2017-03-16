
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}
var task;

function getTask(taskInfo){
	var templateTaskView = _.template($('#taskViewScript').html());
	var html = templateTaskView({
		"ata":JSON.stringify(taskInfo.tasklist[0].ataCategory),
		

		});
	$('#taskView').append(html);
}

function getAllTasks(result){
	var tab =$('#example').DataTable( {
    } );
	for(var i=0; i<result.length; i++){
		tab.row.add( [
			JSON.stringify(result[i].id),
			new Date(result[i].startTime),
			new Date(result[i].endTime),
			JSON.stringify(result[i].description),
			JSON.stringify(result[i].periodicity),
			JSON.stringify(result[i].ataCategory),
			JSON.stringify(result[i].hangarNeed)
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





$(function(){
		getServerData("ws/task/1",getTask);
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