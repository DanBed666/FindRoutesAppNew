package com.example.findroutesappnew;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class RoadNavigatorManager
{
    Context context;
    GeoPoint endPoint;
    MyResultReceiver myResultReceiver;
    public RoadNavigatorManager(Context context, GeoPoint f)
    {
        this.context = context;
        myResultReceiver = (MyResultReceiver) context;
        endPoint = f;
    }

    public void drawRoad()
    {
        MapView map = myResultReceiver.getMapView();
        GeoPoint startPoint = myResultReceiver.getStartPoint();

        WebView mes = new WebView(context);
        String str = mes.getSettings().getUserAgentString();
        Log.i("My User Agent", str);

        RoadManager roadManager = new OSRMRoadManager(context, str);

        ArrayList<GeoPoint> waypoints = new ArrayList<>();
        waypoints.add(startPoint);
        waypoints.add(endPoint);

        Road road = roadManager.getRoad(waypoints);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
        map.getOverlays().add(roadOverlay);
        map.invalidate();
    }
}
