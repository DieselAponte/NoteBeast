package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.UserConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConcreteUserConfigRepository implements UserConfigRepository {
    private final Connection connection;

    public ConcreteUserConfigRepository() {
        this.connection = SQLiteManager.getInstance().getConnection();
    }

    @Override
    public void saveConfig(UserConfig settings) {
        String sql = """
            INSERT INTO UserConfig(theme, fontSize, language, notifications, defaultStudyDuration, defaultBreakDuration, alertDaysInAdvance)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, settings.getTheme());
            stmt.setInt(2, settings.getFontSize());
            stmt.setString(3, settings.getLanguage());
            stmt.setInt(4, settings.isNotifications() ? 1 : 0);
            stmt.setInt(5, settings.getDefaultStudyDuration());
            stmt.setInt(6, settings.getDefaultBreakDuration());
            stmt.setInt(7, settings.getAlertDaysInAdvance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateConfig(UserConfig settings) {
        String sql = """
            UPDATE UserConfig SET
                theme = ?,
                fontSize = ?,
                language = ?,
                notifications = ?,
                defaultStudyDuration = ?,
                defaultBreakDuration = ?,
                alertDaysInAdvance = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, settings.getTheme());
            stmt.setInt(2, settings.getFontSize());
            stmt.setString(3, settings.getLanguage());
            stmt.setInt(4, settings.isNotifications() ? 1 : 0);
            stmt.setInt(5, settings.getDefaultStudyDuration());
            stmt.setInt(6, settings.getDefaultBreakDuration());
            stmt.setInt(7, settings.getAlertDaysInAdvance());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<UserConfig> getConfig() {
        String sql = "SELECT * FROM UserConfig WHERE LIMIT 1";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return Optional.of(buildConfig(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteConfig(UserConfig settings) {
        String sql = "DELETE FROM UserConfig";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserConfig buildConfig(ResultSet rs) throws SQLException {
        return new UserConfig(
                rs.getString("theme"),
                rs.getInt("fontSize"),
                rs.getString("language"),
                rs.getInt("notifications") == 1,
                rs.getInt("defaultStudyDuration"),
                rs.getInt("defaultBreakDuration"),
                rs.getInt("alertDaysInAdvance")
        );
    }
}
