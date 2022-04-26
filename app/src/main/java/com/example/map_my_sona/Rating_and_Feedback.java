package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.manualComplaints.ManualComplaint_page;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Map;

public class Rating_and_Feedback extends AppCompatActivity {

    private LinearLayout ll_rating_elec;

    private TextView ra_avg_elec,ra_avg_paint,ra_avg_plumber,ra_avg_car,ra_avg_net;
    private DatabaseReference mDatabase;
    String status;
    MaterialToolbar toolbar;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_and_feedback);

        ll_rating_elec=(LinearLayout)findViewById(R.id.LL_rating_elec);
        ra_avg_elec=(TextView) findViewById(R.id.ra_avg_elec);
        ra_avg_car=(TextView)findViewById(R.id.ra_avg_car);
        ra_avg_net=(TextView)findViewById(R.id.ra_avg_net);
        ra_avg_plumber=(TextView)findViewById(R.id.ra_avg_plumber);
        ra_avg_paint=(TextView)findViewById(R.id.ra_avg_paint);

        toolbar= findViewById(R.id.topAppBar);

        ll_rating_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Rating_and_Feedback.this,rating_electricity.class));
            }
        });

        mDatabase= FirebaseDatabase.getInstance().getReference().child("complaints");

        mDatabase.child("Electricity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    if(map.get("status").equals("Completed")){
                        Object avg=map.get("rating");
                        Float gvalue=Float.parseFloat(String.valueOf(avg));
                        if(!gvalue.equals(0.0f)){
                            sum +=gvalue;
                            count+=1;
                        }
                    }
                    average=sum/count;
                    ra_avg_elec.setText(String.valueOf(df.format(average)));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //average for network

        mDatabase.child("Network").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    if(map.get("status").equals("Completed")){
                        Object avg=map.get("rating");
                        Float gvalue=Float.parseFloat(String.valueOf(avg));
                        if(!gvalue.equals(0.0f)){
                            sum +=gvalue;
                            count+=1;
                        }
                    }
                    average=sum/count;
                    ra_avg_net.setText(String.valueOf(df.format(average)));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //average for plumber

        mDatabase.child("Plumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    if(map.get("status").equals("Completed")){
                        Object avg=map.get("rating");
                        Float gvalue=Float.parseFloat(String.valueOf(avg));
                        if(!gvalue.equals(0.0f)){
                            sum +=gvalue;
                            count+=1;
                        }
                    }
                    average=sum/count;
                    ra_avg_plumber.setText(String.valueOf(df.format(average)));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //average for painting

        mDatabase.child("Painting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    if(map.get("status").equals("Completed")){
                        Object avg=map.get("rating");
                        Float gvalue=Float.parseFloat(String.valueOf(avg));
                        if(!gvalue.equals(0.0f)){
                            sum +=gvalue;
                            count+=1;
                        }
                    }
                    average=sum/count;
                    ra_avg_paint.setText(String.valueOf(df.format(average)));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //average for carpenter

        mDatabase.child("Carpenter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    if(map.get("status").equals("Completed")){
                        Object avg=map.get("rating");
                        Float gvalue=Float.parseFloat(String.valueOf(avg));
                        if(!gvalue.equals(0.0f)){
                            sum +=gvalue;
                            count+=1;
                        }
                    }
                    average=sum/count;
                    ra_avg_car.setText(String.valueOf(df.format(average)));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Rating_and_Feedback.this, dashboard.class));
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
                    startActivity(new Intent(Rating_and_Feedback.this, ManualComplaint_page.class));
                    break;
                case R.id.bottom_feedback:

                    startActivity(new Intent(Rating_and_Feedback.this,Rating_and_Feedback.class));
                    break;
                case R.id.bottom_home:
                    startActivity(new Intent(Rating_and_Feedback.this,dashboard.class));
                    break;
                case R.id.bottom_report:
                    startActivity(new Intent(Rating_and_Feedback.this,Rating_and_Feedback.class));
                    break;

                case R.id.bottom_emer:
                    startActivity(new Intent(Rating_and_Feedback.this,emergencyContact.class));
                    break;

            }
            return false;
        }
    };

}