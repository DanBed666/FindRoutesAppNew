package com.example.findroutesappnew;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import org.osmdroid.util.GeoPoint;

public class BottomBarFragmentManager
{
    FragmentManager fragmentManager;
    BottomBarFragment bottomBarFragment;

    public BottomBarFragmentManager(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    public void showFragment(String name, GeoPoint startPoint, String placeCode)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);
        bundle.putDouble("LATITUDE", startPoint.getLatitude());
        bundle.putDouble("LONGITUDE", startPoint.getLongitude());
        bundle.putString("CODE", placeCode);

        bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }

    public void showFragment(String name, RouteInfo info, String placeCode)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);
        bundle.putSerializable("INFO", info);
        bundle.putString("CODE", placeCode);

        bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }

    public void hideFragment()
    {
        fragmentManager.beginTransaction()
                .remove(bottomBarFragment)
                .commit();
    }

    public void replaceFragment(String name, GeoPoint startPoint, String placeCode)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);
        bundle.putDouble("LATITUDE", startPoint.getLatitude());
        bundle.putDouble("LONGITUDE", startPoint.getLongitude());
        bundle.putString("CODE", placeCode);

        bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }

    public void replaceFragment(String name, RouteInfo info, String placeCode)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);
        bundle.putSerializable("INFO", info);
        bundle.putString("CODE", placeCode);

        bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }
}
