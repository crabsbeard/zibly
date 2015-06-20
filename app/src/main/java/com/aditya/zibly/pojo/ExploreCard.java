package com.aditya.zibly.pojo;//Created by aditya on 6/19/2015

public class ExploreCard {
    private String id;
    private String place_name;
    private String place_address;
    private float rating;
    private int heart_status; //0 for no 1 for yes
    private String place_distance;
    private String place_type_icon; //number for the icon to be used

    public void setId(String id) {
        this.id = id;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public void setPlace_address(String place_address) {
        this.place_address = place_address;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setHeart_status(int heart_status) {
        this.heart_status = heart_status;
    }

    public void setPlace_distance(String place_distance) {
        this.place_distance = place_distance;
    }

    public void setPlace_type_icon(String place_type_icon) {
        this.place_type_icon = place_type_icon;
    }

    public String getId() {
        return id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getPlace_address() {
        return place_address;
    }

    public float getRating() {
        return rating;
    }

    public int getHeart_status() {
        return heart_status;
    }

    public String getPlace_distance() {
        return place_distance;
    }

    public String getPlace_type_icon() {
        return place_type_icon;
    }
}
