package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.table.FootballerOverviewTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FootballerOverview implements Initializable {
    public TableView<FootballerOverviewTable> table;
    public TableColumn<FootballerOverviewTable, String> colName;
    public TableColumn<FootballerOverviewTable, String> colID;

    private ObservableList<FootballerOverviewTable> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            Database db = new Database();
            Connection connection = db.connection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT footballer_id, name FROM footballer LIMIT 100;");

            while (rs.next()) {
                data.add(new FootballerOverviewTable(Integer.toString(rs.getInt("footballer_id")), rs.getString("name")));
            }

            colID.setCellValueFactory(new PropertyValueFactory<>("footballer_id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            table.setItems(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFootballer(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !mouseEvent.isConsumed()) {
            mouseEvent.consume();
            Stage stage = new Stage();
            stage.setScene(new Scene(new Pane()));
            stage.setTitle("HIHIHi");
            stage.show();
        }
    }
}
