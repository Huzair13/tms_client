package com.example.map_my_sona.admin;

import static android.R.layout.simple_spinner_dropdown_item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Incharge_mobile_change extends AppCompatActivity {

    private Button clicker_mob,clicker_mob_old;
    private DatabaseReference reference;
    private EditText mob_new;
    private Spinner dep_Spinner;
    AlertDialog.Builder builder;
    private TableLayout table;
    private TextView oldmob_txt;

    private TextView old_mob_display;
    String dep_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incharge_mobile_change);

        clicker_mob=findViewById(R.id.clicker_mob);
        dep_Spinner=findViewById(R.id.dep_of_mob_change);
        mob_new=findViewById(R.id.new_mob);
        clicker_mob_old=findViewById(R.id.clicker_mob_old);
        old_mob_display=findViewById(R.id.mob_old_display);
        table=findViewById(R.id.table_old_mob);
        oldmob_txt=findViewById(R.id.txtview_old_mob);

        reference= FirebaseDatabase.getInstance().getReference().child("Emails");

        clicker_mob_old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValididtyDisplay();
            }
        });

        builder=new AlertDialog.Builder(this);

        String[] dep_Str={"Department","Admin","SuperAdmin","Electricity","Plumber","Network","Carpenter","Painting","other"};
        dep_Spinner.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,dep_Str));

        dep_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dep_str=dep_Spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        clicker_mob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValididty();
            }
        });

    }

    private void checkValididty() {
        if(dep_str.equals("Department")){
            Toast.makeText(this, "Please select the department", Toast.LENGTH_SHORT).show();
        }else if(mob_new.getText().toString().isEmpty()){
            Toast.makeText(this, "Please Enter the new Mobile Number", Toast.LENGTH_SHORT).show();
        }
        else{
            update();
        }
    }

    private void update() {
        Long mob_new_str=Long.parseLong(mob_new.getText().toString());

        HashMap hp=new HashMap();
        hp.put("mobile",mob_new_str);

        builder.setTitle("Alert")
                .setMessage("Are you sure to update the data ??")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        reference.child(dep_str).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(Incharge_mobile_change.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Incharge_mobile_change.this, DetailsAssignAdmin.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Incharge_mobile_change.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
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

    private void showoldMob() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String old_email_str=snapshot.child(dep_str).child("mobile").getValue(Long.class).toString() ;
                old_mob_display.setText(old_email_str.toString());

                oldmob_txt.setVisibility(View.VISIBLE);
                table.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkValididtyDisplay() {
        if(dep_str.equals("Department")){
            Toast.makeText(this, "Please select the department", Toast.LENGTH_SHORT).show();
        }else{
            showoldMob();
        }
    }
}