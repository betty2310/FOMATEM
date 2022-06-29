package com.betty2310.app.model;

public class CoachOverviewTable {
    private Integer coach_id;
    private String name;

    public CoachOverviewTable(Integer coach_id, String name) {
        this.coach_id = coach_id;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public Integer getCoach_id() {
        return this.coach_id;
    }
}
