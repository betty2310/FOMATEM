package com.betty2310.app;

import com.betty2310.app.connection.Database;
import com.betty2310.app.table.ClubOverviewTable;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ClubOverview implements Initializable {

    @FXML
    private MFXTableView<ClubOverviewTable> table;
    private ObservableList<ClubOverviewTable> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = FXCollections.observableArrayList();
        try {
            Database db = new Database();
            Connection connection = db.connection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT club_id, name, country FROM club LIMIT 100;");

            MFXTableColumn<ClubOverviewTable> idColumn = new MFXTableColumn<>("club_id", true, Comparator.comparing(ClubOverviewTable::getClub_id));
            MFXTableColumn<ClubOverviewTable> nameColumn = new MFXTableColumn<>("name", true, Comparator.comparing(ClubOverviewTable::getName));
            MFXTableColumn<ClubOverviewTable> countryColumn = new MFXTableColumn<>("country", true, Comparator.comparing(ClubOverviewTable::getCountry));
            while (rs.next()) {
                data.add(new ClubOverviewTable(rs.getInt("club_id"), rs.getString("name"), rs.getString("country")));
            }
            idColumn.setRowCellFactory(club -> new MFXTableRowCell<>(ClubOverviewTable::getClub_id));
            nameColumn.setRowCellFactory(club  -> new MFXTableRowCell<>(ClubOverviewTable::getName));
            countryColumn.setRowCellFactory(club  -> new MFXTableRowCell<>(ClubOverviewTable::getCountry) {{
                setAlignment(Pos.CENTER_RIGHT);
            }});
            countryColumn.setAlignment(Pos.CENTER_RIGHT);

            table.getTableColumns().addAll(idColumn, nameColumn, countryColumn);
            table.setItems(data);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
