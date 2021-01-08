package com.util;

public class GeoUtils {

    /**
     * uses first formula from this article https://en.wikipedia.org/wiki/Great-circle_distance
     */
    public static double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distance = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1.609344; // calculate distance in kilo meters
        return (distance);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
