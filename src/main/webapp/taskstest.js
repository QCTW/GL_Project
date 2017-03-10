
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}
var task;
function getAllTasks(result){
	for(var i=0; i<result.length; i++){
		var templateExample = _.template($('#taskTemp').html());
		var html = templateExample({
			"starttimetask":JSON.stringify(result[i].startTime),
			"endtimetask":JSON.stringify(result[i].endTime),
			"descriptiontask":JSON.stringify(result[i].description),
			"periodicitytask":JSON.stringify(result[i].periodicity),
			"atatask":JSON.stringify(result[i].ataCategory),
			"hangartask":JSON.stringify(result[i].hangarNeed),
		
		});
		$('#tabb').append(html);
	}
} 



$(function(){
		getServerData("ws/task/all",getAllTasks);
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