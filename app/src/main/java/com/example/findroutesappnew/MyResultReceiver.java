package com.example.findroutesappnew;

import androidx.fragment.app.FragmentManager;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public interface MyResultReceiver
{
    MapView getMapView();
    GeoPoint getStartPoint();
    FragmentManager getFManager();
}
