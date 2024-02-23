<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.todoapp.jsptodoapp.model.Task" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="../css/style.css" type="text/css"/>
</head>

<style>
    body{
        padding: 10px;
    }

    .button{
        text-decoration: none;
        background-color: #0088ff;
        color: white;
        width: 10vw;
        height: 5vh;
        padding: 10px;
        border-radius: 8px;
        font-family: Arial, serif;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .button:hover{
        background-color: #0066ff;
    }

    .item{
        font-weight: bold;
        font-family: Arial, serif;
        font-size: 15px;
    }

    .todo{
        padding: 10px;
        width: 50vw;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-radius: 8px;
        border: 1px solid black;
    }

    .todo-wrapper{
        display: flex;
        flex-direction: column;
        gap: 10px;
        margin-top: 10px;
    }

    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgb(0,0,0);
        background-color: rgba(0,0,0,0.4);
    }

    .form {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        display: flex;
        justify-content: center;
        flex-direction: column;
        align-items: center;
    }

    .buttons{
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
</style>
<body>


<button id="addModal" class="button add">Add Todo</button>
<div id="myModal" class="modal">

    <form action="tasks" method="post" class="form">
        <label for="name" class="label">Todo Name:</label>
        <input class="input" id="name" name="name" type="text">
        <label for="date" class="label">Due Date:</label>
        <input class="input" id="date" name="date" type="date">
        <input type="hidden" name="type" value="insert">
        <div class="buttons">
            <input type="submit" value="Submit" class="button">
            <span class="close button">&times;</span>
        </div>
    </form>

</div>

<div class="todo-wrapper">
    <% List<Task> tasks = (List<Task>) request.getAttribute("tasks");
        for(Task task : tasks) {
            if(!task.isCompleted()) {
    %>
    <div class="todo">
        <p class="item"><%=task.getTaskName()%></p>
        <p class="item"><%=task.getDueDate()%></p>
        <form id="deleteForm" action="tasks" method="post">
            <input type="hidden" name="id" value="<%=task.getId()%>">
            <input type="hidden" name="type" value="delete">
            <input type="submit" value="Complete Task" class="button">
        </form>
    </div>
    <%
            }
        }
    %>
</div>

<script>
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("addModal");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>