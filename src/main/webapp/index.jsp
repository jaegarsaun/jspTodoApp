<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.todoapp.jsptodoapp.model.Task" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>
<body>
<% List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    for(Task task : tasks) {
%>
<p>
    ID: <%= task.getId() %><br>
    Description: <%= task.getTaskDescription() %><br>
    Completed: <%= task.isCompleted() ? "Yes" : "No" %><br>
    Due Date: <%= task.getDueDate() != null ? task.getDueDate().toString() : "N/A" %><br>
</p>
<%
    }
%>
</body>
</html>