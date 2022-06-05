package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.map_my_sona.assets.laptops.asset_laptop_recycle;
import com.example.map_my_sona.assets.asset_phone_recycle;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class Assets extends AppCompatActivity {
MaterialToolbar toolbar;
MaterialCardView ass_lap , ass_tab ,ass_phn ,ass_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        toolbar= findViewById(R.id.topAppBar);

        ass_lap = findViewById(R.id.asset_lap);
        ass_phn=findViewById(R.id.asset_phone);
        ass_tab=findViewById(R.id.asset_tab);
        ass_tv = findViewById(R.id.asset_tv);


        ass_lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Assets.this, asset_laptop_recycle.class));
            }
        });

        ass_phn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Assets.this, asset_phone_recycle.class));
            }
        });

        ass_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Assets.this, asset_tablet_recycle.class));
            }
        });

        ass_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Assets.this, asset_tv_recycle.class));
            }
        });




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Assets.this, dashboard.class));
            }
        });
    }
}