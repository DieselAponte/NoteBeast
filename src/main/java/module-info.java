module aponte.dev.notebeast {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens aponte.dev.notebeast to javafx.fxml;
    exports aponte.dev.notebeast;
}