package com.example.map_my_sona.manualComplaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.map_my_sona.R;
import com.example.map_my_sona.rating.Rating_and_Feedback;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.emergencyContact;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Carpenter_manual;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Electricity_manual;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Networks_manual;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    //bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.bottom_history:
                    startActivity(new Intent(manual_dept_history.this, ManualComplaint_page.class));
                    break;
                case R.id.bottom_feedback:

                    startActivity(new Intent(manual_dept_history.this, Rating_and_Feedback.class));
                    break;
                case R.id.bottom_home:
                    startActivity(new Intent(manual_dept_history.this, dashboard.class));
                    break;
                case R.id.bottom_report:
                    startActivity(new Intent(manual_dept_history.this,Rating_and_Feedback.class));
                    break;

                case R.id.bottom_emer:
                    startActivity(new Intent(manual_dept_history.this, emergencyContact.class));
                    break;

            }
            return false;
        }
    };

}