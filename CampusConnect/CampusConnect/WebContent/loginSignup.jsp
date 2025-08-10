<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CampusConnect Auth</title>
<link rel = "stylesheet" href="CSS/loginSignup_style.css">
</head>
<body>
<div class="container" id="container">
	<!-- Sign Up Form -->
	<div class="form-container sign-up-container">
		<form action="${pageContext.request.contextPath}/signup" method="POST" onsubmit="return validateSignUp()">
			<h1>Create Account</h1>
			<select name="role" required>
				<option value="">Select Role</option>
				<option value="user">User</option>
				<option value="admin">Admin</option>
			</select>
			<input type="text" name="name" placeholder="Name" required>
			<input type="text" name="rollNumber" placeholder="Roll Number" required>
			<select name="branch" required>
				<option value="">Select Branch</option>
				<option value="cse-aiml">CSE-AIML</option>
				<option value="cse">CSE</option>
				<option value="ece">ECE</option>
				<option value="eee">EEE</option>
				<option value="it">IT</option>
			</select>
			<input type="email" name="email" placeholder="Email" required id="signUpEmail">
			<input type="password" name="password" placeholder="Password" required id="signUpPassword">
			<button>Sign Up</button>
		</form>
	</div>

	<!-- Sign In Form -->
	<div class="form-container sign-in-container">
		<form action="${pageContext.request.contextPath}/login" method="POST" onsubmit="return validateLogIn()">
			<h1>Sign In</h1>
			<select name="role" required>
				<option value="">Select Role</option>
				<option value="user">User</option>
				<option value="admin">Admin</option>
			</select>
			<input type="email" name="email" placeholder="Email" required id="signInEmail">
			<input type="password" name="password" placeholder="Password" required id="signInPassword">
			<a href="#">Forgot your password?</a>
			<button>Sign In</button>
		</form>
	</div>

	<!-- Overlay -->
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>Enter your details and start your journey with us</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
		</div>
	</div>
</div>

<!-- JS for Panel Switch -->
<script>
	document.addEventListener("DOMContentLoaded", function () {
		const signUpButton = document.getElementById('signUp');
		const signInButton = document.getElementById('signIn');
		const container = document.getElementById('container');

		signUpButton.addEventListener('click', function () {
			container.classList.add("right-panel-active");
		});
		signInButton.addEventListener('click', function () {
			container.classList.remove("right-panel-active");
		});
	});

	// Validation for Sign Up form
	function validateSignUp() {
		const email = document.getElementById('signUpEmail').value;
		const password = document.getElementById('signUpPassword').value;
		const emailPattern = /^[a-zA-Z0-9._%+-]+@bvrithyderabad\.edu\.in$/;
		const passwordPattern = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

		if (!emailPattern.test(email)) {
			alert("College Email must end in @bvrithyderabad.edu.in");
			return false;
		}

		if (!passwordPattern.test(password)) {
			alert("Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.");
			return false;
		}
		return true;
	}

	// Validation for log In form
	function validateLogIn() {
		const email = document.getElementById('signInEmail').value;
		const password = document.getElementById('signInPassword').value;
		const emailPattern = /^[a-zA-Z0-9._%+-]+@bvrithyderabad\.edu\.in$/;
		const passwordPattern = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

		if (!emailPattern.test(email)) {
			alert("College Email must end in @bvrithyderabad.edu.in");
			return false;
		}

		if (!passwordPattern.test(password)) {
			alert("Password must be at least 8 characters long, include an uppercase letter, a number, and a special character.");
			return false;
		}
		return true;
	}
	const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get("error") === "true") {
        alert("Invalid credentials. Please try again.");
    }
</script>

</body>
</html>

