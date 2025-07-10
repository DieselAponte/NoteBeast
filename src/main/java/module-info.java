module aponte.dev.notebeast {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens aponte.dev.notebeast to javafx.fxml;
    exports aponte.dev.notebeast;
}