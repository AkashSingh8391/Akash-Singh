module com.example.test_database {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.test_database to javafx.fxml;
    exports com.example.test_database;
}