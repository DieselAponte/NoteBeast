package aponte.dev.notebeast;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SceneManager {

    private static final Map<String, Object> controllers = new HashMap<>();
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static <T> void changeView(String fxmlPath, String title, Consumer<T> controllerInitializer) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            T controller = loader.getController();
            controllers.put(fxmlPath, controller);

            if (controllerInitializer != null && controller != null) {
                controllerInitializer.accept(controller);
            }

            if (primaryStage == null) {
                primaryStage = new Stage();
            }

            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar la vista con inicialización: " + fxmlPath);
            e.printStackTrace();
        }
    }
}