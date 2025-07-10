package aponte.dev.notebeast;

import aponte.dev.notebeast.controller.ProjectController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setPrimaryStage(stage);
        SceneManager.changeView(
                "resources.aponte.dev.notebeast.ProjectView.fxml",
                "NoteBeast",
                null
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
