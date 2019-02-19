package com.rishabhrawat.mytrack.Models;

import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class UserLocation {

    private GeoPoint geo_point;
    private @ServerTimestamp Date timestamp;
    private String name;

    public UserLocation() {
    }

    public UserLocation(GeoPoint geo_point, Date timestamp, String name) {
        this.geo_point = geo_point;
        this.timestamp = timestamp;
        this.name = name;
    }


    public GeoPoint getGeo_point() {
        return geo_point;
    }

    public void setGeo_point(GeoPoint geo_point) {
        this.geo_point = geo_point;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
