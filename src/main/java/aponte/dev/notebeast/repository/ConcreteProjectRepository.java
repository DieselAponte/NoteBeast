package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.ProgressEntry;
import aponte.dev.notebeast.model.Project;
import aponte.dev.notebeast.util.ObjectiveStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcreteProjectRepository implements ProjectRepository {
    private final Connection connection;

    public ConcreteProjectRepository() {
        this.connection = SQLiteManager.getInstance().getConnection();
    }

    @Override
    public Project create(Project project) {
        String insertProjectSQL = "INSERT INTO Project(title, description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertProjectSQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int generatedId = keys.getInt(1);
                project.setId(generatedId);

                insertObjectives(generatedId, project.getObjectives());
                insertResourcePaths(generatedId, project.getResourcePaths());
                insertProgressLog(generatedId, project.getProgressLog());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Project update(Project project) {
        String sql = "UPDATE Project SET title = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription() );
            stmt.setInt(3, project.getId());
            stmt.executeUpdate();

            // Limpiar y volver a insertar datos relacionados
            deleteChildTables(project.getId());
            insertObjectives(project.getId(), project.getObjectives());
            insertResourcePaths(project.getId(), project.getResourcePaths());
            insertProgressLog(project.getId(), project.getProgressLog());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public void delete(Project project) {
        String sql = "DELETE FROM Project WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, project.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertObjectives(int projectId, List<String> objectives) throws SQLException {
        String sql = "INSERT INTO ProjectObjective(project_id, objective) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String obj : objectives) {
                stmt.setInt(1, projectId);
                stmt.setString(2, obj);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void insertResourcePaths(int projectId, List<String> paths) throws SQLException {
        String sql = "INSERT INTO ProjectResourcePath(project_id, path) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String path : paths) {
                stmt.setInt(1, projectId);
                stmt.setString(2, path);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void insertProgressLog(int projectId, List<ProgressEntry> entries) throws SQLException {
        String sql = "INSERT INTO ProgressLog(project_id, timestamp, currentObjective, eventDescription, statusComment) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (ProgressEntry entry : entries) {
                stmt.setInt(1, projectId);
                stmt.setString(2, entry.getTimestamp().toString());
                stmt.setString(3, entry.getCurrentObjective());
                stmt.setString(4, entry.getEventDescription());
                stmt.setString(5, entry.getStatusComment().toString());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    @Override
    public Optional<Project> readById(Integer id) {
        String query = "SELECT * FROM Project WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(buildProjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Project> readAll() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM Project";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                projects.add(buildProjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public Optional<Project> readByTitle(String title) {
        String query = "SELECT * FROM Project WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(buildProjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private List<String> readObjectives(int projectId) throws SQLException {
        List<String> objectives = new ArrayList<>();
        String sql = "SELECT objective FROM ProjectObjective WHERE project_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    objectives.add(rs.getString("objective"));
                }
            }
        }
        return objectives;
    }

    private List<String> readPaths(int projectId) throws SQLException {
        List<String> paths = new ArrayList<>();
        String sql = "SELECT path FROM ProjectResourcePath WHERE project_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    paths.add(rs.getString("path"));
                }
            }
        }
        return paths;
    }

    private List<ProgressEntry> readProgressLog(int projectId) throws SQLException {
        List<ProgressEntry> log = new ArrayList<>();
        String sql = "SELECT * FROM ProgressLog WHERE project_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idAffiliatedProject = rs.getInt("idAffiliatedProject");
                    int idProject = rs.getInt("idProject");
                    String timeStamp = rs.getString("timestamp");
                    String currentObjective = rs.getString("currentObjective");
                    String eventDescription = rs.getString("eventDescription");
                    //Revisar si .valueOf() cumple los requisitos para que sea una salida valida para el Enum ObjectiveStatus
                    ObjectiveStatus statusComment = ObjectiveStatus.valueOf(rs.getString("statusComment"));
                    ProgressEntry entry = new ProgressEntry(idAffiliatedProject,idProject,
                            LocalDateTime.parse(timeStamp),currentObjective,
                            eventDescription,statusComment);
                    log.add(entry);
                }
            }
        }
        return log;
    }

    private Project buildProjectFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        Project project = new Project(id, title, description);
        List<String> objectives = readObjectives(id);
        if(objectives != null) {
            project.setObjectives(objectives);
        } else {
            project.setObjectives(new ArrayList<>());
        }
        List<String> resourcePaths = readPaths(id);
        if(resourcePaths != null) {
            project.setResourcePaths(resourcePaths);
        } else {
            project.setResourcePaths(new ArrayList<>());
        }
        List<ProgressEntry> progressLog = readProgressLog(id);
        if(progressLog != null) {
            project.setProgressLog(progressLog);
        } else {
            project.setProgressLog(new ArrayList<>());
        }
        project.setObjectives(objectives);
        project.setResourcePaths(resourcePaths);
        project.setProgressLog(progressLog);
        return project;
    }

    private void deleteChildTables(int projectId) throws SQLException {
        String[] tables = {"ProjectObjective", "ProjectResourcePath", "ProgressLog"};
        for (String table : tables) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM " + table + " WHERE project_id = ?")) {
                stmt.setInt(1, projectId);
                stmt.executeUpdate();
            }
        }
    }

}