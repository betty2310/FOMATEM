package com.betty2310.app.model;

public class CoachOverviewTable {
    private Integer coach_id;
    private String coach;

    private String club;

    private Integer totaltrophy;



    public CoachOverviewTable(Integer coach_id, String name, String club, Integer trophy) {
        this.coach_id = coach_id;
        this.coach = name;
        this.club = club;
        this.totaltrophy = trophy;
    }
    public String getCoach() {
        return this.coach;
    }

    public Integer getCoach_id() {
        return this.coach_id;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Integer getTotaltrophy() {
        return totaltrophy;
    }

    public void setTotaltrophy(Integer totaltrophy) {
        this.totaltrophy = totaltrophy;
    }
}
