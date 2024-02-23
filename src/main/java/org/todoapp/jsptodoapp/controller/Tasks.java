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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class Tasks extends HttpServlet {
    List<Task> taskList = new ArrayList<>();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        List<Task> taskList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM tasks";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String taskName = rs.getString("task_name");
                    boolean isCompleted = rs.getBoolean("is_completed");
                    Date dueDate = rs.getDate("due_date");

                    Task task = new Task(id, taskName, isCompleted, dueDate);
                    taskList.add(task);
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

        // Forward request to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if(Objects.equals(type, "delete")){
            int id = Integer.parseInt(request.getParameter("id"));

            try(Connection conn = DatabaseConnector.getConnection()) {
                // SQL query to delete a row based on id
                String query = "DELETE FROM tasks WHERE id = ?";

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    // Set the id parameter in the query
                    stmt.setInt(1, id); // Make sure to convert id to integer

                    int affectedRows = stmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Delete successful");
                        response.sendRedirect("/jspTodoApp_war_exploded/tasks");
                    } else {
                        // Handle the case where the task with the given id does not exist or could not be deleted
                        System.out.println("No task found with id: " + id + " or task could not be deleted.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle SQL exceptions
                    response.sendRedirect("/jspTodoApp_war_exploded/errorPage"); // Redirect to an error page or another appropriate page
                }
            }catch (Exception e) {
                e.printStackTrace();
                // Handle other exceptions
            }

        }else if(Objects.equals(type, "insert")){
            // Retrieve form data
            String name = request.getParameter("name");
            String date = request.getParameter("date");
            Boolean completed = false;

            try (Connection conn = DatabaseConnector.getConnection()) {

                String query = "INSERT INTO tasks(task_name, due_date, is_completed) VALUES(?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setString(1, name);
                    stmt.setDate(2, java.sql.Date.valueOf(date));
                    stmt.setBoolean(3, completed);

                    int affectedRows = stmt.executeUpdate();


                    if (affectedRows > 0) {
                        System.out.println("Insert successful");
                        response.sendRedirect("/jspTodoApp_war_exploded/tasks");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle SQL exceptions
                }

            } catch (Exception e) {
                e.printStackTrace();
                // Handle other exceptions
            }



        }

    }



    public void destroy() {
    }
}