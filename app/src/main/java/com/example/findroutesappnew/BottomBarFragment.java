package com.example.findroutesappnew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomBarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String test;
    double latitude;
    double longitude;
    TextView nazwa;
    Button find;
    Button route;
    Button save;
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    String url;
    MyResultReceiver myResultReceiver;
    MapView map;

    public BottomBarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomBarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomBarFragment newInstance(String param1, String param2)
    {
        BottomBarFragment fragment = new BottomBarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        myResultReceiver = (MyResultReceiver) context;
        map = myResultReceiver.getMapView();

        someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result ->
                {
                    if (result.getResultCode() == MainActivity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        assert data != null;
                        url = data.getStringExtra("QUERY");
                        Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
                        FindRoutes fr = new FindRoutes(new GeoPoint(latitude, longitude),getContext(), map, getParentFragmentManager(), url);
                        fr.findRoutes();
                    }
                });

        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            test = getArguments().getString("NAZWA");
            latitude = getArguments().getDouble("LATITUDE");
            longitude = getArguments().getDouble("LONGITUDE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom_bar, container, false);
        nazwa = v.findViewById(R.id.blank);
        find = v.findViewById(R.id.btn_find);
        route = v.findViewById(R.id.btn_route);
        save = v.findViewById(R.id.btn_save);

        find.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(), FindOptionsActivity.class);
                intent.putExtra("LATITUDE", latitude);
                intent.putExtra("LONGITUDE", longitude);
                someActivityResultLauncher.launch(intent);
                //startActivity(intent);
            }
        });

        route.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RoadNavigatorManager rnm = new RoadNavigatorManager(getContext(), new GeoPoint(latitude, longitude));
                rnm.drawRoad();
            }
        });

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        nazwa.setText(test);
        return v;
    }
}