package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Note;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcreteNoteRepository implements NoteRepository {
    private final Connection connection;

    public ConcreteNoteRepository() {
        this.connection = SQLiteManager.getInstance().getConnection();
    }

    @Override
    public Note create(Note note) {
        String sql = """
            INSERT INTO Note(title, noteDocumentPath, createdAt, updatedAt, project_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, note.getTitle());
            if(note.getDocumentPath() != null){
                stmt.setString(2, note.getDocumentPath());
            } else {
                stmt.setNull(2, Types.VARCHAR);
            }
            stmt.setString(3, note.getCreatedAt().toString());
            stmt.setString(4, note.getUpdatedAt().toString());
            if (note.getIdAffiliatedProject() != null) {
                stmt.setInt(5, note.getIdAffiliatedProject());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    note.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public Note update(Note note) {
        String sql = """
            UPDATE Note
            SET title = ?, noteDocumentPath = ?, updatedAt = ?, project_id = ?
            WHERE id = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, note.getTitle());
            stmt.setString(2, note.getDocumentPath());
            stmt.setString(3, note.getUpdatedAt().toString());
            if (note.getIdAffiliatedProject() != null) {
                stmt.setInt(4, note.getIdAffiliatedProject());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, note.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public void delete(Note note) {
        String sql = "DELETE FROM Note WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, note.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> readAll() {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM Note";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                notes.add(buildNoteFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    @Override
    public Optional<Note> readById(Integer id) {
        String sql = "SELECT * FROM Note WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return Optional.of(buildNoteFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //Metodos privados
    private Note buildNoteFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        Note note = new Note(id, title);
        String path = rs.getString("noteDocumentPath");
        LocalDateTime createdAt = LocalDateTime.parse(rs.getString("createdAt"));
        note.setDocumentPath(path);
        note.setCreatedAt(createdAt);

        String updatedAtStr = rs.getString("updatedAt");
        if (updatedAtStr != null) {
            note.setUpdatedAt(LocalDateTime.parse(updatedAtStr));
        }

        int projectId = rs.getInt("project_id");
        if (!rs.wasNull()) {
            note.setIdAffiliatedProject(projectId);
        }
        return note;
    }

    //Metodos adicionales
    @Override
    public Optional<Note> readByTitle(String title) {
        String sql = "SELECT * FROM Note WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return Optional.of(buildNoteFromResultSet(rs));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void insertAssociatedResources(int projectId, Note note){
        String sql = "INSERT INTO ProjectResourceLog(project_id, resource_id) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projectId);
            stmt.setInt(2, note.getId());
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
        }
    }
}
