package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.Task;
import aponte.dev.notebeast.util.Priority;
import aponte.dev.notebeast.util.TaskStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcreteTaskRepository implements TaskRepository {

    private final Connection connection;

    public ConcreteTaskRepository() {
        this.connection = SQLiteManager.getInstance().getConnection();
    }

    @Override
    public Task create(Task task) {
        String sql = """
            INSERT INTO Task(title, description, status, priority, deadline)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, task.getTitle());
            if(task.getDescription() != null){
                stmt.setString(2, task.getDescription());
            } else {
                stmt.setNull(2, Types.VARCHAR);
            }
            stmt.setString(3, task.getStatus().name());
            stmt.setString(4, task.getPriority().name());

            if (task.getDeadline() != null) {
                stmt.setString(5, task.getDeadline().toString());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }
            stmt.executeUpdate();

            // Obtener id generado
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int generatedId = keys.getInt(1);
                task.setId(generatedId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Task update(Task task) {
        String sql = """
            UPDATE Task
            SET title = ?, description = ?, status = ?, priority = ?, deadline = ?
            WHERE id = ?
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());

            if(task.getDescription() != null){
                stmt.setString(2, task.getDescription());
            } else {
                stmt.setNull(2, Types.VARCHAR);
            }
            stmt.setString(3, task.getStatus().name());
            stmt.setString(4, task.getPriority().name());
            if (task.getDeadline() != null) {
                stmt.setString(5, task.getDeadline().toString());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }
            stmt.setInt(6, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void delete(Task task) {
        String sql = "DELETE FROM Task WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<Task> readById(Integer id) {
        String sql = "SELECT * FROM Task WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(buildTaskFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Task> readAll() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Task";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tasks.add(buildTaskFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> readByPriority(Priority priority) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Task WHERE priority = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, priority.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tasks.add(buildTaskFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Optional<Task> readByTitle(String title) {
        String sql = "SELECT * FROM Task WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(buildTaskFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Task> readByStatus(TaskStatus status) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Task WHERE status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tasks.add(buildTaskFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task buildTaskFromResultSet(ResultSet rs) throws SQLException {
        int id     = rs.getInt("id");
        String title       = rs.getString("title");
        String description = rs.getString("description");

        if(description ==null){
            description = "";
        }

        TaskStatus status  = TaskStatus.valueOf(rs.getString("status"));
        Priority priority  = Priority.valueOf(rs.getString("priority"));

        String dl = rs.getString("deadline");
        LocalDateTime deadline = (dl != null)
                ? LocalDateTime.parse(dl)
                : null;

        Task task = new Task(id, title, status, priority);
        task.setDescription(description);
        task.setDeadline(deadline);
        return task;
    }
}

