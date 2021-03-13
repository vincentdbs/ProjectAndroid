package com.android.projectandroid.model;

public class Team {
    private final int logo;
    private String name, abreviation, city;
    private boolean favorites;
    private final int apiId;

    public Team(int logo, String name, String abreviation, String city, boolean favorites) {
        this.logo = logo;
        this.name = name;
        this.abreviation = abreviation;
        this.city = city;
        this.favorites = favorites;
        this.apiId = 0;
    }

    public Team(int logo, String name, String abreviation, String city, int apiId) {
        this.logo = logo;
        this.name = name;
        this.abreviation = abreviation;
        this.city = city;
        this.favorites = false;
        this.apiId = apiId;
    }

    public int getApiId() {
        return apiId;
    }

    public void flipFavorite(){
        this.favorites = !this.favorites;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public int getLogo() {
        return logo;
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

    public String getCity() {
        return city;
    }
}
