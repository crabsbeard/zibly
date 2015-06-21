package com.aditya.zibly.data;//Created by aditya on 6/19/2015

public class StaticData {

    //url endpoints and other data
    private String client_id = "NKB4KD0ORMMLTY1LI0MSHNI3WHY4H53Y2A50QIA1FE5TN03U";
    private String client_secret = "EEEOE0YNKQJOYLF02OTC134HOH4RN5UJDTBHPSX4WOTJHYL5";
    private String version = "20150619";
    private String base_url_explore = "https://api.foursquare.com/v2/venues/explore";
    private String near_value = "New Delhi";

    private String client_id_key = "client_id";
    private String client_secret_key = "client_secret";
    private String version_key ="v";
    private String near_key = "near";

    //json data keys for the home screen feed, need to add more for detailed data
		
		private String response = "response";	//the first object that we get inside the unnamed object
		private String groups = "groups";	//groups contains the items array, itself it is an array
		private String items = "items"; //array of unnamed object, there another item named array with unnamed objects inside it
		private String reasons = "reasons";	//object
		private String summary = "summary"; //key-value
		private String venue = "venue"; //object
		private String id = "id"; //key-value
		private String name = "name"; //key-value
		private String location = "location"; //object
		private String latitude = "lat"; //key-value
		private String longitude = "lng"; //key-value
		private String address = "address"; //key-value, and only the main address
		private String categories = "categories"; //array with unnamed objects
		private String icon = "icon"; //object
		private String prefix = "prefix"; //key-value
		private String suffix = "suffix"; //key-value
		private String rating = "rating"; //key-value
		private String tips = "tips"; //array
		private String text = "text"; //key-value
		private String user = "user"; //object
		private String firstname = "firstname"; //key-value
		private String gender = "gender"; //key-value
		private String photo = "photo"; //object

    //all getter for the variables

    public String getNear_value() {
        return near_value;
    }

    public String getNear_key() {
        return near_key;
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

    public String getResponse() {
        return response;
    }

    public String getGroups() {
        return groups;
    }

    public String getItems() {
        return items;
    }

    public String getReasons() {
        return reasons;
    }

    public String getSummary() {
        return summary;
    }

    public String getVenue() {
        return venue;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCategories() {
        return categories;
    }

    public String getIcon() {
        return icon;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getRating() {
        return rating;
    }

    public String getTips() {
        return tips;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoto() {
        return photo;
    }

    //all setters for variables

    public void setNear_value(String near_value) {
        this.near_value = near_value;
    }

    public void setNear_key(String near_key) {
        this.near_key = near_key;
    }

    public void setClient_id_key(String client_id_key) {
        this.client_id_key = client_id_key;
    }

    public void setClient_secret_key(String client_secret_key) {
        this.client_secret_key = client_secret_key;
    }

    public void setVersion_key(String version_key) {
        this.version_key = version_key;
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

    public void setResponse(String response) {
        this.response = response;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
