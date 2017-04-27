$(".alert").hide();
/*if(localStorage.getItem("logFalse")!= null)
alert('ok');
*/
function postLogin(pseudo, pass){
	$.ajax({
        dataType: "json",
        url: "ws/login/"+pseudo+"/"+pass,
        type: "POST",
        success : function(){
        	alert('success');
        },

        error : function(){
        	alert('failure');
        }
    });
}

function successLogin(){
	
}


$("#ok").click(function (){
	var pseudo =$('#login-username').val();
	var pass = $('#login-password').val();
	postLogin(pseudo,pass)
	
});



