package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class complaint_Page extends AppCompatActivity{

    TextView sn,make,model,procurement,powerRating,wexpiry,wperiod,ins_by,ins_date,mob;

    String sn_str,make_str,model_str,procurement_str,powerRating_str,wexpiry_str,wperiod_str,ins_by_str,ins_date_str,mob_str;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        sn=(TextView)findViewById(R.id.sn_unit);
        make=(TextView)findViewById(R.id.make_unit);
        model=(TextView)findViewById(R.id.model_unit);
        powerRating=(TextView)findViewById(R.id.powerRating_unit);
        procurement=(TextView)findViewById(R.id.procurement_unit);
        wperiod=(TextView)findViewById(R.id.warranty_unit);
        wexpiry=(TextView)findViewById(R.id.warranty_exp_unit);
        ins_by=(TextView)findViewById(R.id.ins_by_unit);
        ins_date=(TextView)findViewById(R.id.ins_date_unit);
        mob=(TextView)findViewById(R.id.mob_unit);


        TextView scanText = (TextView) findViewById(R.id.textView);
        String s = getIntent().getStringExtra("SCAN_RESULT");

        databaseReference=FirebaseDatabase.getInstance().getReference("Datas").child(s);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mob_str=snapshot.child("mob").getValue(String.class);
                sn_str=snapshot.child("sn").getValue(String.class);
                make_str=snapshot.child("make").getValue(String.class);
                model_str=snapshot.child("model").getValue(String.class);
                procurement_str=snapshot.child("procurement").getValue(String.class);
                powerRating_str=snapshot.child("power_rating").getValue(String.class);
                wexpiry_str=snapshot.child("wexpiry").getValue(String.class);
                wperiod_str=snapshot.child("wperiod").getValue(String.class);
                ins_by_str=snapshot.child("insBy").getValue(String.class);
                ins_date_str=snapshot.child("insDate").getValue(String.class);


                sn.setText(sn_str);
                make.setText(make_str);
                model.setText(model_str);
                procurement.setText(procurement_str);
                powerRating.setText(powerRating_str);
                wexpiry.setText(wexpiry_str);
                wperiod.setText(wperiod_str);
                ins_by.setText(ins_by_str);
                ins_date.setText(ins_date_str);
                mob.setText(mob_str);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //scanText.setText(s);
    }
}