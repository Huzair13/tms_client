package com.example.map_my_sona.complaints.HistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.complaints_history_Adapter;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails_Networks extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history_networks;
    DatabaseReference reference_complaints_history_networks;
    complaints_history_Adapter adapter_complaint_history_networks;
    ArrayList<Complaint_details> arrayList_complaints_history_networks;

    //fliter
    TextInputLayout hisfliter_networks;
    AutoCompleteTextView hisflitertext_networks;
    Spinner spin_networks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_networks);

        recyclerView_complaints_history_networks=findViewById(R.id.recyclerview_complaints_history_networks);
        reference_complaints_history_networks= FirebaseDatabase.getInstance().getReference("complaints").child("Networks");

        recyclerView_complaints_history_networks.setHasFixedSize(true);
        recyclerView_complaints_history_networks.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history_networks=new ArrayList<>();
        adapter_complaint_history_networks = new complaints_history_Adapter(arrayList_complaints_history_networks,this);
        recyclerView_complaints_history_networks.setAdapter(adapter_complaint_history_networks);

        reference_complaints_history_networks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history_networks.add(user);
                }
                adapter_complaint_history_networks.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Networks.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}