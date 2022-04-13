package com.example.map_my_sona.manualComplaints.ManualHistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.manualComplaints.ManualComplaint_details;
import com.example.map_my_sona.manualComplaints.manual_complaints_history_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails_Networks_manual extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history_manual_network;
    DatabaseReference reference_complaints_history_manual_network;
    manual_complaints_history_adapter adapter_complaint_history_manual_network;
    ArrayList<ManualComplaint_details> arrayList_complaints_history_manual_network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_networks_manual);

        recyclerView_complaints_history_manual_network=findViewById(R.id.recyclerview_complaints_history_manual_network);
        reference_complaints_history_manual_network= FirebaseDatabase.getInstance().getReference("Manual complaints").child("Networks");

        recyclerView_complaints_history_manual_network.setHasFixedSize(true);
        recyclerView_complaints_history_manual_network.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history_manual_network=new ArrayList<>();
        adapter_complaint_history_manual_network = new manual_complaints_history_adapter(arrayList_complaints_history_manual_network,this);
        recyclerView_complaints_history_manual_network.setAdapter(adapter_complaint_history_manual_network);

        reference_complaints_history_manual_network.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ManualComplaint_details user =dataSnapshot.getValue(ManualComplaint_details.class);
                    arrayList_complaints_history_manual_network.add(user);
                }
                adapter_complaint_history_manual_network.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Networks_manual.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}