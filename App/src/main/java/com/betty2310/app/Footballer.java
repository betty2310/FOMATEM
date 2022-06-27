package com.betty2310.app;

public class Footballer {
    private String footballer_id;
    private String name;

    public Footballer(String footballer_id, String name) {
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
