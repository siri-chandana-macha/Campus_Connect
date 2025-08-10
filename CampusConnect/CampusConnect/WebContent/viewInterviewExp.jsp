<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="includes/navigation.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Interview Experiences</title>
<link rel="stylesheet" href="CSS/viewInterviewExp_style.css">
<link rel="stylesheet" href="CSS/navigation_style.css">
</head>
<body>
	<div class="addBtn">
		<a href="submitInterviewExp.jsp">+ Add Experience</a>
	</div>
	<div class="container">
		<c:forEach var="exp" items="${experiences}">
			<div class="header">
				<div class="left">
					<span class="profile-icon">👤</span>
					<div class="info">
						<h2>${exp.name}</h2>
						<p>Company: ${exp.company}</p>
					</div>
				</div>
				<div class="package">
					Duration: ${exp.duration}<br> Package: ${exp.packageOffered}
				</div>
			</div>

			<div class="questions">
				<h3>Questions</h3>
				<ol>
					<c:forEach var="q" items="${exp.questionList}">
						<li>${q}</li>
					</c:forEach>
				</ol>
			</div>
			<hr style="margin: 40px 0;">
		</c:forEach>
	</div>
	<script>
		let menu = document.querySelector("#menu-icon");
		let navLinks = document.querySelector(".nav-links");
		menu.onclick = function() {
			navLinks.classList.toggle("show-links");
			menu.classList.toggle("menu-change");
		};
	</script>
</body>
</html>
