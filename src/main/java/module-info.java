module com.oopii.wellness_centre4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jfoenix;
    requires java.sql;

    opens com.oopii.wellness_centre4 to javafx.fxml;
    exports com.oopii.wellness_centre4;
}