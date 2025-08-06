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
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        //Just in case
        private Integer idAffiliatedProject;

    public Note(int id, String title) {
        this.id = id;
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }
    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContentNote() {
        if (documentPath == null) return "";
        try {
            return Files.readString(Path.of(documentPath));
        } catch(IOException e){
            e.printStackTrace();
            e.getMessage();
            return "Surgio un error inesperado. " + e.getMessage();
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

    public Integer getIdAffiliatedProject() {
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setIdAffiliatedProject(Integer idAffiliatedProject) {
        this.idAffiliatedProject = idAffiliatedProject;
    }


}
