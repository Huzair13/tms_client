package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.map_my_sona.manualComplaints.ManualComplaint_page;
import com.example.map_my_sona.rating.Rating_and_Feedback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class update_database extends AppCompatActivity {

    private EditText sn,make,model,procurement,powerRating,wexpiry,wperiod,ins_by,ins_date,mob,uniqueID,dep_of_pro,location;
    private Button update,get_details;
    AlertDialog.Builder builder;
    //private String sn_str,make_str,model_str,procurement_str,powerRating_str,wexpiry_str,wperiod_str,ins_by_str,ins_date_str,mob_str;
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
//        mob=(EditText) findViewById(R.id.mob_unit_update_ad);
        dep_of_pro=(EditText)findViewById(R.id.dep_of_pro_unit_update_ad);
        location=(EditText)findViewById(R.id.location_unit_update_ad);

        get_details=(Button)findViewById(R.id.get_details_up_ad);

        updateDatabase=(TableLayout)findViewById(R.id.table_layout_update_ad);
        updateDatabase.setVisibility(View.GONE);

        update=(Button) findViewById(R.id.update_database_admin);
        update.setVisibility(View.GONE);


        get_details.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                uniqueIDadmin();
            }
        });


    };




    private void uniqueIDadmin() {
        String unique_id_str=uniqueID.getText().toString();
        if(unique_id_str.isEmpty()){
            Toast.makeText(update_database.this, "Unique ID is empty can't fetch data", Toast.LENGTH_SHORT).show();
        }else{
            show_details(unique_id_str);
        }
    }

    private void show_details(String unique_id_str) {

        reference= FirebaseDatabase.getInstance().getReference("Datas").child(unique_id_str);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {

                    updateDatabase.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);

//                    String mob_str1 = snapshot.child("mob").getValue(String.class);
                    String sn_str1 = String.valueOf(snapshot.child("sn_no").getValue(Integer.class));
                    String make_str1= snapshot.child("make").getValue(String.class);
                    String model_str1 = snapshot.child("model").getValue(String.class);
                    String procurement_str1 = snapshot.child("procurement").getValue(String.class);
                    String powerRating_str1 = snapshot.child("power_rating").getValue(String.class);
                    String wexpiry_str1 = snapshot.child("wexpiry").getValue(String.class);
                    String wperiod_str1 = snapshot.child("wperiod").getValue(String.class);
                    String ins_by_str1 = snapshot.child("ins_by").getValue(String.class);
                    String ins_date_str1 = snapshot.child("ins_date").getValue(String.class);
                    String dep_of_pro_str1 =snapshot.child("dep_of_pro").getValue(String.class);
                    String location1=snapshot.child("location").getValue(String.class);

                    sn.setText(sn_str1);
                    make.setText(make_str1);
                    model.setText(model_str1);
                    procurement.setText(procurement_str1);
                    powerRating.setText(powerRating_str1);
                    wexpiry.setText(wexpiry_str1);
                    wperiod.setText(wperiod_str1);
                    ins_by.setText(ins_by_str1);
                    ins_date.setText(ins_date_str1);
//                    mob.setText(mob_str1);
                    dep_of_pro.setText(dep_of_pro_str1);
                    location.setText(location1);

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            update_database_ad();
                        }
                    });

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

        int sn_str2=Integer.valueOf(String.valueOf(sn.getText()));
        String make_str2=make.getText().toString();
        String model_str2=model.getText().toString();
        String procurement_str2=procurement.getText().toString();
        String powerRating_str2=powerRating.getText().toString();
        String wexpiry_str2=wexpiry.getText().toString();
        String wperiod_str2=wperiod.getText().toString();
        String ins_by_str2=ins_by.getText().toString();
        String ins_date_str2=ins_date.getText().toString();
//        String mob_str2=mob.getText().toString();
        String dep_of_pro_str2=dep_of_pro.getText().toString();
        String location2=location.getText().toString();

        HashMap hp=new HashMap();
        hp.put("sn_no",sn_str2);
        hp.put("make",make_str2);
        hp.put("model",model_str2);
        hp.put("procurement",procurement_str2);
        hp.put("power_rating",powerRating_str2);
        hp.put("wexpiry",wexpiry_str2);
        hp.put("wperiod",wperiod_str2);
        hp.put("ins_by",ins_by_str2);
        hp.put("ins_date",ins_date_str2);
//        hp.put("mob",mob_str2);
        hp.put("dep_of_pro",dep_of_pro_str2);
        hp.put("location",location2);

        builder.setTitle("Alert")
                .setMessage("Are you sure to update the data ??")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        reference.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(update_database.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(update_database.this, dashboard.class);
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
}
