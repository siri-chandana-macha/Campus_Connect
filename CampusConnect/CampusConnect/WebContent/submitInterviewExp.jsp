<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Submit Interview Experience</title>
<link href="CSS/submitInterviewExp_style.css" rel="stylesheet">
  <link rel="stylesheet" href="CSS/navigation_style.css">

</head>
<body>
	  <%@ include file="includes/navigation.jsp" %>

	<div class="form-box">
		<h2>Share Your Placement Experience</h2>
		<form id="experienceForm" method="post" action = "${pageContext.request.contextPath}/submitExperience">
			<input type="text" name="name" placeholder="Your Name (Optional)" />
			<input type="text" id="rollNumber" name="rollNumber"
				placeholder="Roll Number (e.g. 23wh5a6605)" required /> <input
				type="text" name="company" placeholder="Company Name" required /> 
				<input type="text" name="package" placeholder="Package (e.g. 6 LPA)"
				required />
				<input type="text" name="duration" placeholder="Duration (e.g. FTE/6 month Internship)"
				required />
			<textarea name="questions" placeholder="Interview Questions Asked"
				rows="5" required></textarea>
			<button type="button" onclick="validateForm()">Submit</button>
		</form>
	</div>

	<!-- Success Popup -->
	<div class="popup" id="successPopup">
		Submitted successfully! <span class="checkmark">✔</span>
	</div>

	<script>
		function validateForm() {
			const rollInput = document.getElementById("rollNumber");
			const rollValue = rollInput.value.trim().toLowerCase();

			// Check if 'wh' is present and length is 10
			if (rollValue.length !== 10 || !rollValue.includes("wh")) {
				alert("roll number must contain 10 characters and wh code e.g. 23wh516605");
				rollInput.focus();
				return;
			}

			showPopup();
		}

		function showPopup() {
			const popup = document.getElementById("successPopup");
			popup.style.display = "block";

			setTimeout(function() {
				popup.style.display = "none";
				document.getElementById("experienceForm").submit();
			}, 2000);
		}
	    let menu = document.querySelector("#menu-icon");
	    let navLinks = document.querySelector(".nav-links");
	    menu.onclick = function () {
	      navLinks.classList.toggle("show-links");
	      menu.classList.toggle("menu-change");
	    };
	</script>

</body>
</html>