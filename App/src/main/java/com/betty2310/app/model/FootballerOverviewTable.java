package com.betty2310.app.model;

public class FootballerOverviewTable {
    private String footballer_id;
    private String name;

    public FootballerOverviewTable(String footballer_id, String name) {
        this.footballer_id = footballer_id;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public String getFootballer_id() {
        return this.footballer_id;
    }
}
