package com.example.map_my_sona.complaints.HistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.complaints_history_Adapter;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.loginpage;
import com.example.map_my_sona.report_page;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails_Carpenter extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history_carpenter;
    DatabaseReference reference_complaints_history_carpenter;
    complaints_history_Adapter adapter_complaint_history_carpenter;
    ArrayList<Complaint_details> arrayList_complaints_history_carpenter;


    //fliter
    TextInputLayout hisfliter_carpenter;
    AutoCompleteTextView hisflitertext_carpenter;
    Spinner spin_carpenter;
    MaterialToolbar toolbar;
    private DatabaseReference refDash;
    AlertDialog.Builder builder11;
    FirebaseAuth mAuth;
//    TextView  TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_carpenter);

        recyclerView_complaints_history_carpenter=findViewById(R.id.recyclerview_complaints_history_carpenter);
        reference_complaints_history_carpenter= FirebaseDatabase.getInstance().getReference("complaints").child("Carpenter");

        recyclerView_complaints_history_carpenter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());

        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        recyclerView_complaints_history_carpenter.setLayoutManager(linearLayoutManager);
        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());


        //recyclerView_complaints_history_carpenter.setLayoutManager(new LinearLayoutManager(this));
        arrayList_complaints_history_carpenter=new ArrayList<>();
        adapter_complaint_history_carpenter = new complaints_history_Adapter(arrayList_complaints_history_carpenter,this);
        recyclerView_complaints_history_carpenter.setAdapter(adapter_complaint_history_carpenter);

        builder11=new AlertDialog.Builder(this);
        mAuth=FirebaseAuth.getInstance();

        reference_complaints_history_carpenter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history_carpenter.add(user);
                }
                adapter_complaint_history_carpenter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Carpenter.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        toolbar= findViewById(R.id.topAppBar_carpenter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin")){
                            startActivity(new Intent(Complaints_HistoryDetails_Carpenter.this, dashboard.class));
                        }
                        else{
                            builder11.setTitle("Alert")
                                    .setMessage("Are you sure to Log out ?")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            mAuth.signOut();
                                            Intent intent=new Intent(Complaints_HistoryDetails_Carpenter.this, loginpage.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            finish();
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
//
//    @Override
//    public void onBackPressed() {
//        Toast.makeText(this, "You are already in the home page", Toast.LENGTH_SHORT).show();
//    }
}