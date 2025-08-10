<%@ page import="com.campusconnect.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect("loginSignup.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="CSS/profile.css">
      <link rel="stylesheet" href="CSS/navigation_style.css">
    
</head>
<body>
<%@ include file="includes/navigation.jsp" %>
    <div class="header"></div>

  <div class="profile-card">
    <div class="profile-image">
      <img src="data:image/png;base64,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" alt="<%= user.getName() %>">
    </div> 
    <h2><%= user.getName() %></h2>
    <p class="location"><%= user.getBranch().toUpperCase() %> Branch</p>
    <p class="title">
      Roll No: <%= user.getRollNumber() %><br>
      Email: <%= user.getEmail() %><br>
      Role: <%= user.getRole() %>
    </p>
  </div>
   <script>
    let menu = document.querySelector("#menu-icon");
    let navLinks = document.querySelector(".nav-links");
    menu.onclick = function () {
      navLinks.classList.toggle("show-links");
      menu.classList.toggle("menu-change");
    };
  </script>
</body>
</html>
