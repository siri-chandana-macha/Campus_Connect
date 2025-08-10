<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.campusconnect.dao.EventDao" %>
<%@ page import="com.campusconnect.model.Event" %>
<%@ page import="java.util.List" %>

<%
    String role = (String) session.getAttribute("role");
    request.setAttribute("role", role);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Academic Events</title>
    <link rel="stylesheet" href="CSS/navigation_style.css">
    <link rel="stylesheet" href="CSS/podcast.css">
</head>
<body>
<%@ include file="includes/navigation.jsp" %>

<!-- Top Section with Title and Upload Button -->
<div class="addBtn">
    <h3 class="section-title">Academic Events</h3>
    <c:if test="${role eq 'admin'}">
        <button onclick="toggleForm()">+ Add Event</button>
    </c:if>
</div>

<!-- Events Grid or Empty Message -->
<div class="container">
    <c:choose>
        <c:when test="${empty eventList}">
            <div class="empty-state">
                <div class="icon">📅</div>
                <p>No academic events found</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="grid">
                <c:forEach var="e" items="${eventList}">
                    <div class="podcast-card">
                        <h3>${e.title}</h3>
                        <p><strong>Organizer:</strong> ${e.organizer}</p>
                        <p><strong>Date:</strong> ${e.date}</p>
                        <p><strong>Time:</strong> ${e.time}</p>
                        <p><strong>Venue:</strong> ${e.venue}</p>
                        <p><strong>Description:</strong> ${e.description}</p>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<!-- Popup Upload Form for Admin -->
<c:if test="${role eq 'admin'}">
    <form id="uploadEvent" class="upload-form-popup" method="post" action="${pageContext.request.contextPath}/uploadEvent" style="display:none;">
        <h3>Add New Event</h3>

        <label>Title</label>
        <input type="text" name="title" required>

        <label>Organizer</label>
        <input type="text" name="organizer" required>

        <label>Date</label>
        <input type="date" name="date" required>

        <label>Time</label>
        <input type="text" name="time" required>

        <label>Venue</label>
        <input type="text" name="venue" required>

        <label>Description</label>
        <textarea name="description" rows="3" required></textarea>

        <button type="submit">Upload</button>
        <button type="button" onclick="toggleForm()">Cancel</button>
    </form>
    <div class="overlay" id="overlay" onclick="toggleForm()"></div>
</c:if>

<script>
    function toggleForm() {
        const form = document.getElementById("uploadEvent");
        const overlay = document.getElementById("overlay");
        const isVisible = form.style.display === "block";
        form.style.display = isVisible ? "none" : "block";
        overlay.style.display = isVisible ? "none" : "block";
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