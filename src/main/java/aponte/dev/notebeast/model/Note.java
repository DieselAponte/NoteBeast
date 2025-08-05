package aponte.dev.notebeast.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Note {
        private int id;
        private String title;
        //Se ha agregado la keyword transient por la temporalidad del contenido del documento
        private transient String contentNote;
        private String documentPath;
        private final LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        //Just in case
        private int idAffiliatedProject;

    public Note(int id, String title, String documentPath, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.documentPath = documentPath;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = this.createdAt; // Se inicializa como la misma fecha al crearla
        this.idAffiliatedProject = 0;    // No está afiliada a ningún proyecto aún
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContentNote() {
        try{
            return Files.readString(Path.of(documentPath));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error " + e.getMessage();
        }
    }

    public String getDocumentPath() {
        return documentPath;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContentNote(String contentNote) {
        this.contentNote = contentNote;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setIdAffiliatedProject(int idAffiliatedProject) {
        this.idAffiliatedProject = idAffiliatedProject;
    }
}
