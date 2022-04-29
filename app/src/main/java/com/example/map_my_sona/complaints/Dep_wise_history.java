package com.example.map_my_sona.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.map_my_sona.R;
import com.example.map_my_sona.rating.Rating_and_Feedback;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Carpenter;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Networks;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Electricity;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Painting;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Plumber;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.emergencyContact;
import com.example.map_my_sona.manualComplaints.ManualComplaint_page;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;

public class Dep_wise_history extends AppCompatActivity {

//    private Button networks,electricity,carpenter,plumber,painting;

    private MaterialCardView networks;
    private  MaterialCardView electricity;
    private MaterialCardView carpenter;
    private  MaterialCardView plumber;
    private MaterialCardView painting;
    MaterialToolbar toolbar;

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
        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dep_wise_history.this, dashboard.class));
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    //bottom navi
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.bottom_history:
                    startActivity(new Intent(Dep_wise_history.this, ManualComplaint_page.class));
                    break;
                case R.id.bottom_feedback:

                    startActivity(new Intent(Dep_wise_history.this, Rating_and_Feedback.class));
                    break;
                case R.id.bottom_home:
                    startActivity(new Intent(Dep_wise_history.this,dashboard.class));
                    break;
                case R.id.bottom_report:
                    startActivity(new Intent(Dep_wise_history.this,Rating_and_Feedback.class));
                    break;

                case R.id.bottom_emer:
                    startActivity(new Intent(Dep_wise_history.this, emergencyContact.class));
                    break;

            }
            return false;
        }
    };

}