package com.example.map_my_sona;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class dashboard extends AppCompatActivity {

    private MaterialCardView scanner;
    private  MaterialCardView manualentry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
//        getSupportActionBar().setLogo(R.drawable.logout);

        // Customize the back button
//        actionBar.setHomeAsUpIndicator(R.drawable.logout);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        scanner=findViewById(R.id.card1);
        manualentry = findViewById(R.id.card4);

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,ScannerPage.class));
            }
        });

        manualentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,ScannerPage.class));
            }
        });
    }
}