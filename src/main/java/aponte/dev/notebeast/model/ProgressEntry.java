package aponte.dev.notebeast.model;

import java.time.LocalDateTime;

public class ProgressEntry {
    private final LocalDateTime timestamp;
    private final String currentObjective;
    private final String eventDescription;
    private final String statusComment;

    public ProgressEntry(LocalDateTime timestamp, String currentObjective, String eventDescription, String statusComment) {
        this.timestamp = timestamp;
        this.currentObjective = currentObjective;
        this.eventDescription = eventDescription;
        this.statusComment = statusComment;
    }

    //Getters
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
}
