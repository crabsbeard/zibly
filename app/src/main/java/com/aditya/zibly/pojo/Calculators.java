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
    float radiusMotherEarth = 6371.0F;

    public Calculators(Context context) {
        locationService = new LocationService(context);
    }

    public String calculateDistance(double lat, double lon) {
        double user_lat = locationService.getUserLat();
        double user_lon = locationService.getUserLon();
        String distance = "N/A";

        float disF = calculationFormula(lat, lon, user_lat, user_lon);

        if (disF < 1.0f) {
            //the distance is in meters
            Float dis = disF;
            distance = dis.toString();
            distance = distance.substring(2, 5) + " m";
        } else if (disF == 0.0f || disF < 0.020f) {
            //some problem or very close
            distance = "Look Around!";
        } else {
            //distance is in kilometers
            Float dis = disF;
            distance = dis.toString();
            distance = distance.substring(0, 4) + " km";
        }

        return distance;
    }

    private float calculationFormula(double lat1, double lon1, double lat2, double lon2) {
        //float diffLat = (float) (lat1 - lat2);
        //float diffLon = (float) (lon1 - lon2);
        a= (float)( 0.5 - Math.cos((lat2 - lat1) * Math.PI / 180)/2 +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        (1 - Math.cos((lon2 - lon1) * Math.PI / 180))/2);

      /*  a = (float) (
                Math.sin(diffLat / 2) * Math.sin(diffLat / 2)
                        + Math.cos(degToRad(lat)) * Math.cos(degToRad(user_lat))
                        * (Math.sin(diffLon / 2)) * (Math.sin(diffLon / 2))
        );

        c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
        d = radiusMotherEarth * c; //in Km for getting value in miles, simply have to change the radiusMotherEarth value
        */

        d = (float)(radiusMotherEarth*2*Math.asin(Math.sqrt(a)));

        return d;
    }

    private double degToRad(double deg) {
        return deg*(Math.PI/180);
    }
}
