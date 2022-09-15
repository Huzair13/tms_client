package com.example.map_my_sona.complaints.HistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.complaints_history_Adapter;
import com.example.map_my_sona.dashboard;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class complaint_HistoryDetails_others extends AppCompatActivity {
    RecyclerView recyclerView_complaints_history_others;
    DatabaseReference reference_complaints_history_others;
    complaints_history_Adapter adapter_complaint_history_others;
    ArrayList<Complaint_details> arrayList_complaints_history_others;
    private DatabaseReference refDash;

    TextInputLayout hisfliter;
    AutoCompleteTextView hisflitertext;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_history_details_others);

        recyclerView_complaints_history_others=findViewById(R.id.recyclerview_complaints_history_others);
        reference_complaints_history_others= FirebaseDatabase.getInstance().getReference("complaints").child("Others");

        recyclerView_complaints_history_others.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView_complaints_history_others.setLayoutManager(linearLayoutManager);
        //recyclerView_complaints_history.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history_others=new ArrayList<>();
        adapter_complaint_history_others = new complaints_history_Adapter(arrayList_complaints_history_others,this);
        recyclerView_complaints_history_others.setAdapter(adapter_complaint_history_others);
        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());


        reference_complaints_history_others.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history_others.add(user);
                }
                adapter_complaint_history_others.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(complaint_HistoryDetails_others.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                refDash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String pos=snapshot.child("position").getValue(String.class);
                        if(pos.equals("admin")){
                            startActivity(new Intent(complaint_HistoryDetails_others.this, dashboard.class));
                        }
                        else{
                            startActivity(new Intent(complaint_HistoryDetails_others.this, AdminDashboard.class));
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