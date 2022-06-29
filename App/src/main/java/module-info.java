module com.betty2310.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires MaterialFX;


    opens com.betty2310.app to javafx.fxml;
    exports com.betty2310.app;
    exports com.betty2310.app.model;
    opens com.betty2310.app.model to javafx.fxml;
}