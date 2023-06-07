package com.example.tms_sona2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Map;

public class Rating_and_Feedback_user extends AppCompatActivity {

    private LinearLayout ll_rating_elec,ll_rating_net,ll_rating_car,ll_rating_paint,ll_rating_plumber,ll_rating_assets,ll_rating_others;

    private TextView ra_avg_elec,ra_avg_paint,ra_avg_plumber,ra_avg_car,ra_avg_net,ra_assets,ra_others;
    private DatabaseReference mDatabase;
    String status;
    MaterialToolbar toolbar;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_and_feedback_user);

        ll_rating_elec=(LinearLayout)findViewById(R.id.LL_rating_elec_user);
        ll_rating_net=(LinearLayout)findViewById(R.id.LL_rating_net_user);
        ll_rating_car=(LinearLayout)findViewById(R.id.LL_rating_car_user);
        ll_rating_paint=(LinearLayout)findViewById(R.id.LL_rating_paint_user);
        ll_rating_plumber=(LinearLayout)findViewById(R.id.LL_rating_plumber_user);
        ll_rating_assets=(LinearLayout)findViewById(R.id.LL_rating_assets_user);
        ll_rating_others=(LinearLayout)findViewById(R.id.LL_rating_others_user);


        ra_avg_elec=(TextView) findViewById(R.id.ra_avg_elec_user);
        ra_avg_car=(TextView)findViewById(R.id.ra_avg_car_user);
        ra_avg_net=(TextView)findViewById(R.id.ra_avg_net_user);
        ra_avg_plumber=(TextView)findViewById(R.id.ra_avg_plumber_user);
        ra_avg_paint=(TextView)findViewById(R.id.ra_avg_paint_user);
        ra_assets=(TextView)findViewById(R.id.rating_asset_field_user);
        ra_others=(TextView)findViewById(R.id.rating_others_field_user);

        toolbar= findViewById(R.id.topAppBar_userRat);


        //ASSETS RATING
        mDatabase= FirebaseDatabase.getInstance().getReference().child("UserFeedback");

        mDatabase.child("assets").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("rate_value");
                    Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(!gvalue.equals(0.0f)){
                        sum +=gvalue;
                        count+=1;
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
        mDatabase= FirebaseDatabase.getInstance().getReference().child("UserFeedback");
        mDatabase.child("others").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("rate_value");
                    Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(!gvalue.equals(0.0f)){
                        sum +=gvalue;
                        count+=1;
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
        mDatabase= FirebaseDatabase.getInstance().getReference().child("UserFeedback");

        mDatabase.child("electricity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("rate_value");
                    Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(!gvalue.equals(0.0f)){
                        sum +=gvalue;
                        count+=1;
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

        mDatabase.child("network").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("rate_value");
                    Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(!gvalue.equals(0.0f)){
                        sum +=gvalue;
                        count+=1;
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

        mDatabase.child("plumbing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("rate_value");
                    Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(!gvalue.equals(0.0f)){
                        sum +=gvalue;
                        count+=1;
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

        mDatabase.child("painting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("rate_value");
                    Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(!gvalue.equals(0.0f)){
                        sum +=gvalue;
                        count+=1;
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

        mDatabase.child("carpenter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Float sum=0.0f;
                int count=0;
                Float average=0.0f;
                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map=(Map<String, Object>) ds.getValue();
                    Object avg=map.get("rate_value");
                    Float gvalue=Float.parseFloat(String.valueOf(avg));
                    if(!gvalue.equals(0.0f)){
                        sum +=gvalue;
                        count+=1;
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
                startActivity(new Intent(Rating_and_Feedback_user.this, dashboard.class));
            }
        });

    };
}