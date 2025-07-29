package com.example.findroutesappnew;

public class Address
{
    private String attraction;
    private String house_number;
    private String road;
    private String quarter;
    private String suburb;
    private String city;
    private String state_district;
    private String state;
    private String postcode;
    private String country;
    private String country_code;

    public Address(String attraction, String house_number, String road, String quarter, String suburb, String city, String state_district, String state, String postcode, String country, String country_code)
    {
        this.attraction = attraction;
        this.house_number = house_number;
        this.road = road;
        this.quarter = quarter;
        this.suburb = suburb;
        this.city = city;
        this.state_district = state_district;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
        this.country_code = country_code;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_district() {
        return state_district;
    }

    public void setState_district(String state_district) {
        this.state_district = state_district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
