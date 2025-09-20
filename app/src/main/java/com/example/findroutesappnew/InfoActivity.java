package com.example.findroutesappnew;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InfoActivity extends AppCompatActivity
{
    TextView name;
    TextView colour;
    TextView operator;
    RouteInfo routeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        routeInfo = (RouteInfo) getIntent().getSerializableExtra("INFO");

        name = findViewById(R.id.tv_name);
        colour = findViewById(R.id.tv_colour);
        operator = findViewById(R.id.tv_operator);

        name.setText(routeInfo.getName());
        colour.setText(routeInfo.getColour());
        operator.setText(routeInfo.getOperator());
    }
}