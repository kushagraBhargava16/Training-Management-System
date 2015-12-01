/*$(document).ready(function() {

	$('#signUp').click(function() {
		$("#registerDiv").dialog({
			height : 350,
			width : 600
		});
	});
});*/
$(document).ready(function() {
	$('#username').focusin(function() {
		$('#username').addClass('box');
		$('#user').text("Enter userName");
	});

	$('#username').focusout(function() {
		$('#username').removeClass('box');
		$('#user').text("");
	});

	$('#password').focusin(function() {
		$('#password').addClass('box');
		$('#pass').text("Enter Password");
	});

	$('#password').focusout(function() {
		$('#password').removeClass('box');
		$('#pass').text("");
	});

	$('#username').keyup(function() {
		var userNameLength = $('#username').val().length;
		$('#username').text(userNameLength);
		if (userNameLength < 15) {
			$('#user').text("");
		} else {
			$('#user').text("Not exceeded than 15 characters");

		}
	});

	$('#password').keyup(function() {
		var passwordLength = $('#password').val().length;
		// $('#userName').text(userNameLength);
		if (passwordLength <= 10 && passwordLength >= 4) {
			$('#pass').text("");
		} else {
			$('#pass').text("Password range is between 4 -10");

		}
		
	});
	
});
