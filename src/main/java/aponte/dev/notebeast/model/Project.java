package aponte.dev.notebeast.model;


import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String title;
    private String descripcion;
    private List<String> objectives = new ArrayList<String>();
    private List<String> resourcePaths = new ArrayList<String>();
    private List<ProgressEntry> progressLog = new ArrayList<ProgressEntry>();

    public Project(int id, String title, String descripcion) {
        this.id = id;
        this.title = title;
        this.descripcion = descripcion;
    }

    //Objectives methods
    public void addObjective(String objective) {
        objectives.add(objective);
    }
    public void removeObjective(String objective) {
        objectives.remove(objective);
    }

    //Resources methods
    public void addResourcePath(String resourcePath) {
        resourcePaths.add(resourcePath);
    }
    public void removeResourcePath(String resourcePath) {
        resourcePaths.remove(resourcePath);
    }

    //Progress methods
    public void addProgressEntry(ProgressEntry progressEntry) {
        progressLog.add(progressEntry);
    }
    public void removeProgressEntry(ProgressEntry progressEntry) {
        progressLog.remove(progressEntry);
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getObjectives() {
        return objectives;
    }

    public List<String> getResourcePaths() {
        return resourcePaths;
    }

    public List<ProgressEntry> getProgressLog() {
        return progressLog;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setObjectives(List<String> objectives) {
        this.objectives = objectives;
    }

    public void setResourcePaths(List<String> resourcePaths) {
        this.resourcePaths = resourcePaths;
    }

    public void setProgressLog(List<ProgressEntry> progressLog) {
        this.progressLog = progressLog;
    }
}
