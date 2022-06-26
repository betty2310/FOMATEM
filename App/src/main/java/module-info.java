module com.betty2310.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires CustomStage;


    opens com.betty2310.app to javafx.fxml;
    exports com.betty2310.app;
}