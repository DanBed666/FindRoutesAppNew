package com.example.findroutesappnew;

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
    }
}
