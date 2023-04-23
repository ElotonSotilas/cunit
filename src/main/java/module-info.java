module dev.converter.cunit {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.json;

    opens dev.converter.cunit to javafx.fxml;
    exports dev.converter.cunit;
}