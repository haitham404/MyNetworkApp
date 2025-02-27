module com.example.mynetworkapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.mynetworkapp to javafx.fxml;
    exports com.example.mynetworkapp;
}
