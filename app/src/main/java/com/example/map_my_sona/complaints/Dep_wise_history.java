package com.example.map_my_sona.complaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.map_my_sona.R;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Carpenter;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Networks;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Electricity;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Painting;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Plumber;
import com.google.android.material.card.MaterialCardView;

public class Dep_wise_history extends AppCompatActivity {

//    private Button networks,electricity,carpenter,plumber,painting;

    private MaterialCardView networks;
    private  MaterialCardView electricity;
    private MaterialCardView carpenter;
    private  MaterialCardView plumber;
    private MaterialCardView painting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_wise_history);

        networks= findViewById(R.id.button_111);
        electricity= findViewById(R.id.button2_112);
        carpenter=findViewById(R.id.button_131);
        plumber=findViewById(R.id.plumber_history);
        painting=findViewById(R.id.painting_history);

        networks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Networks.class));

            }
        });

        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Electricity.class));

            }
        });

        carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Carpenter.class));
            }
        });

        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Plumber.class));
            }
        });

        painting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Painting.class));
            }
        });
    }
}