package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Carpenter;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Networks;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Electricity;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Painting;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Plumber;

public class Dep_wise_history extends AppCompatActivity {

    private Button networks,electricity,carpenter,plumber,painting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_wise_history);

        networks=(Button) findViewById(R.id.button_111);
        electricity=(Button) findViewById(R.id.button2_112);
        carpenter=(Button)findViewById(R.id.button_131);
        plumber=(Button)findViewById(R.id.plumber_history);
        painting=(Button)findViewById(R.id.painting_history);

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