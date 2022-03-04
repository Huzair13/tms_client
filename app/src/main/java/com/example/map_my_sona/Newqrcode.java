package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.map_my_sona.complaints.Complaints_HistoryDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Newqrcode extends AppCompatActivity {

    private EditText unique_id_ad,sn_ad,make_ad,model_ad,procurement_ad,powerRating_ad,wperiod_ad,wexpiryDate_ad,insDate_ad,insBy_ad,mob_ad;
    private Button AddNew;
    AlertDialog.Builder builder;
    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newqrcode);

        reference= FirebaseDatabase.getInstance().getReference();

        builder=new AlertDialog.Builder(this);

//        unique_id_ad=(EditText) findViewById(R.id.uniqueid_update_admin);
//        sn_ad=(EditText) findViewById(R.id.sn_update_admin);
//        make_ad=(EditText) findViewById(R.id.make_update_admin);
//        model_ad=(EditText) findViewById(R.id.model_update_admin);
//        procurement_ad=(EditText) findViewById(R.id.procurement_update_admin);
//        powerRating_ad=(EditText) findViewById(R.id.powerRating_update_admin);
//        wperiod_ad=(EditText) findViewById(R.id.warranty_update_admin);
//        wexpiryDate_ad=(EditText) findViewById(R.id.warranty_exp_update_admin);
//        insBy_ad=(EditText) findViewById(R.id.ins_by_update_admin);
//        insDate_ad=(EditText) findViewById(R.id.ins_date_update_admin);
//        mob_ad=(EditText) findViewById(R.id.mob_update_admin);
//
//        AddNew=(Button) findViewById(R.id.newqrupdate_btn);

        AddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setTitle("Alert")
                        .setMessage("Are you sure to close the complaint ??")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getdata();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();

            }
        });

    }

    private void getdata() {

        String unique_id_str_ad=unique_id_ad.getText().toString();
        String sn_str_ad=sn_ad.getText().toString();
        String make_str_ad=make_ad.getText().toString();
        String model_str_ad=model_ad.getText().toString();
        String procurement_str_ad=procurement_ad.getText().toString();
        String wperiod_str_ad=wperiod_ad.getText().toString();
        String wexpiryDate_str_ad=wexpiryDate_ad.getText().toString();
        String insBy_str_ad=insBy_ad.getText().toString();
        String insDate_str_ad=insDate_ad.getText().toString();
        String powerRating_str_ad=powerRating_ad.getText().toString();
        String mob_str_ad=mob_ad.getText().toString();

        checkValidity(unique_id_str_ad,sn_str_ad,make_str_ad,model_str_ad,procurement_str_ad,powerRating_str_ad,wperiod_str_ad,wexpiryDate_str_ad,insDate_str_ad,insBy_str_ad,mob_str_ad);


    }


    private void checkValidity(String unique_id_str_ad,String sn_str_ad,String make_str_ad,String model_str_ad,String procurement_str_ad,String powerRating_str_ad,String wperiod_str_ad,String wexpiryDate_str_ad,String insDate_str_ad,String insBy_str_ad,String mob_str_ad) {
        if(unique_id_str_ad.isEmpty()){
            unique_id_ad.setError("It can't be empty");
            unique_id_ad.requestFocus();
        }else if(sn_str_ad.isEmpty()){
            sn_ad.setError("It can't be empty");
            sn_ad.requestFocus();
        }else if(make_str_ad.isEmpty()){
            make_ad.setError("It can't be empty");
            make_ad.requestFocus();
        }else if(model_str_ad.isEmpty()){
            model_ad.setError("It can't be empty");
            model_ad.requestFocus();
        }else if(procurement_str_ad.isEmpty()){
            procurement_ad.setError("It can't be empty");
            procurement_ad.requestFocus();
        }else if(powerRating_str_ad.isEmpty()){
            powerRating_ad.setError("It can't be empty");
            powerRating_ad.requestFocus();
        }else if(wperiod_str_ad.isEmpty()){
            wperiod_ad.setError("It can't be empty");
            wperiod_ad.requestFocus();
        }else if(wexpiryDate_str_ad.isEmpty()){
            wexpiryDate_ad.setError("It can't be empty");
            wexpiryDate_ad.requestFocus();
        }else if(insBy_str_ad.isEmpty()){
            insBy_ad.setError("It can't be empty");
            insBy_ad.requestFocus();
        }else if(insDate_str_ad.isEmpty()){
            insDate_ad.setError("It can't be empty");
            insDate_ad.requestFocus();
        }else if(mob_str_ad.isEmpty()){
            mob_ad.setError("It can't be empty");
            mob_ad.requestFocus();
        }else{
            AddData(unique_id_str_ad,sn_str_ad,make_str_ad,model_str_ad,procurement_str_ad,powerRating_str_ad,wperiod_str_ad,wexpiryDate_str_ad,insDate_str_ad,insBy_str_ad,mob_str_ad);
        }
       }


    private void AddData(String unique_id_str_ad,String sn_str_ad,String make_str_ad,String model_str_ad,String procurement_str_ad,String powerRating_str_ad,String wperiod_str_ad,String wexpiryDate_str_ad,String insDate_str_ad,String insBy_str_ad,String mob_str_ad) {


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Datas").child(unique_id_str_ad);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    Toast.makeText(Newqrcode.this, "Data for ID: " + unique_id_str_ad + " already exists", Toast.LENGTH_SHORT).show();
                    // Exist! Do whatever.

                } else {

                    dbRef = reference.child("Datas");
                    AddNewData addNewData = new AddNewData(sn_str_ad, make_str_ad, model_str_ad, procurement_str_ad, powerRating_str_ad, wperiod_str_ad, wexpiryDate_str_ad, insDate_str_ad, insBy_str_ad, mob_str_ad);

                    dbRef.child(unique_id_str_ad).setValue(addNewData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Newqrcode.this, "Data Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Newqrcode.this, admin_dashboard.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(Newqrcode.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed, how to handle?

            }

        });

    }
}