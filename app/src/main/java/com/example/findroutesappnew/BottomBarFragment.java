package com.example.findroutesappnew;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    TextView nazwa;
    Button find;
    Button route;
    Button save;

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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            test = getArguments().getString("NAZWA");
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
                //FindRoutes fr = new FindRoutes();
                //fr.findRoutes();
            }
        });

        route.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

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