package com.example.map_my_sona.complaints.HistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.complaints_history_Adapter;
import com.example.map_my_sona.dashboard;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails_Plumber extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history_plumber;
    DatabaseReference reference_complaints_history_plumber;
    complaints_history_Adapter adapter_complaint_history_plumber;
    ArrayList<Complaint_details> arrayList_complaints_history_plumber;

    TextInputLayout hisfliter_plumber;
    AutoCompleteTextView hisflitertext_plumber;
    Spinner spin_plumber;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_plumber);

        recyclerView_complaints_history_plumber=findViewById(R.id.recyclerview_complaints_history_plumber);
        reference_complaints_history_plumber= FirebaseDatabase.getInstance().getReference("complaints").child("Plumber");

        recyclerView_complaints_history_plumber.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView_complaints_history_plumber.setLayoutManager(linearLayoutManager);

        //recyclerView_complaints_history_plumber.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history_plumber=new ArrayList<>();
        adapter_complaint_history_plumber = new complaints_history_Adapter(arrayList_complaints_history_plumber,this);
        recyclerView_complaints_history_plumber.setAdapter(adapter_complaint_history_plumber);

        reference_complaints_history_plumber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history_plumber.add(user);
                }
                adapter_complaint_history_plumber.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Plumber.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Complaints_HistoryDetails_Plumber.this, dashboard.class));
            }
        });

    }

}