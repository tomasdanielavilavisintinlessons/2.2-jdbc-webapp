<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nextre
  Date: 11/10/2022
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operations on course</title>
</head>
<body>
<h1>
  <%= "Operations on Course" %>
</h1>
<h2>
  <%= "Insert new course:" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-course">
  <label>Choose professorId:</label><br>
  <select name="chosenFreeProfessor">
    <% for(String professorId : (List<String>) request.getAttribute("freeProfessors")) {%>
    <option><%= professorId %></option>
    <% } %>
  </select>
  <br>
  <label>Insert course name:</label><br>
  <input type="text" id="courseName" name="courseNameText">
  <button type="submit" name="insertButton">Confirm</button>
  <br>
</form>
<br>
<h2>
  <%= "Delete course by id" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-course">
  <label>Insert courseId:</label><br>
  <input type="text" id="courseToDeleteId" name="courseToDeleteIdText">
  <br>
  <button type="submit" name="deleteButton">Confirm</button>
  <br>
</form>
<br>
<h2>
  <%= "Update course name" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-course">
  <label>Insert courseId:</label><br>
  <input type="text" id="courseToUpdateId" name="courseToUpdateIdText">
  <br>
  <label>Insert new course name:</label><br>
  <input type="text" id="newCourseName" name="newCourseNameText">
  <br>
  <button type="submit" name="updateButton">Confirm</button>
  <br>
</form>
<br>
<h2>
  <%= "Course list here!" %>
</h2>
<ul>
  <% for(String course : (List<String>) request.getAttribute("courses")) {%>
  <li><%= course %></li>
  <% } %>
</ul>
</body>
</html>
