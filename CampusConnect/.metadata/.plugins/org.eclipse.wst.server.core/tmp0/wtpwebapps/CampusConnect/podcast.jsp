<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.campusconnect.dao.PodcastDao"%>
<%@ page import="com.campusconnect.model.Podcast"%>
<%@ page import="java.util.List"%>

<%
    String role = (String) session.getAttribute("role");
    request.setAttribute("role", role);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Podcasts</title>
    <link rel="stylesheet" href="CSS/podcast.css">
    <link rel="stylesheet" href="CSS/navigation_style.css">
</head>
<body>
    <%@ include file="includes/navigation.jsp" %>

    <!-- Admin Upload Button-->
    <div class="addBtn">
        <h3 class="section-title">Podcasts</h3>
        <c:if test="${role eq 'admin'}">
            <button onclick="toggleForm()">+ Add Podcast</button>
        </c:if>
    </div>

    <!-- Podcast Grid -->
    <div class="container">
        <c:choose>
            <c:when test="${empty podcastList}">
                <div class="empty-state">
                    <div class="icon">🎙️</div>
                    <p>No podcasts yet</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="grid">
                    <c:forEach var="p" items="${podcastList}">
                        <div class="podcast-card">
                            <!-- Video Player First -->
                            <c:set var="filePath" value="${p.filePath}" />
                            <c:set var="ext" value="${fn:toLowerCase(filePath)}" />
                            <c:choose>
                                <c:when test="${fn:endsWith(ext, '.mp4') || fn:endsWith(ext, '.webm') || fn:endsWith(ext, '.ogg')}">
                                    <video width="100%" height="auto" controls>
                                        <source src="${pageContext.request.contextPath}/${filePath}" type="video/mp4">
                                        Your browser does not support the video tag.
                                    </video>
                                </c:when>
                                <c:otherwise>
                                    <audio controls>
                                        <source src="${pageContext.request.contextPath}/${filePath}" type="audio/mpeg">
                                        Your browser does not support the audio tag.
                                    </audio>
                                </c:otherwise>
                            </c:choose>

                            <!-- Podcast Details Below Video -->
                            <h3>Title: ${p.title}</h3>
                            <p>Description: ${p.description}</p>
                            <small>Uploaded: ${p.uploadTime}</small>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Upload Form (admin only) -->
    <c:if test="${role eq 'admin'}">
        <form id="uploadPodcast" class="upload-form-popup" method="post" action="${pageContext.request.contextPath}/uploadPodcast" enctype="multipart/form-data" style="display:none;">
            <h3>Upload New Podcast</h3>

            <label>Title</label>
            <input type="text" name="title" required>

            <label>Description</label>
            <textarea name="description" rows="3" required></textarea>

            <label>Audio/Video File</label>
            <input type="file" name="file" accept="audio/*,video/*" required>

            <button type="submit">Upload</button>
            <button type="button" onclick="toggleForm()">Cancel</button>
        </form>
    </c:if>

    <!-- Scripts -->
    <script>
        function toggleForm() {
            const form = document.getElementById("uploadPodcast");
            form.style.display = form.style.display === "block" ? "none" : "block";
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
