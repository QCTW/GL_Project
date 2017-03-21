/**
 * Created by krimo on 21/03/17.
 */

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
        "id":JSON.stringify(taskInfo.tasklist[0].id),
        "qualification":JSON.stringify(taskInfo.tasklist[0].description),
        "startDate":JSON.stringify(taskInfo.tasklist[0].startTime),
        "endDate":JSON.stringify(taskInfo.tasklist[0].endTime),
        "description":JSON.stringify(taskInfo.tasklist[0].description),
        "periodicity":JSON.stringify(taskInfo.tasklist[0].periodicity),
        "hangarNeed":JSON.stringify(taskInfo.tasklist[0].hangarNeed),

    });
    $('#taskView').append(html);
}




$(function(){
    getServerData("ws/task/all",getAllTasks());
});
