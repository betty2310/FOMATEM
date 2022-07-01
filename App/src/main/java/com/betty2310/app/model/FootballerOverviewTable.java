package com.betty2310.app.model;

public class FootballerOverviewTable {
    private String footballer_id;
    private String name;

    private String nationality;
    private Integer height;
    private Integer weight;
    private Integer age;
    private char stronger_foot;
    private Integer price;
    private String club;
    private String coach;


    public FootballerOverviewTable(String footballer_id, String name, String nationality, Integer height, Integer weight, Integer age, char strongerFoot, Integer price, String club, String coach) {
        this.footballer_id = footballer_id;
        this.name = name;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.stronger_foot = strongerFoot;
        this.price = price;
        this.club = club;
        this.coach = coach;
    }

    public String getName() {
        return this.name;
    }

    public String getFootballer_id() {
        return this.footballer_id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public char getStronger_foot() {
        return stronger_foot;
    }

    public void setStronger_foot(char strongerFoot) {
        this.stronger_foot = strongerFoot;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
