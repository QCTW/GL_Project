
function printTask(PlaneInfo, i){
	//console.log(taskList[i]);
	var but = "<button onclick='sendAlert("
		+  PlaneInfo.flights[i].id//sub(JSON.stringify(PlaneInfo.flights[i].id))
		+ ")' "
		+ "class='btn icon-btn btn-danger'>  "
		+ "<span class='glyphicon glyphicon-alert'></span> "
		+ "</button>";
	var ifMcc="";
	if (localStorage.getItem("role") == "mcc"){
		ifMcc = "<td>"+but+"</td>";
	}
	else {
		$("#sendAlert").hide();
	}
    return "<tr> "
	+ "<td>"
	+ sub(JSON.stringify(PlaneInfo.flights[i].commercialId))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(PlaneInfo.flights[i].departureAirport))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(PlaneInfo.flights[i].arrivalAirport))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(PlaneInfo.flights[i].departureTime))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(PlaneInfo.flights[i].arrivalTime))
	+ "</td>"
	+ "<td>"
	+ sub(JSON.stringify(PlaneInfo.flights[i].planeId))
	+ "</td>"
	+ "<td><button onclick='viewTask("
	+  JSON.stringify(PlaneInfo.flights[i])//sub(JSON.stringify(taskList[i].task.id))
	+ ")' "
	+ "class='btn icon-btn btn-primary'>  "
	+ "<span class='glyphicon btn-glyphicon glyphicon-eye-open'></span> "
	+ "</button></td>"
	+ sub(JSON.stringify(PlaneInfo.flights[i].planeId))
	+ "</td>"
	+ ifMcc
	+ "</tr>";
   
}