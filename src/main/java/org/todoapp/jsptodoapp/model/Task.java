package org.todoapp.jsptodoapp.model;

import java.util.Date;

public class Task {
    private int id;
    private String taskName;
    private boolean isCompleted;
    private Date dueDate;

    // Constructor
    public Task(int id, String taskName, boolean isCompleted, Date dueDate) {
        this.id = id;

        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
        this.taskName = taskName;
    }

    // Getters
    public int getId() {
        return id;
    }


    public String getTaskName() {
        return taskName;
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

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        // Customize this format to display the information you want about each task
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", isCompleted=" + isCompleted +
                ", dueDate=" + dueDate +
                '}';
    }
}

