package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.complaints.Complaints_HistoryDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class update_database extends AppCompatActivity {

    private EditText sn,make,model,procurement,powerRating,wexpiry,wperiod,ins_by,ins_date,mob,uniqueID;
    private Button update,get_details;
    AlertDialog.Builder builder;
    private String sn_str,make_str,model_str,procurement_str,powerRating_str,wexpiry_str,wperiod_str,ins_by_str,ins_date_str,mob_str;
    private DatabaseReference reference;
    private TableLayout updateDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);

        builder=new AlertDialog.Builder(this);

        uniqueID=(EditText) findViewById(R.id.uniqueid_update_ad);

        sn=(EditText) findViewById(R.id.sn_unit_update_ad);
        make=(EditText) findViewById(R.id.make_unit_update_ad);
        model=(EditText) findViewById(R.id.model_unit_update_ad);
        procurement=(EditText) findViewById(R.id.procurement_unit_update_ad);
        powerRating=(EditText) findViewById(R.id.powerRating_unit_update_ad);
        wexpiry=(EditText) findViewById(R.id.warranty_exp_unit_update_ad);
        wperiod=(EditText) findViewById(R.id.warranty_unit_update_ad);
        ins_by=(EditText) findViewById(R.id.ins_by_unit_update_ad);
        ins_date=(EditText) findViewById(R.id.ins_date_unit_update_ad);
        mob=(EditText) findViewById(R.id.mob_unit_update_ad);

        get_details=(Button)findViewById(R.id.get_details_up_ad);

        updateDatabase=(TableLayout)findViewById(R.id.table_layout_update_ad);
        updateDatabase.setVisibility(View.GONE);

        update=(Button) findViewById(R.id.update_database_admin);
        update.setVisibility(View.GONE);


        get_details.setOnClickListener(new View.OnClickListener() {
            String unique_id_str=uniqueID.getText().toString();
            @Override
            public void onClick(View view) {
                if(unique_id_str.isEmpty()){
                    Toast.makeText(update_database.this, "Unique ID is empty can't fetch data", Toast.LENGTH_SHORT).show();
                }else{
                    show_details(unique_id_str);
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_database_ad();
            }
        });

    }

    private void show_details(String unique_id_str) {
        reference= FirebaseDatabase.getInstance().getReference("Datas").child(unique_id_str);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {

                    updateDatabase.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);

                    mob_str = snapshot.child("mob").getValue(String.class);
                    sn_str = snapshot.child("sn_no").getValue(String.class);
                    make_str = snapshot.child("make").getValue(String.class);
                    model_str = snapshot.child("model").getValue(String.class);
                    procurement_str = snapshot.child("procurement").getValue(String.class);
                    powerRating_str = snapshot.child("power_rating").getValue(String.class);
                    wexpiry_str = snapshot.child("wexpiry").getValue(String.class);
                    wperiod_str = snapshot.child("wperiod").getValue(String.class);
                    ins_by_str = snapshot.child("ins_by").getValue(String.class);
                    ins_date_str = snapshot.child("ins_date").getValue(String.class);

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
                }else{
                    Toast.makeText(update_database.this, "Entered ID is not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void update_database_ad() {

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap hp=new HashMap();
                hp.put("sn_no",sn_str);
                hp.put("make",make_str);
                hp.put("model",model_str);
                hp.put("procurement",procurement_str);
                hp.put("power_rating",powerRating_str);
                hp.put("wexpiry",wexpiry_str);
                hp.put("wperiod",wperiod_str);
                hp.put("ins_by",ins_by_str);
                hp.put("ins_date",ins_date_str);
                hp.put("mob",mob_str);

                builder.setTitle("Alert")
                        .setMessage("Are you sure to close the complaint ??")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                reference.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        Toast.makeText(update_database.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(update_database.this, admin_dashboard.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(update_database.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
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
}
