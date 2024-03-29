<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Sign Up</title>

	<!-- Font Icon -->
	<link rel="stylesheet"
		  href="fonts/material-icon/css/material-design-iconic-font.min.css">

	<!-- Main css -->
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">

<div class="main">

	<!-- Sign up form -->
	<section class="signup">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h2 class="form-title">Sign up</h2>

					<form method="post" action="registration" class="register-form"
						  id="register-form">
						<div class="form-group">
							<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
								type="text" name="name" id="name" placeholder="Your Name" required />
						</div>
						<div class="form-group">
							<label for="email"><i class="zmdi zmdi-email"></i></label> <input
								type="email" name="email" id="email" placeholder="Your Email" required />
						</div>
						<div class="form-group">
							<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
								type="password" name="pass" id="pass" placeholder="Password" required />
						</div>
						<div class="form-group">
							<label for="re_pass"><i class="zmdi zmdi-lock-outline"></i></label>
							<input type="password" name="re_pass" id="re_pass" required
								   placeholder="Repeat your password" />
						</div>
						<div class="form-group">
							<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
							<input type="text" name="contact" id="contact" required
								   placeholder="Contact no" maxlength="11" minlength="11"/>
						</div>
						<div class="form-group">
							<input type="checkbox" name="agree-term" id="agree-term"
								   class="agree-term" /> <label for="agree-term"
																class="label-agree-term"><span><span></span></span>I
							agree all statements in <a href="#" class="term-service">Terms
								of service</a></label>
						</div>
						<div class="form-group form-button">
							<input type="submit" name="signup" id="signup"
								   class="form-submit" value="Register" />
						</div>
					</form>
				</div>
				<div class="signup-image">
					<figure>
						<img src="images/register.jpg" alt="sing up image">
					</figure>
					<a href="login.jsp" class="signup-image-link">I am already
						member</a>
				</div>
			</div>
		</div>
	</section>


</div>
<!-- JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'/>
<script type="text/javascript">
	var status = document.getElementById("status").value;
	console.log(status);
	if(status === "success"){
		swal("Congrats", "Account Created Successfully");
	}
	if(status === "invalid"){
		swal("Invalid", "all fields are required");
	}
	if(status === "password"){
		swal("Invalid", "Password is not match");
	}
	document.getElementById("signup").onclick = function (){
		var password = document.getElementById("pass").value,
				confirmPassword = document.getElementById("re_pass").value;
		if(password == null || confirmPassword == null){
			alert("Field cannot be empty")
		}else if(password !== confirmPassword){
			alert("Passwords are dont match")
			return false;
		}
		return true;
	}
</script>


</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>