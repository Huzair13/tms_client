package com.example.map_my_sona.manualComplaints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.map_my_sona.R;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Carpenter_manual;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Electricity_manual;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Networks_manual;
import com.google.android.material.card.MaterialCardView;

public class manual_dept_history extends AppCompatActivity {

    MaterialCardView manualEntry_elec,manualEntry_net,manualEntry_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_dept_history);
        manualEntry_elec=(MaterialCardView) findViewById(R.id.manual_his_electricity);
        manualEntry_net=(MaterialCardView)findViewById(R.id.manual_his_network);
        manualEntry_car=(MaterialCardView)findViewById(R.id.manual_his_carpenter);

        manualEntry_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(manual_dept_history.this, Complaints_HistoryDetails_Electricity_manual.class
                ));
            }
        });

        manualEntry_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(manual_dept_history.this, Complaints_HistoryDetails_Networks_manual.class));
            }
        });

        manualEntry_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(manual_dept_history.this, Complaints_HistoryDetails_Carpenter_manual.class));
            }
        });
    }
}