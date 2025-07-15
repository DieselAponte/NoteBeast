package aponte.dev.notebeast.repository;

import aponte.dev.notebeast.model.UserConfig;

import java.util.List;
import java.util.Optional;

public interface UserConfigRepository extends Repository<UserConfig, Integer> {
    void saveConfig(UserConfig settings);
    void updateConfig(UserConfig settings);
}
