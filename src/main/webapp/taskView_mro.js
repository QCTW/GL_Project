
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




$(function(){
		getServerData("ws/task/1",getTask);
});
