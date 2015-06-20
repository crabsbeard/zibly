package com.aditya.zibly.data;//Created by aditya on 6/19/2015

public class StaticData {

    //http request parameter data that need to passed
    private String client_id = "NKB4KD0ORMMLTY1LI0MSHNI3WHY4H53Y2A50QIA1FE5TN03U";
    private String client_secret = "EEEOE0YNKQJOYLF02OTC134HOH4RN5UJDTBHPSX4WOTJHYL5";
    private String version = "20150619";
    private String base_url_explore = "https://api.foursquare.com/v2/venues/explore";
    private String near_value = "New Delhi";

    public void setNear_value(String near_value) {
        this.near_value = near_value;
    }

    public void setNear_key(String near_key) {
        this.near_key = near_key;
    }

    public String getNear_value() {
        return near_value;
    }

    public String getNear_key() {
        return near_key;
    }

    private String client_id_key = "client_id";
    private String client_secret_key = "client_secret";
    private String version_key ="v";
    private String near_key = "near";

    public void setClient_id_key(String client_id_key) {
        this.client_id_key = client_id_key;
    }

    public void setClient_secret_key(String client_secret_key) {
        this.client_secret_key = client_secret_key;
    }

    public void setVersion_key(String version_key) {
        this.version_key = version_key;
    }

    public String getClient_id_key() {
        return client_id_key;
    }

    public String getClient_secret_key() {
        return client_secret_key;
    }

    public String getVersion_key() {
        return version_key;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBase_url_explore(String base_url_explore) {
        this.base_url_explore = base_url_explore;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getVersion() {
        return version;
    }

    public String getBase_url_explore() {
        return base_url_explore;
    }
}
