package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class complaint_Page extends AppCompatActivity {
    public static TextView scanText;

    private String make_str,model_str,sn_Str,insBy_str,insDate_str,powerRating_str,procurement_str,wexpiry_str,wperiod_str;

    TextView sn , make, model,ins_by,insDate,powerRating,procurement,wexpiry,wperiod;
    DatabaseReference databaseReference;
    //FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);

        sn=(TextView) findViewById(R.id.sn_unit);
        model=(TextView) findViewById(R.id.model_unit);
        make=(TextView) findViewById(R.id.make_unit);
        ins_by=(TextView) findViewById(R.id.ins_by_unit);
        insDate=(TextView)findViewById(R.id.ins_date_unit);
        powerRating=(TextView)findViewById(R.id.powerRating_unit);
        procurement=(TextView)findViewById(R.id.procurement_unit);
        wexpiry=(TextView)findViewById(R.id.warranty_exp_unit);
        wperiod=(TextView)findViewById(R.id.warranty_unit);


        scanText=(TextView) findViewById(R.id.textView);
        String s = getIntent().getStringExtra("SCAN_RESULT");


        databaseReference=FirebaseDatabase.getInstance().getReference("Datas").child(s);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sn_Str=snapshot.child("sn").getValue(String.class);
                make_str=snapshot.child("make").getValue(String.class);
                model_str=snapshot.child("model").getValue(String.class);
                insBy_str=snapshot.child("insBy").getValue(String.class);
                insDate_str=snapshot.child("insDate").getValue(String.class);
                powerRating_str=snapshot.child("power_rating").getValue(String.class);
                procurement_str=snapshot.child("procurement").getValue(String.class);
                wexpiry_str=snapshot.child("wexpiry").getValue(String.class);
                wperiod_str=snapshot.child("wperiod").getValue(String.class);


                sn.setText(sn_Str);
                make.setText(make_str);
                model.setText(model_str);
                ins_by.setText(insBy_str);
                insDate.setText(insDate_str);
                powerRating.setText(powerRating_str);
                procurement.setText(procurement_str);
                wperiod.setText(wperiod_str);
                wexpiry.setText(wexpiry_str);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //scanText.setText(s);
    }
}