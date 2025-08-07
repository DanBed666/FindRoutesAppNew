package com.example.findroutesappnew;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.osmdroid.bonuspack.kml.KmlFeature;
import org.osmdroid.bonuspack.kml.KmlLineString;
import org.osmdroid.bonuspack.kml.KmlPlacemark;
import org.osmdroid.bonuspack.kml.KmlPoint;
import org.osmdroid.bonuspack.kml.KmlPolygon;
import org.osmdroid.bonuspack.kml.KmlTrack;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;

public class KMLStyler implements KmlFeature.Styler
{
    FragmentManager fragmentManager;

    public KMLStyler(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onFeature(Overlay overlay, KmlFeature kmlFeature) {

    }

    @Override
    public void onPoint(Marker marker, KmlPlacemark kmlPlacemark, KmlPoint kmlPoint) {

    }

    @Override
    public void onLineString(Polyline polyline, KmlPlacemark kmlPlacemark, KmlLineString kmlLineString)
    {
        polyline.getOutlinePaint().setColor(Color.RED);

        polyline.setOnClickListener(new Polyline.OnClickListener()
        {
            @Override
            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos)
            {
                Bundle bundle = new Bundle();
                bundle.putString("NAZWA", kmlPlacemark.getExtendedData("name"));

                if (fragmentManager.findFragmentById(R.id.fragmentContainerView) != null)
                {
                    BottomBarFragment bottomBarFragment = new BottomBarFragment();
                    bottomBarFragment.setArguments(bundle);

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, bottomBarFragment)
                            .commit();
                }
                else
                {
                    BottomBarFragment bottomBarFragment = new BottomBarFragment();
                    bottomBarFragment.setArguments(bundle);

                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainerView, bottomBarFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }

                return true;
            }
        });
    }

    @Override
    public void onPolygon(Polygon polygon, KmlPlacemark kmlPlacemark, KmlPolygon kmlPolygon) {

    }

    @Override
    public void onTrack(Polyline polyline, KmlPlacemark kmlPlacemark, KmlTrack kmlTrack) {

    }
}
