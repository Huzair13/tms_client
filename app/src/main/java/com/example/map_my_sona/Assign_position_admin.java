package com.example.map_my_sona;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Assign_position_admin extends AppCompatActivity {

    private EditText username;
    private TextView uname, email, pos;
    private String uname_str, email_str, pos_str, userID;
    private Button get_details;
    private TableLayout table_layout;
    private String pos_str1;

    AlertDialog.Builder builder;
    private Button yes,update_pos;
    private Spinner change_pos_spinner;
    private TextView txtview_change_pos;
    private String new_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_position_admin);

        username = (EditText) findViewById(R.id.username_assign_admin);

        builder=new AlertDialog.Builder(this);

        uname = (TextView) findViewById(R.id.uname_admin_assign);
        email = (TextView) findViewById(R.id.email_admin_assign);
        pos = (TextView) findViewById(R.id.position_admin_assign);
        table_layout = (TableLayout) findViewById(R.id.table_layout_admin_assign);
        get_details = (Button) findViewById(R.id.get_details_admin_assign);

        yes=(Button)findViewById(R.id.yes_change_the_position);
        update_pos=(Button)findViewById(R.id.update_position_admin_assign);
        change_pos_spinner=(Spinner)findViewById(R.id.change_position_admin_assign_spinner);
        txtview_change_pos=(TextView)findViewById(R.id.txtview_change_pos);

        String[] positions={"Position","user","admin","superuser","technician"};
        change_pos_spinner.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,positions));

        change_pos_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                new_pos=change_pos_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_pos_spinner.setVisibility(View.VISIBLE);
                update_pos.setVisibility(View.VISIBLE);
            }
        });

        get_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIsempty();
            }
        });

        update_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checKVal();
            }
        });

    }

    private void checKVal() {
        if(new_pos.equals("Position")){
            Toast.makeText(this, "Please select the position to proceed", Toast.LENGTH_SHORT).show();
            change_pos_spinner.requestFocus();
        }
        else {
            update_position();
        }
    }

    private void update_position() {

        HashMap hp=new HashMap();
        hp.put("position",new_pos);

        builder.setTitle("Alert")
                .setMessage("Are you sure to update the position ??")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("userDetails").child(uname_str);
                        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("users").child(userID);


                        reference.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                reference1.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        Toast.makeText(Assign_position_admin.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Assign_position_admin.this, AdminDashboard.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Assign_position_admin.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Assign_position_admin.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
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

    private void checkIsempty() {
        uname_str = username.getText().toString();
        if (uname_str.isEmpty()) {
            Toast.makeText(Assign_position_admin.this, "Please enter username to get the user details", Toast.LENGTH_SHORT).show();
        } else {
            showdetails(uname_str);
        }
    }

    private void showdetails(String uname_str) {
        getDetailsUser(uname_str);
    }

    private void getDetailsUser(String uname_str) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("userDetails").child(uname_str);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    pos_str=snapshot.child("position").getValue(String.class);
                    userID = snapshot.child("userID").getValue(String.class);
                    email_str = snapshot.child("email").getValue(String.class);

                    uname.setText(uname_str);
                    email.setText(email_str);
                    pos.setText(pos_str);

                    table_layout.setVisibility(View.VISIBLE);
                    txtview_change_pos.setVisibility(View.VISIBLE);
                    yes.setVisibility(View.VISIBLE);
                } else {
                    table_layout.setVisibility(View.GONE);
                    Toast.makeText(Assign_position_admin.this, "Username is not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String getpostion() {
        if (userID != null) {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("users");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        pos_str1 = snapshot.child(userID).child("position").getValue(String.class);
                    } else {
                        table_layout.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return pos_str1;
    }
}