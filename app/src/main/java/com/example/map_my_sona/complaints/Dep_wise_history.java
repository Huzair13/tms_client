package com.example.map_my_sona.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.map_my_sona.R;
import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.complaints.HistoryDetails.complaint_HistoryDetails_others;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dep_wise_history extends AppCompatActivity {

//    private Button networks,electricity,carpenter,plumber,painting;

    private MaterialCardView networks;
    private  MaterialCardView electricity;
    private MaterialCardView carpenter;
    private  MaterialCardView plumber;
    private MaterialCardView painting;
    private MaterialCardView others;
    MaterialToolbar toolbar;
    private DatabaseReference refDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dep_wise_history);

        networks= findViewById(R.id.button_111);
        electricity= findViewById(R.id.button2_112);
        carpenter=findViewById(R.id.button_131);
        plumber=findViewById(R.id.plumber_history);
        painting=findViewById(R.id.painting_history);
        others=findViewById(R.id.others_history);

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        networks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dep_wise_history.this, Complaints_HistoryDetails_Networks.class));

            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dep_wise_history.this, complaint_HistoryDetails_others.class));
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
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin")){
                            startActivity(new Intent(Dep_wise_history.this, dashboard.class));
                        }
                        else{
                            startActivity(new Intent(Dep_wise_history.this, AdminDashboard.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    //bottom navi



    };

}