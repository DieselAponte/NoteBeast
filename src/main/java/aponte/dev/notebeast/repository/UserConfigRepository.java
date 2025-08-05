package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.UserConfig;

import java.util.Optional;

public interface UserConfigRepository {
    void saveConfig(UserConfig settings);
    void updateConfig(UserConfig settings);
    Optional<UserConfig> getConfig();
    void deleteConfig(UserConfig settings);
}
