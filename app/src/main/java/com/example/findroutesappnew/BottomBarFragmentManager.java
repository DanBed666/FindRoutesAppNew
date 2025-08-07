package com.example.findroutesappnew;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

public class BottomBarFragmentManager
{
    FragmentManager fragmentManager;

    public BottomBarFragmentManager(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
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
