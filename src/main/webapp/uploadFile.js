function importMDP() {
	var file = $("#uploadMPD")[0].files[0];
	var text;
	var reader = new FileReader();
	reader.onload = function() {
		text = reader.result;
		// var node = document.getElementById('output');
		// node.innerText = text;
		console.log("text " +text);
		postServerData("ws/task/mpd/"+encodeURIComponent(btoa(text)),alert(text),null);
	}
	reader.readAsText($("#uploadMPD")[0].files[0]);
	
}