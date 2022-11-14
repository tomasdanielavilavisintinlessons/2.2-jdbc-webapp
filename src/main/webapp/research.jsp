<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nextre
  Date: 11/10/2022
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Research operations</title>
</head>
<body>
<h1>
    <%= "Research operations" %>
</h1>
<h2>
    <%= "Students course enrollment count" %>
</h2>
<form action="${pageContext.request.contextPath}/research">
    <label>Insert the number of courses:</label><br>
    <input type="text" id="courses" name="coursesText">
    <br>
    <button type="submit" name="enrollmentCountButton">Confirm</button>
    <br>
</form>
<ul>
    <% for(String student : (List<String>) request.getAttribute("enrollmentCountByStudentList")) {%>
    <li><%= student %></li>
    <% } %>
</ul>
<h2>
    <%= "Students enrolled in a course with a specific professor" %>
</h2>
<form action="${pageContext.request.contextPath}/research">
    <label>Choose professorId:</label><br>
    <select name="chosenWorkingProfessor">
        <% for(String professorId : (List<String>) request.getAttribute("professorIds")) {%>
        <option><%= professorId %></option>
        <% } %>
    </select>
    <br>
    <button type="submit" name="confirmProfessorIdButton">Confirm</button>
    <br>
</form>
<ul>
    <% for(String student : (List<String>) request.getAttribute("enrolledStudents")) {%>
    <li><%= student %></li>
    <% } %>
</ul>
</body>
</html>
