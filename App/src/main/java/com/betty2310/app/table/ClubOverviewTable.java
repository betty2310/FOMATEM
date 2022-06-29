package com.betty2310.app.table;

public class ClubOverviewTable {

    private String club_id ;
    private String clubName;
    private String clubCountry;

    private String coachName;

    private String nFootballer;

    private String nTrophy;

    public ClubOverviewTable(String id, String name, String country, String coachName, String nFootballer, String nTrophy) {
        setClub_id(id);
        setClubName(name);
        setClubCountry(country);
        setCoachName(coachName);
        setNFootballer(nFootballer);
        setNTrophy(nTrophy);
    }
    public String getClub_id() {
        return club_id;
    }
    public void setClub_id(String id) {
        this.club_id = id;
    }
    public String getClubName() {
        return this.clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
   public String getClubCountry() {
        return this.clubCountry;
   }
    public void setClubCountry(String clubCountry) {
        this.clubCountry = clubCountry;

    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getNFootballer() {
        return nFootballer;
    }

    public void setNFootballer(String nFootballer) {
        this.nFootballer = nFootballer;
    }

    public String getNTrophy() {
        return nTrophy;
    }

    public void setNTrophy(String nTrophy) {
        this.nTrophy = nTrophy;
    }
}
