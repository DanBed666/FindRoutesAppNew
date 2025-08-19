package com.example.findroutesappnew;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class FindOptionsActivity extends AppCompatActivity
{
    RadioGroup routeRG;
    RadioButton option;
    Button start;

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
    }

    public void getOption(String option)
    {
        if (Objects.equals(option, "Zolty"))
        {
            FindRoutes fr = new FindRoutes(new GeoPoint(latitude, longitude),getContext(), map, getParentFragmentManager());
            fr.findRoutes();
        }
        else if (Objects.equals(option, "Zielony"))
        {

        }
        else if (Objects.equals(option, "Niebieski"))
        {

        }
        else if (Objects.equals(option, "Czerwony"))
        {

        }
        else if (Objects.equals(option, "Czarny"))
        {

        }
    }
}