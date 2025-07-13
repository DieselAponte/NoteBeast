package aponte.dev.notebeast.model;

import aponte.dev.notebeast.util.*;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDateTime deadline;

    public Task(int id, String title, String description, TaskStatus status, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
