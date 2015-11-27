$(document)
		.ready(
				function() {
					$('#registerUsername').focusin(function() {
						$('#registerUsername').addClass('box');
						$('#spanUser').text("Enter username");
					});

					$('#registerUsername').focusout(function() {
						$('#registerUsername').removeClass('box');
						$('#spanUser').text("");
					});

					$('#registerPassword').focusin(function() {
						$('#registerPassword').addClass('box');
						$('#spanPassword').text("Enter Password");
					});

					$('#registerPassword').focusout(function() {
						$('#registerPassword').removeClass('box');
						$('#spanPassword').text("");
					});

					$('#name').focusin(function() {
						$('#name').addClass('box');
						$('#spanName').text("Enter Name");
					});

					$('#name').focusout(function() {
						$('#name').removeClass('box');
						$('#spanName').text("");
					});

					$('#contact').focusin(function() {
						$('#contact').addClass('box');
						$('#spanContact').text("Enter Contact");
					});

					$('#contact').focusout(function() {
						$('#contact').removeClass('box');
						$('#spanContact').text("");
					});

					$('#email').focusin(function() {
						$('#email').addClass('box');
						$('#spanEmail').text("Enter Email");
					});

					$('#email').focusout(function() {
						$('#email').removeClass('box');
						$('#spanEmail').text("");
					});

					$('#name').focusin(function() {
						$('#name').addClass('box');
						$('#spanName').text("Enter Name");
					});

					$('#name').focusout(function() {
						$('#name').removeClass('box');
						$('#spanName').text("");
					});

					$('#registerUsername').keyup(
							function() {
								var userNameLength = $('#registerUsername')
										.val().length;
								// $('#registerUserame').text(userNameLength);
								if (userNameLength < 15) {
									$('#user').text("");
								} else {
									$('#user').text(
											"Not exceeded than 15 characters");
								}
							});

					$('#registerPassword')
							.keyup(
									function() {
										var passwordLength = $(
												'#registerPassword').val().length;
										// $('#userName').text(userNameLength);
										if (passwordLength <= 10
												&& passwordLength >= 4) {
											$('#pass').text("");
										} else {
											$('#pass')
													.text(
															"Password range is between 4 -10");
										}
									});

					$('#contact')
							.keyup(
									function() {
										var contactLength = $('#contact').val().length;
										var contact = $('#contact').val();
										for (var n = 0; n < contact.length; n++) {
											var digit = contact.charCodeAt(n) >= 48
													&& contact.charCodeAt(n) <= 57
													|| contact.charCodeAt(n) == 46
													|| contact.charCodeAt(n) == 45;
											if (!digit) {
												$('#spanContact').text(
														"Enter Numbers Only");
											} else {
												if (contactLength <= 10) {
													$('#spanContact').text("");
												} else {
													$('#spanContact')
															.text(
																	"Contact digit is more than 10");
												}
											}
										}

									});

					$('#email')
							.keyup(
									function() {
										var emailValid = $('#email').val();
										var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
										if (!filter.test(emailValid)) {

											$('#spanEmail').text(
													"Enter Valid  Email-ID");
										} else {
											$('#spanEmail').text("")
										}

									});

					$('#password1')
							.keyup(
									function() {
										var passwordValid = $('#password1')
												.val();
										var regex = /^[a-zA-Z0-9]+$/;
										if (regex.test(passwordValid)) {
											$('#spanPassword')
													.text(
															"Password Contains Alphanumeric and Special Charcters");
										} else {
											$('#spanPassword').text("")

										}

									});
					
				});