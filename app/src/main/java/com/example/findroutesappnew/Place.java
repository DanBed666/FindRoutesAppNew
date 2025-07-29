package com.example.findroutesappnew;

import java.util.ArrayList;

public class Place
{
    private String place_id;
    private String licence;
    private String osm_type;
    private String osm_id;
    private String lat;
    private String lon;
    private Address address;
    private ArrayList<Object> boundingbox = new ArrayList<>();

    public Place(String place_id, String licence, String osm_type, String osm_id, String lat, String lon, Address address, ArrayList<Object> boundingbox)
    {
        this.place_id = place_id;
        this.licence = licence;
        this.osm_type = osm_type;
        this.osm_id = osm_id;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.boundingbox = boundingbox;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    public String getOsm_id() {
        return osm_id;
    }

    public void setOsm_id(String osm_id) {
        this.osm_id = osm_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Object> getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(ArrayList<Object> boundingbox) {
        this.boundingbox = boundingbox;
    }
}
