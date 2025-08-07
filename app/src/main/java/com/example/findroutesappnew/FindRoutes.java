package com.example.findroutesappnew;

import android.content.Context;
import android.widget.Toast;

import org.osmdroid.bonuspack.kml.KmlDocument;
import org.osmdroid.bonuspack.location.OverpassAPIProvider;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;

public class FindRoutes
{
    GeoPoint startPoint;
    Context context;
    MapView map;

    public FindRoutes(GeoPoint startPoint, Context context, MapView map)
    {
        this.startPoint = startPoint;
        this.context = context;
        this.map = map;
    }

    public void findRoutes()
    {
        OverpassAPIProvider overpassProvider = new OverpassAPIProvider();

        BoundingBox bb = new BoundingBox(startPoint.getLatitude() + 0.25, startPoint.getLongitude() + 0.25,
                startPoint.getLatitude() - 0.25, startPoint.getLongitude() - 0.25);

        String url = overpassProvider.urlForTagSearchKml("route=hiking", bb, 500, 30);
        KmlDocument kmlDocument = new KmlDocument();

        boolean ok = overpassProvider.addInKmlFolder(kmlDocument.mKmlRoot, url);

        if (ok)
        {
            FolderOverlay kmlOverlay = (FolderOverlay) kmlDocument.mKmlRoot.buildOverlay(map, null, null, kmlDocument);
            map.getOverlays().add(kmlOverlay);
        }
        else
        {
            Toast.makeText(context, "Error when loading KML", Toast.LENGTH_SHORT).show();
        }
    }
}
