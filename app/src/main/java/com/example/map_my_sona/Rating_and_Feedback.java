package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.map_my_sona.complaints.Complaint_details;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Rating_and_Feedback extends AppCompatActivity {

    private TextView ra_avg;
    private DatabaseReference mDatabase_e;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_and_feedback);

        ra_avg=(TextView) findViewById(R.id.ra_avg);

        mDatabase_e= FirebaseDatabase.getInstance().getReference().child("complaints").child("Electricity");

        mDatabase_e.addValueEventListener(new ValueEventListener() {
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
                    ra_avg.setText(String.valueOf(average));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}