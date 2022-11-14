<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nextre
  Date: 11/9/2022
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operations on Enrollments</title>
</head>
<body>
<h1>
    <%= "Operations on enrollments" %>
</h1>
<h2>
    <%= "Insert new enrollment:" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-enrollments">
    <label>Insert studentId:</label><br>
    <input type="text" id="studentId" name="studentIdText">
    <br>
    <label>Insert courseId:</label><br>
    <input type="text" id="courseId" name="courseIdText">
    <button type="submit" name="insertButton">Confirm</button>
    <br>
</form>
<h2>
    <%= "Students:" %>
</h2>
<ul>
    <% for(String student : (List<String>) request.getAttribute("students")) {%>
    <li><%= student %></li>
    <% } %>
</ul>
<h2>
    <%= "Courses:" %>
</h2>
<ul>
    <% for(String course : (List<String>) request.getAttribute("courses")) {%>
    <li><%= course %></li>
    <% } %>
</ul>
<h2>
    <%= "Delete enrollment:" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-enrollments">
    <label>Insert studentId:</label><br>
    <input type="text" id="studentToDeleteId" name="studentToDeleteIdText">
    <br>
    <label>Insert courseId:</label><br>
    <input type="text" id="courseToDeleteId" name="courseToDeleteIdText">
    <button type="submit" name="deleteButton">Confirm</button>
    <br>
</form>
<br>
<h2>
    <%= "Enrollment list:" %>
</h2>
<ul>
  <% for(String enrollment : (List<String>) request.getAttribute("enrollments")) {%>
  <li><%= enrollment %></li>
  <% } %>
</ul>
</body>
</html>
