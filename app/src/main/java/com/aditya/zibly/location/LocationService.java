package com.aditya.zibly.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by devad_000 on 24-06-2015.
 */

public class LocationService {

    private double userLon;
    private double userLat;
    LocationManager locationManager;
    LocationListener locationListener;
    Location lastKnownLocation;

    public LocationService(Context context) {
        //lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //lastKnownLocation = location;
                if(location!=null){
                    userLat = location.getLatitude();
                    userLon = location.getLongitude();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER)){

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    public double getUserLon() {
        return userLon;
    }

    public double getUserLat() {
        return userLat;
    }
}
