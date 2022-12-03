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

public class Complaint_email_assign extends AppCompatActivity {

    private Button clicker_email,clicker_email_old;
    private DatabaseReference reference;
    private EditText email_new;
    private Spinner dep_Spinner;
    AlertDialog.Builder builder;
    private TableLayout table;
    private TextView oldemail_txt;

    private TextView old_email_display;
    String dep_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_email_assign);

        clicker_email=findViewById(R.id.clicker_email);
        dep_Spinner=findViewById(R.id.dep_of_email_change);
        email_new=findViewById(R.id.email_of_email_change);
        clicker_email_old=findViewById(R.id.clicker_email_old);
        old_email_display=findViewById(R.id.email_old_display);
        table=findViewById(R.id.table_old_email);
        oldemail_txt=findViewById(R.id.txtview_old_email);

        reference= FirebaseDatabase.getInstance().getReference().child("Emails");

        clicker_email_old.setOnClickListener(new View.OnClickListener() {
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

        clicker_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkValididty();
            }
        });
    }

    private void checkValididtyDisplay() {
        if(dep_str.equals("Department")){
            Toast.makeText(this, "Please select the department", Toast.LENGTH_SHORT).show();
        }else{
            showoldEmail();
        }
    }

    private void showoldEmail() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String old_email_str=snapshot.child(dep_str).child("email").getValue(String.class);
                old_email_display.setText(old_email_str);

                oldemail_txt.setVisibility(View.VISIBLE);
                table.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkValididty() {
        if(dep_str.equals("Department")){
            Toast.makeText(this, "Please select the department", Toast.LENGTH_SHORT).show();
        }else if(email_new.getText().toString().isEmpty()){
            Toast.makeText(this, "Please Enter the new email Address", Toast.LENGTH_SHORT).show();
        }
        else{
            update();
        }
    }

    private void update() {
        String email_new_Str=email_new.getText().toString();

        HashMap hp=new HashMap();
        hp.put("email",email_new_Str);

        builder.setTitle("Alert")
                .setMessage("Are you sure to update the data ??")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        reference.child(dep_str).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(Complaint_email_assign.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Complaint_email_assign.this, DetailsAssignAdmin.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Complaint_email_assign.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
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