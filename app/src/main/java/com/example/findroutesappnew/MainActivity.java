package com.example.findroutesappnew;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MapEventsReceiver, MyResultReceiver
{
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map;
    Button loc;
    Button clear;
    MyLocationNewOverlay mLocationOverlay;
    GeoPoint startPoint;
    boolean yourLocation = true;
    String apiKey = "pk.f20efc3587b2e16667f447b8ae8065dc";
    Map<String, String> options = new HashMap<>();
    BottomBarFragmentManager bottomBarFragmentManager;
    MapEventsOverlay mapEventsOverlay;
    LocationManager locationManager;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //inflate and create the map
        setContentView(R.layout.activity_main);

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's
        //tile servers will get you banned based on this string

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        // if you need to show the current location, uncomment the line below
        // Manifest.permission.ACCESS_FINE_LOCATION,
        // WRITE_EXTERNAL_STORAGE is required in order to show the map
        String [] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO, Manifest.permission.READ_MEDIA_AUDIO};

        requestPermissionsIfNecessary(permissions);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        locationManager = new LocationManager(map);
        locationManager.showLocation();

        showCompass(ctx);


        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        mapEventsOverlay = new MapEventsOverlay(getApplicationContext(), this);
        map.getOverlays().add(mapEventsOverlay);

        loc = findViewById(R.id.btn_loc);

        loc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                locationManager.goToMyLocation();
            }
        });

        clear = findViewById(R.id.btn_clear);

        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                for (Overlay o : map.getOverlays())
                {
                    Log.i("OVERLAY", o.toString());

                    if (o instanceof FolderOverlay)
                    {
                        map.getOverlays().remove(o);
                    }
                }

                clear.setVisibility(View.INVISIBLE);
            }
        });

        bottomBarFragmentManager = new BottomBarFragmentManager(getSupportFragmentManager());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause()
    {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> permissionsToRequest = new ArrayList<>(Arrays.asList(permissions).subList(0, grantResults.length));

        if (!permissionsToRequest.isEmpty())
        {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions)
    {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions)
        {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
            {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (!permissionsToRequest.isEmpty())
        {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
    public void showCompass(Context context)
    {
        CompassOverlay mCompassOverlay = new CompassOverlay(context, new InternalCompassOrientationProvider(context), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p)
    {
        map.getOverlays().removeIf(x -> x instanceof Marker);
        Log.i("TAPD", "single tapd");


        if (locationManager.checkIfLocationTapped(p))
        {
            Log.i("TOUCH", "lokalizacja");
            getPlace(p, "DEVLOC");
        }
        else
        {
            Log.i("TOUCH", "nie");
        }

        if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) != null)
        {
            bottomBarFragmentManager.hideFragment();
        }

        return true;
    }

    @Override
    public boolean longPressHelper(GeoPoint p)
    {
        Log.i("TAPD", "single tapd");
        map.getOverlays().removeIf(x -> x instanceof Marker);

        Marker m = new Marker(map);
        m.setDefaultIcon();
        m.setPosition(p);
        m.setSubDescription("Punkt: "  + p.getLatitude() + " " + p.getLongitude());
        map.getOverlays().add(m);
        yourLocation = false;
        startPoint = p;

        getPlace(p, "LONGPRESS");

        return true;
    }
    public void getPlace(GeoPoint placeCord, String placeCode)
    {
        PlaceViewModel placeViewModel = new PlaceViewModel();

        options.put("key", apiKey);
        options.put("lat", String.valueOf(placeCord.getLatitude()));
        options.put("lon", String.valueOf(placeCord.getLongitude()));
        options.put("format", "json");

        placeViewModel.getPlace(options).observeForever(new Observer<Place>()
        {
            @Override
            public void onChanged(Place place)
            {
                if (getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) != null)
                {
                    bottomBarFragmentManager.replaceFragment(place.getDisplay_name(), placeCord, placeCode);
                }
                else
                {
                    bottomBarFragmentManager.showFragment(place.getDisplay_name(), placeCord, placeCode);
                }
            }
        });
    }

    @Override
    public MapView getMapView()
    {
        return map;
    }

    @Override
    public GeoPoint getStartPoint()
    {
        return mLocationOverlay.getMyLocation();
    }

    @Override
    public Button getClearButton()
    {
        return clear;
    }

    @Override
    public FragmentManager getFManager()
    {
        return getSupportFragmentManager();
    }
}