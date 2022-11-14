<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nextre
  Date: 11/9/2022
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operations on Professors</title>
</head>
<body>
<h1>
    <%= "Operations on professors" %>
</h1>
<br>
<h2>
    <%= "Insert a new professor here!" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-professor">
    <label>Insert name:</label><br>
    <input type="text" id="name" name="nameText">
    <br>
    <label>Insert surname:</label><br>
    <input type="text" id="surname" name="surnameText">
    <br>
    <label>Insert age:</label><br>
    <input type="text" id="age" name="ageText">
    <br>
    <label>Insert taught subject:</label><br>
    <input type="text" id="subject" name="subjectText">
    <br>
    <label>Insert assumption year:</label><br>
    <input type="text" id="assumption" name="assumptionText">
    <br>
    <button type="submit" name="insertButton">Confirm</button>
    <br>
</form>
<h2>
    <%= "Delete a professor" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-professor">
    <label>Insert professorId:</label><br>
    <input type="text" id="professorToDeleteId" name="professorToDeleteIdText">
    <br>
    <button type="submit" name="deleteButton">Confirm</button>
    <br>
</form>
<br>
<h2>
    <%= "Update professor's taught subject" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-professor">
    <label>Insert professorId:</label><br>
    <input type="text" id="professorToUpdateId" name="professorToUpdateIdText">
    <br>
    <label>Insert new subject:</label><br>
    <input type="text" id="newSubject" name="newSubjectText">
    <br>
    <button type="submit" name="updateButton">Confirm</button>
    <br>
</form>
<br>
<h1>
    <%= "All professors here!" %>
</h1>
<ul>
  <% for(String professor : (List<String>) request.getAttribute("professors")) {%>
  <li><%= professor %></li>
  <% } %>
</ul>
</body>
</html>
