package org.todoapp.jsptodoapp.model;

import java.util.Date;

public class Task {
    private int id;
    private String taskDescription;
    private boolean isCompleted;
    private Date dueDate;

    // Constructor
    public Task(int id, String taskDescription, boolean isCompleted, Date dueDate) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Date getDueDate() {
        return dueDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

