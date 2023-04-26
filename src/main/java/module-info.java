module ma.fstt.applicationgestionstockit {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens ma.fstt.applicationgestionstockit to javafx.fxml;
    exports ma.fstt.applicationgestionstockit;
}