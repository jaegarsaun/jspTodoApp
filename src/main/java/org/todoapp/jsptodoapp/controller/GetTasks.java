package org.todoapp.jsptodoapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import org.todoapp.jsptodoapp.db.DatabaseConnector;
import org.todoapp.jsptodoapp.model.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.*;


import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "gettaskservlet", value = "/get-task-servlet")

public class GetTasks extends HttpServlet {
    List<Task> taskList = new ArrayList<>();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        try (Connection conn = DatabaseConnector.getConnection()) {
            // Example: Query to fetch all tasks
            String query = "SELECT * FROM tasks";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String taskDescription = rs.getString("task_description");
                    boolean isCompleted = rs.getBoolean("is_completed");
                    Date dueDate = rs.getDate("due_date"); // Use appropriate data type and handling for dates


                    // Create a new Task object from the row data
                    Task task = new Task(id, taskDescription, isCompleted, dueDate);

                    // Add the Task object to the list
                    taskList.add(task);
                    \
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle SQL exceptions
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions
        }

        request.setAttribute("tasks", taskList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.include(request,response);
        dispatcher.forward(request,response);
        response.sendRedirect("index.jsp");
    }

    public void destroy() {
    }
}