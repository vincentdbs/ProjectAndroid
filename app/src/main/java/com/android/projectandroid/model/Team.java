package com.android.projectandroid.model;

public class Team {
    private int logo;
    private String name, abreviation, city;
    private boolean favorites;

    public Team(int logo, String name, String abreviation, String city, boolean favorites) {
        this.logo = logo;
        this.name = name;
        this.abreviation = abreviation;
        this.city = city;
        this.favorites = favorites;
    }

    public Team(int logo, String name, String abreviation, String city) {
        this.logo = logo;
        this.name = name;
        this.abreviation = abreviation;
        this.city = city;
        this.favorites = false;
    }

    public void flipFavorite(){
        this.favorites = !this.favorites;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
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
