package com.mygdx.game;

public class ProfileInfo {

    public ProfileInfo(String userName, String email, String gender, String cities, String country, String degree, String active) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.cities = cities;
        this.country = country;
        this.degree = degree;
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getCities() {
        return cities;
    }

    public String getCountry() {
        return country;
    }

    public String getDegree() {
        return degree;
    }

    public String getActive() {
        return active;
    }

    private final String userName;
    private final String email;
    private final String gender;
    private final String cities;
    private final String country;
    private final String degree;
    private final String active;
}
