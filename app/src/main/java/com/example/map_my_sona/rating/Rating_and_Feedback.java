package com.example.map_my_sona.rating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.map_my_sona.R;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.emergencyContact;
import com.example.map_my_sona.manualComplaints.ManualComplaint_page;
import com.example.map_my_sona.rating.rating_view.rating_electricity;
import com.example.map_my_sona.rating.rating_view.rating_network;
import com.example.map_my_sona.rating.rating_view.rating_carpenter;
import com.example.map_my_sona.rating.rating_view.rating_painting;
import com.example.map_my_sona.rating.rating_view.rating_plumbering;
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

    private LinearLayout ll_rating_elec,ll_rating_net,ll_rating_car,ll_rating_paint,ll_rating_plumber;

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
        ll_rating_net=(LinearLayout)findViewById(R.id.LL_rating_net);
        ll_rating_car=(LinearLayout)findViewById(R.id.LL_rating_car);
        ll_rating_paint=(LinearLayout)findViewById(R.id.LL_rating_paint);
        ll_rating_plumber=(LinearLayout)findViewById(R.id.LL_rating_plumber);


        ra_avg_elec=(TextView) findViewById(R.id.ra_avg_elec);
        ra_avg_car=(TextView)findViewById(R.id.ra_avg_car);
        ra_avg_net=(TextView)findViewById(R.id.ra_avg_net);
        ra_avg_plumber=(TextView)findViewById(R.id.ra_avg_plumber);
        ra_avg_paint=(TextView)findViewById(R.id.ra_avg_paint);

        toolbar= findViewById(R.id.topAppBar);

        ll_rating_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Rating_and_Feedback.this, rating_electricity.class));
            }
        });

        ll_rating_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Rating_and_Feedback.this, rating_network.class));
            }
        });

        ll_rating_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Rating_and_Feedback.this, rating_carpenter.class));
            }
        });

        ll_rating_paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Rating_and_Feedback.this, rating_painting.class));
            }
        });

        ll_rating_plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Rating_and_Feedback.this, rating_plumbering.class));
            }
        });

        mDatabase= FirebaseDatabase.getInstance().getReference().child("complaints");

        mDatabase.child("Electricity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
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
                if(average>=4.0 && average<=5.0){
                    ra_avg_elec.setBackgroundResource(R.color.green);
                }
                else if(average>=3.0 && average<4.0){
                    ra_avg_elec.setBackgroundResource(R.color.orange);
                }
                else if(average<3.0 && average>=0.0){
                    ra_avg_elec.setBackgroundResource(R.color.Red);
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
                Float average=0.0f;
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
                if(average>=4.0 && average<=5.0){
                    ra_avg_net.setBackgroundResource(R.color.green);
                }
                else if(average>=3.0 && average<4.0){
                    ra_avg_net.setBackgroundResource(R.color.orange);
                }
                else if(average<3.0 && average>=0.0){
                    ra_avg_net.setBackgroundResource(R.color.Red);
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
                Float average=0.0f;
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

                if(average>=4.0 && average<=5.0){
                    ra_avg_plumber.setBackgroundResource(R.color.green);
                }
                else if(average>=3.0 && average<4.0){
                    ra_avg_plumber.setBackgroundResource(R.color.orange);
                }
                else if(average<3.0 && average>=0.0){
                    ra_avg_plumber.setBackgroundResource(R.color.Red);
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
                Float average=0.0f;
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

                if(average>=4.0 && average<=5.0){
                    ra_avg_paint.setBackgroundResource(R.color.green);
                }
                else if(average>=3.0 && average<4.0){
                    ra_avg_paint.setBackgroundResource(R.color.orange);
                }
                else if(average<3.0 && average>=0.0){
                    ra_avg_paint.setBackgroundResource(R.color.Red);
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
                Float average=0.0f;
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

                if(average>=4.0 && average<=5.0){
                    ra_avg_car.setBackgroundResource(R.color.green);
                }
                else if(average>=3.0 && average<4.0){
                    ra_avg_car.setBackgroundResource(R.color.orange);
                }
                else if(average<3.0 && average>=0.0){
                    ra_avg_car.setBackgroundResource(R.color.Red);
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
                    startActivity(new Intent(Rating_and_Feedback.this, emergencyContact.class));
                    break;

            }
            return false;
        }
    };

}