<%@ page language="java" %>
<%
    session.invalidate();
    response.sendRedirect("loginSignup.jsp");
%>
