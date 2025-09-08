package com.example.findroutesappnew;

import android.widget.Button;

import androidx.fragment.app.FragmentManager;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public interface MyResultReceiver
{
    MapView getMapView();
    GeoPoint getStartPoint();
    Button getClearButton();
    FragmentManager getFManager();
}
