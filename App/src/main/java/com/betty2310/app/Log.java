package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.model.FootballerOverviewTable;
import com.betty2310.app.model.LogTable;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Log implements Initializable {
    public MFXTableView<LogTable> table;
    public MFXTableColumn<LogTable> colStamp;
    public MFXTableColumn<LogTable> colId;
    public MFXTableColumn<LogTable> colOperation;

    private Connection connection = new Database().connection();
    private ObservableList<LogTable> data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = connection.createStatement().executeQuery("select * from footballer_log;");
            while (rs.next())
            {
                data.add(new LogTable(rs.getString("stamp"), rs.getString("operation").charAt(0), rs.getInt("id")));
            }

            colId = new MFXTableColumn<>("id", true, Comparator.comparing(LogTable::getId));
            colStamp = new MFXTableColumn<>("stamp", true, Comparator.comparing(LogTable::getStamp));
            colOperation = new MFXTableColumn<>("operation", true, Comparator.comparing(LogTable::getOperation));

            colId.setRowCellFactory(logTable -> new MFXTableRowCell<>(LogTable::getId));
            colStamp.setRowCellFactory(logTable -> new MFXTableRowCell<>(LogTable::getStamp));
            colOperation.setRowCellFactory(logTable -> new MFXTableRowCell<>(LogTable::getOperation));

            table.getTableColumns().addAll(colId, colStamp, colOperation);

            table.setItems(data);


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
}
