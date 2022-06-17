module com.challenge5.converter {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.challenge5.converter.controller to javafx.fxml;
    exports com.challenge5.converter;
}
