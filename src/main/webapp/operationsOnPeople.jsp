<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nextre
  Date: 11/9/2022
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operations on People</title>
</head>
<body>
<h1>
  <%= "Operations on People" %>
</h1>
<h2>
  <%= "Retrieve person by id" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-people">
  <label>Insert personId:</label><br>
  <input type="text" id="personId" name="personIdText">
  <br>
  <button type="submit" name="idButton">Confirm</button>
  <br>
</form>
<%= "Person's info:" + request.getAttribute("person-info")%>
<br>
<h2>
  <%= "Delete person by id" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-people">
  <label>Insert personId:</label><br>
  <input type="text" id="personToDeleteId" name="personToDeleteIdText">
  <br>
  <button type="submit" name="deleteButton">Confirm</button>
  <br>
</form>
<br>
<h2>
  <%= "Update person name" %>
</h2>
<form action="${pageContext.request.contextPath}/operations-on-people">
  <label>Insert personId:</label><br>
  <input type="text" id="personToUpdateId" name="personToUpdateIdText">
  <br>
  <label>Insert new name:</label><br>
  <input type="text" id="newName" name="newNameText">
  <br>
  <button type="submit" name="updateButton">Confirm</button>
  <br>
</form>
<br>
<h2>
  <%= "People list here!" %>
</h2>
<ul>
  <% for(String person : (List<String>) request.getAttribute("peopleList")) {%>
  <li><%= person %></li>
  <% } %>
</ul>
</body>
</html>
