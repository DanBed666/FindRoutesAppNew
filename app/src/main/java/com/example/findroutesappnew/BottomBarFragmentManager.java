package com.example.findroutesappnew;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import org.osmdroid.util.GeoPoint;

public class BottomBarFragmentManager
{
    FragmentManager fragmentManager;

    public BottomBarFragmentManager(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    public void showFragment(String name, GeoPoint startPoint)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);
        bundle.putDouble("LATITUDE", startPoint.getLatitude());
        bundle.putDouble("LONGITUDE", startPoint.getLongitude());

        BottomBarFragment bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }

    public void showFragment(String name)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);

        BottomBarFragment bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }

    public void hideFragment()
    {
        BottomBarFragment bottomBarFragment = new BottomBarFragment();

        fragmentManager.beginTransaction()
                .remove(bottomBarFragment)
                .commit();
    }

    public void replaceFragment(String name, GeoPoint startPoint)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);
        bundle.putDouble("LATITUDE", startPoint.getLatitude());
        bundle.putDouble("LONGITUDE", startPoint.getLongitude());

        BottomBarFragment bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }

    public void replaceFragment(String name)
    {
        Bundle bundle = new Bundle();
        bundle.putString("NAZWA", name);

        BottomBarFragment bottomBarFragment = new BottomBarFragment();
        bottomBarFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, bottomBarFragment)
                .commit();
    }
}
