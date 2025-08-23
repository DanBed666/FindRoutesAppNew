package com.example.findroutesappnew;

import static android.app.ProgressDialog.show;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.Objects;

public class FindOptionsActivity extends AppCompatActivity
{
    RadioGroup routeRG;
    RadioButton option;
    Button start;
    GeoPoint geoPoint;
    String query = "route=hiking";
    int color = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_options);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        double latitude = getIntent().getDoubleExtra("LATITUDE", 0);
        double longitude = getIntent().getDoubleExtra("LONGITUDE", 0);
        geoPoint = new GeoPoint(latitude, longitude);
        routeRG = findViewById(R.id.routecolorRG);
        start = findViewById(R.id.btn_start);

        routeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                option = findViewById(radioGroup.getCheckedRadioButtonId());
                getOption(option.getText().toString());

                Toast.makeText(getApplicationContext(), "Selected Radio Button is : " + option.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("QUERY", query);
                returnIntent.putExtra("COLOR", color);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    public void getOption(String option)
    {
        if (Objects.equals(option, "Zolty"))
        {
            query = "route=hiking][colour=yellow";
            color = Color.YELLOW;
        }
        else if (Objects.equals(option, "Zielony"))
        {
            query = "route=hiking][colour=green";
            color = Color.GREEN;
        }
        else if (Objects.equals(option, "Niebieski"))
        {
            query = "route=hiking][colour=blue";
            color = Color.BLUE;
        }
        else if (Objects.equals(option, "Czerwony"))
        {
            query = "route=hiking][colour=red";
            color = Color.RED;
        }
        else if (Objects.equals(option, "Czarny"))
        {
            query = "route=hiking][colour=black";
            color = Color.BLACK;
        }
    }
}