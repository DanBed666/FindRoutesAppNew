package com.example.findroutesappnew;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class LocationManager extends MyLocationNewOverlay
{
    MapView map;
    public LocationManager(MapView mapView)
    {
        super(mapView);
        map = mapView;
    }

    public void showLocation(MyLocationNewOverlay myLocationNewOverlay)
    {
        map.getController().setCenter(myLocationNewOverlay.getMyLocation());
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e, MapView mapView)
    {
        if (getLastFix() != null)
        {
            Toast.makeText(map.getContext(), "Tap! I am at " + getLastFix().getLatitude() + "," + getLastFix().getLongitude(), Toast.LENGTH_LONG).show();
        }

        return false;
    }

    @Override
    protected void drawMyLocation(Canvas canvas, Projection pj, Location lastFix)
    {
        super.drawMyLocation(canvas, pj, lastFix);

        Marker marker = new Marker(map);
        marker.setPosition(new GeoPoint(lastFix.getLatitude(), lastFix.getLongitude()));
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView)
            {
                Toast.makeText(map.getContext(), "Tap! I am at " + getLastFix().getLatitude() + "," + getLastFix().getLongitude(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
