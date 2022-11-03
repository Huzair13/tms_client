package com.example.map_my_sona.rating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.map_my_sona.R;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.rating.rating_view.rating_assets;
import com.example.map_my_sona.rating.rating_view.rating_electricity;
import com.example.map_my_sona.rating.rating_view.rating_network;
import com.example.map_my_sona.rating.rating_view.rating_carpenter;
import com.example.map_my_sona.rating.rating_view.rating_others;
import com.example.map_my_sona.rating.rating_view.rating_painting;
import com.example.map_my_sona.rating.rating_view.rating_plumbering;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Map;

public class Rating_and_Feedback extends AppCompatActivity {

    private LinearLayout ll_rating_elec,ll_rating_net,ll_rating_car,ll_rating_paint,ll_rating_plumber,ll_rating_assets,ll_rating_others;

    private TextView ra_avg_elec,ra_avg_paint,ra_avg_plumber,ra_avg_car,ra_avg_net,ra_assets,ra_others;
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
        ll_rating_assets=(LinearLayout)findViewById(R.id.LL_rating_assets);
        ll_rating_others=(LinearLayout)findViewById(R.id.LL_rating_others);


        ra_avg_elec=(TextView) findViewById(R.id.ra_avg_elec);
        ra_avg_car=(TextView)findViewById(R.id.ra_avg_car);
        ra_avg_net=(TextView)findViewById(R.id.ra_avg_net);
        ra_avg_plumber=(TextView)findViewById(R.id.ra_avg_plumber);
        ra_avg_paint=(TextView)findViewById(R.id.ra_avg_paint);
        ra_assets=(TextView)findViewById(R.id.rating_asset_field);
        ra_others=(TextView)findViewById(R.id.rating_others_field);

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

        ll_rating_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Rating_and_Feedback.this, rating_assets.class));
            }
        });

        ll_rating_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Rating_and_Feedback.this, rating_others.class));
            }
        });

        //ASSETS RATING
        mDatabase= FirebaseDatabase.getInstance().getReference().child("complaints");

        mDatabase.child("Assets").addValueEventListener(new ValueEventListener() {
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
                    ra_assets.setText(String.valueOf(df.format(average)));
                }
                if(average>=4.0 && average<=5.0){
                    ra_assets.setBackgroundResource(R.color.green);
                }
                else if(average>=3.0 && average<4.0){
                    ra_assets.setBackgroundResource(R.color.orange);
                }
                else if(average<3.0 && average>=0.0){
                    ra_assets.setBackgroundResource(R.color.Red);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //OTHERS RATING
        mDatabase= FirebaseDatabase.getInstance().getReference().child("complaints");
        mDatabase.child("Others").addValueEventListener(new ValueEventListener() {
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
                    ra_others.setText(String.valueOf(df.format(average)));
                }
                if(average>=4.0 && average<=5.0){
                    ra_others.setBackgroundResource(R.color.green);
                }
                else if(average>=3.0 && average<4.0){
                    ra_others.setBackgroundResource(R.color.orange);
                }
                else if(average<3.0 && average>=0.0){
                    ra_others.setBackgroundResource(R.color.Red);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //ELECTRICITY
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

        mDatabase.child("Pluming").addValueEventListener(new ValueEventListener() {
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
                startActivity(new Intent(Rating_and_Feedback.this, dashboard.class));
            }
        });

    };

}