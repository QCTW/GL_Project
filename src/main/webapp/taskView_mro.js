
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}
var task;
var s;
function getTask(taskInfo){
	var templateTaskView = _.template($('#taskViewScript').html());
	var html = templateTaskView({
		"ata":JSON.stringify(taskInfo.tasklist[0].ataCategory),
		"id":JSON.stringify(taskInfo.tasklist[0].id),
		"qualification":JSON.stringify(taskInfo.tasklist[0].description),
		"startDate":JSON.stringify(taskInfo.tasklist[0].startTime),
		"endDate":JSON.stringify(taskInfo.tasklist[0].endTime),
		"description":JSON.stringify(taskInfo.tasklist[0].description),
		"periodicity":JSON.stringify(taskInfo.tasklist[0].periodicity),
		"hangarNeed":JSON.stringify(taskInfo.tasklist[0].hangarNeed),

		});
	if(taskInfo.tasklist[0].taskStatus==1){
		//s="ws/task/add"
        $('#buttons').append("<button type=\"button\" class=\"btn btn-default btn-xl\">Add</button>");
	}
	else if(taskInfo.tasklist[0].taskStatus==2){
        //s="ws/task/add"
        $('#buttons').append("<button type=\"button\" class=\"btn btn-default btn-xl\">Edit</button>");
    }
    else if(taskInfo.tasklist[0].taskStatus==3){
        //s="ws/task/add"
        $('#buttons').append("<button type=\"button\" class=\"btn btn-default btn-xl\">Remove</button>");
    }
	$('#taskView').append(html);

}

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

$(function(){
	var x = getUrlParameter('id');
	getServerData("ws/task/"+x,getTask);
});
