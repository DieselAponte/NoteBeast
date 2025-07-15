package aponte.dev.notebeast.model;

import java.time.LocalDateTime;

public class ProgressEntry {
    private final int idAffiliatedProject;
    private int id;
    private final LocalDateTime timestamp;
    private final String currentObjective;
    private final String eventDescription;
    private final String statusComment;

    public ProgressEntry(int idAffiliatedProject, int id, LocalDateTime timestamp, String currentObjective, String eventDescription, String statusComment) {
        this.id = id;
        this.idAffiliatedProject = idAffiliatedProject;
        this.timestamp = timestamp;
        this.currentObjective = currentObjective;
        this.eventDescription = eventDescription;
        this.statusComment = statusComment;
    }

    //Getters
    public int getIdAffiliatedProject() {
        return idAffiliatedProject;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCurrentObjective() {
        return currentObjective;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getStatusComment() {
        return statusComment;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
}
