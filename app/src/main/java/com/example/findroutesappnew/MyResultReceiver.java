package com.example.findroutesappnew;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public interface MyResultReceiver
{
    MapView getMapView();
    GeoPoint getStartPoint();
}
