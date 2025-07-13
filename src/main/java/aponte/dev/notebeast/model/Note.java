package aponte.dev.notebeast.model;

import java.time.LocalDateTime;

public class Note {
    private int id;
    private String titleNote;
    private String contentNote;
    private String noteDocumentPath;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //Just in case
    private int idAffiliatedProject;

    public Note(LocalDateTime updatedAt, LocalDateTime createdAt, String noteDocumentPath, String titleNote, String contentNote, int id) {
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.noteDocumentPath = noteDocumentPath;
        this.titleNote = titleNote;
        this.contentNote = contentNote;
        this.id = id;
        this.idAffiliatedProject = 0;
    }

    //Getters

    public int getId() {
        return id;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public String getContentNote() {
        return contentNote;
    }

    public String getNoteDocumentPath() {
        return noteDocumentPath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getIdAffiliatedProject() {
        return idAffiliatedProject;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public void setContentNote(String contentNote) {
        this.contentNote = contentNote;
    }

    public void setNoteDocumentPath(String noteDocumentPath) {
        this.noteDocumentPath = noteDocumentPath;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setIdAffiliatedProject(int idAffiliatedProject) {
        this.idAffiliatedProject = idAffiliatedProject;
    }
}
