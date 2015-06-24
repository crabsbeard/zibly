package com.aditya.zibly.pojo;

import android.content.Context;
import com.aditya.zibly.location.LocationService;
import java.lang.Math;


/**
 * Created by devad_000 on 24-06-2015.
 */
public class Calculators {
    LocationService locationService;
    float a, b, c, d;
    float radiusMotherEarth = 6373.0F;
    public Calculators(Context context){
        locationService = new LocationService(context);
    }
    public String calculateDistance(double lat, double lon) {
        double user_lat = locationService.getUserLat();
        double user_lon = locationService.getUserLon();
        String distance = "N/A";

        //function to calculate the distance, using the Haversine formula, because we need hardcore accuracy mofo

        float diffLat = (float)(lat - user_lat);
        float diffLon = (float)(lon - user_lon);
        //the variables a, b, c and d have meaning that I don't know, but hey the shit works, who gives a flying eff anyway

        a= (float)(Math.pow((Math.sin(diffLat / 2)), 2)+Math.cos(lat)*Math.cos(user_lat)*Math.pow((Math.sin(diffLon/2)),2));
        c = (float)(2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));
        d = radiusMotherEarth * c; //in Km for getting value in miles, simply have to change the radiusMotherEarth value

        if(d<1.0f){
            //the distance is in meters
            Float dis = d;
            distance = dis.toString();
            distance = distance.substring(2,5) + " m";
        }
        else if(d==0.0f || d<0.020f){
            //some problem or very close
            distance = "Look Around!";
        }
        else{
            //distance is in kilometers
            Float dis = d;
            distance = dis.toString();
            distance = distance.substring(0,3) + " km";
        }

        return distance;
    }
}
