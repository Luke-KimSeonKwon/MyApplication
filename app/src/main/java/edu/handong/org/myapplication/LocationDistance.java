package edu.handong.org.myapplication;

/**
 * Created by user on 2017-08-08.
 */

public class LocationDistance extends BusStationDatas{
      String busStationLati;
      String busStatiionLongi;

    public LocationDistance(String busStationLati, String busStatiionLongi) {
        this.busStationLati = busStationLati;
        this.busStatiionLongi = busStatiionLongi;

        double distanceMile =
                distance(37.504198, 127.047967, 37.501025, 127.037701, "");

        double distanceMeter =
                distance(37.504198, 127.047967, 37.501025, 127.037701, "meter");

        double distanceKiloMeter =
                distance(37.504198, 127.047967, 37.501025, 127.037701, "kilometer");

        System.out.println(distanceMile);
        System.out.println(distanceMeter);
        System.out.println(distanceKiloMeter);

    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))*Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist*60*1.1515;

        if(unit == "kilometer"){
            dist = dist*1.609344;
        } else if(unit =="meter"){
            dist = dist*1609.344;
        }
        return (dist);
    }


    private static double deg2rad(double deg) {
        return (deg*Math.PI/180.0);
    }

    private static double rad2deg(double rad) {
        return (rad*180/Math.PI);
    }
}
