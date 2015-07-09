package com.aditya.zibly.data;//Created by aditya on 6/19/2015

public interface StaticData {

    //url endpoints and other data
     String CLIENT_ID = "NKB4KD0ORMMLTY1LI0MSHNI3WHY4H53Y2A50QIA1FE5TN03U";
     String CLIENT_SECRET = "EEEOE0YNKQJOYLF02OTC134HOH4RN5UJDTBHPSX4WOTJHYL5";
     String VERSION = "20150619";
     String PATH_VENUES = "venues";
     String PATH_VENUE = "venue";
     String PATH_EXPLORE = "explore";
     String BASE_URL = "https://api.foursquare.com/v2/";
     String NEAR_VALUE = "New Delhi";
     String PHOTOS = "photos";
     String VENUE_PHOTO_KEY = "venuePhotos";
     String LIMIT_KEY = "limit";

     String TRUE = "1";
     String FALSE = "0";

     String CLIENT_ID_KEY = "client_id";
     String CLIENT_SECRET_KEY = "client_secret";
     String VERSION_KEY ="v";
     String NAER_KEY = "near";

    //json data keys for the home screen feed, need to add more for detailed data
		
     String RESPONSE = "response";	//the first object that we get inside the unnamed object
     String GROUPS = "groups";	//groups contains the items array, itself it is an array
     String ITEMS = "items"; //array of unnamed object, there another item named array with unnamed objects inside it
     String REASONS = "reasons";	//object
     String SUMMARY = "summary"; //key-value
     String VENUE = "venue"; //object
     String LIKE = "like"; //key-value
     String ID = "id"; //key-value
     String NAME = "name"; //key-value
     String LOCATION = "location"; //object
     String LATITUDE = "lat"; //key-value
     String LONGITUDE = "lng"; //key-value
     String ADDRESS = "address"; //key-value, and only the main address
     String CATEGORIES = "categories"; //array with unnamed objects
     String ICON = "icon"; //object
     String PREFIX = "prefix"; //key-value
     String SUFFIX = "suffix"; //key-value
     String RATING = "rating"; //key-value
     String TIPS = "tips"; //array
     String TEXT = "text"; //key-value
     String USER = "user"; //object
     String FIRSTNAME = "firstname"; //key-value
     String GENDER = "gender"; //key-value
     String PHOTO = "photo"; //object

}
