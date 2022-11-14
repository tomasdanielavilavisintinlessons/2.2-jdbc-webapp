<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nextre
  Date: 11/9/2022
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operations on students</title>
</head>
<body>
<h1>
  <%= "Operations on students" %>
</h1>
<form action="${pageContext.request.contextPath}/operations-on-student">
  <label>Insert name:</label><br>
  <input type="text" id="name" name="nameText">
  <br>
  <label>Insert surname:</label><br>
  <input type="text" id="surname" name="surnameText">
  <br>
  <label>Insert age:</label><br>
  <input type="text" id="age" name="ageText">
  <br>
  <label>Insert years of study:</label><br>
  <input type="text" id="years" name="yearsText">
  <br>
  <label>Insert enrollment year:</label><br>
  <input type="text" id="enrollment" name="enrollmentText">
  <br>
  <button type="submit" name="insertButton">Confirm</button>
  <br>
</form>
<h2>
  <%= "Delete a student" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-student">
  <label>Insert studentId:</label><br>
  <input type="text" id="studentToDeleteId" name="studentToDeleteIdText">
  <br>
  <button type="submit" name="deleteButton">Confirm</button>
  <br>
</form>
<br>
<h1>
  <%= "All students here!" %>
</h1>
<ul>
  <% for(String student : (List<String>) request.getAttribute("students")) {%>
  <li><%= student %></li>
  <% } %>
</ul>
</body>
</html>
