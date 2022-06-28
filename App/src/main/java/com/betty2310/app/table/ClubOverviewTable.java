package com.betty2310.app.table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClubOverviewTable {

    private final IntegerProperty club_id = new SimpleIntegerProperty();
    private String name ;
    private String country ;

    public ClubOverviewTable(Integer id, String name, String country) {
        setClub_id(id);
        setName(name);
        setCountry(country);
    }
    public int getClub_id() {
        return club_id.get();
    }
    public void setClub_id(int id) {
        this.club_id.set(id);
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
   public String getCountry() {
        return this.country;
   }
    public void setCountry(String country) {
        this.country = country;

    }
}
