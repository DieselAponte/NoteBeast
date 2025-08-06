package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Note;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConcreteRecentNotesRepository implements RecentNotesRepository {
    private static final int MAX_RECENTS = 5;
    private final Connection connection;

    public ConcreteRecentNotesRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerRecentNote(Note note) {
        String sql = """
                INSERT INTO RecentNotes(title, openedAt)
                VALUES (?, ?)
                """;
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, note.getTitle());
            stmt.setString(2, LocalDateTime.now().toString());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        // Mantener solo los 5 más recientes
        String deleteOldSql = """
            DELETE FROM RecentNote
             WHERE id NOT IN (
               SELECT id
                 FROM RecentNote
                ORDER BY openedAt DESC
                LIMIT ?
             )
        """;
        try (PreparedStatement stmt = connection.prepareStatement(deleteOldSql)) {
            stmt.setInt(1, MAX_RECENTS);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readRecentNotes() {
        List<String> titles = new ArrayList<>();
        String sql = """
            SELECT title
              FROM RecentNote
             ORDER BY openedAt DESC
             LIMIT ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, MAX_RECENTS);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    titles.add(rs.getString("title"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titles;
    }
}
