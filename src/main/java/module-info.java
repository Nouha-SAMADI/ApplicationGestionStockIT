module ma.fstt.applicationgestionstockit {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens ma.fstt to javafx.fxml;
    exports ma.fstt;
}