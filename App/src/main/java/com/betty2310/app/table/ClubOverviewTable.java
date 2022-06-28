package com.betty2310.app.table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ClubOverviewTable {

    private String club_id ;
    private String name ;
    private String country ;

    public ClubOverviewTable(String id, String name, String country) {
        setClub_id(id);
        setName(name);
        setCountry(country);
    }
    public String getClub_id() {
        return club_id;
    }
    public void setClub_id(String id) {
        this.club_id = id;
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
