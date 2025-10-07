package com.example.findroutesappnew;

import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class LocationManager
{
    MyLocationNewOverlay mLocationOverlay;
    MapView map;

    public LocationManager(MapView map)
    {
        this.map = map;
        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(map.getContext()), map);
    }

    public void showLocation()
    {
        mLocationOverlay.enableFollowLocation();
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);
        map.getController().setZoom(18.0);
    }

    public void goToMyLocation()
    {
        map.getController().setCenter(mLocationOverlay.getMyLocation());
        map.getController().setZoom(18.0);
    }

    public boolean checkIfLocationTapped(GeoPoint touched)
    {
        GeoPoint startPoint = mLocationOverlay.getMyLocation();

        BoundingBox bb = new BoundingBox(startPoint.getLatitude() + 0.0005, startPoint.getLongitude() + 0.0005,
                startPoint.getLatitude() - 0.0005, startPoint.getLongitude() - 0.0005);

        return bb.contains(touched);
    }
}
