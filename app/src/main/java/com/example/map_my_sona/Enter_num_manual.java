package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.complaints.ComplaintPage_user;
import com.example.map_my_sona.complaints.complaint_Page;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Enter_num_manual extends AppCompatActivity {

    TextView tv;
    Button bt;
    DatabaseReference databaseReference;
    MaterialToolbar toolbar;
    private DatabaseReference refDash,reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_num_manual);

        tv=findViewById(R.id.editTextTextPersonName);
        bt=findViewById(R.id.button);
        databaseReference = FirebaseDatabase.getInstance().getReference("Datas");
        toolbar=findViewById(R.id.topAppBar);

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tv_str=tv.getText().toString();
                if(tv_str.isEmpty()){
                    Toast.makeText(Enter_num_manual.this, "Cant be empty", Toast.LENGTH_SHORT).show();
                }
                else if(!tv_str.isEmpty()){
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(tv_str)){
                                refDash.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if((snapshot.child("position").getValue(String.class)).equals("user")){
                                            Intent intent = new Intent(getBaseContext(), ComplaintPage_user.class);
                                            intent.putExtra("SCAN_RESULT", tv_str);
                                            startActivity(intent);
                                        }
                                        else{
                                            Intent intent = new Intent(getBaseContext(), complaint_Page.class);
                                            intent.putExtra("SCAN_RESULT", tv_str);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            else{
                                Toast.makeText(Enter_num_manual.this, "Invaild Details", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin")){
                            startActivity(new Intent(Enter_num_manual.this, dashboard.class));
                        }
                        else{
                            startActivity(new Intent(Enter_num_manual.this, ScannerPage.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}