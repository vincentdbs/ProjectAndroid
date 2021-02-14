package com.android.projectandroid.model;

public class Team {
    private int logo;
    private String name, abreviation, city;

    public Team(int logo, String name, String abreviation, String city) {
        this.logo = logo;
        this.name = name;
        this.abreviation = abreviation;
        this.city = city;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
