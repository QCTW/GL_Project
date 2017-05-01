function getServerData(url, success) {
	$.ajax({
		dataType : "json",
		url : url
	}).done(success);
}
var task;
var s;
function getTask(taskInfo) {
	$('#taskId').html("Task n° " + taskInfo.task.id);
	$('#ata').html(JSON.stringify(taskInfo.task.ataCategory));
	$('#startDate').html(JSON.stringify(taskInfo.task.startTime));
	$('#endDate').html(JSON.stringify(taskInfo.task.endTime));
	$('#description').html(JSON.stringify(taskInfo.task.description));
	$('#periodicity').html(JSON.stringify(taskInfo.task.periodicity));
	$('#hangarNeed').html(taskInfo.task.hangarNeed);
	$('#mro').html(taskInfo.mro.name);
}
function getAllMro(result) {
	//var cpt = [ result.length ];
	var tab = $('#mroList').DataTable({
		"searching": false
	});
	for (var i = 0; i < result.length; i++) {
		//cpt[i] = i;
		tab.row.add(
				[

				JSON.stringify(result[i].id),
				JSON.stringify(result[i].name),
				JSON.stringify(result[i].qualification) ]).draw(false);
	}
	

	$('#mroList tbody')
			.on(
					'click',
					'tr',
					function() {
						if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
						} else {
							tab.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
						}
						var data = tab.row(this).data();

						document.getElementById("mroSelected").innerHTML = "<h5><b>Mro : </b>"
								+ data[1] + "</h5>";

					});
}

var getUrlParameter = function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
			.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
};

$(function() {
	var x = getUrlParameter('id');
	getServerData("ws/task/" + x, getTask);
	getServerData("ws/mro/all", getAllMro);
});
