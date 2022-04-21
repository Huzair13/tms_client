package com.example.map_my_sona.manualComplaints.ManualHistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.manualComplaints.ManualComplaint_details;
import com.example.map_my_sona.manualComplaints.manual_complaints_history_adapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails_Networks_manual extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView_complaints_history_manual_network;
    DatabaseReference reference_complaints_history_manual_network;
    manual_complaints_history_adapter adapter_complaint_history_manual_network;
    ArrayList<ManualComplaint_details> arrayList_complaints_history_manual_network;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_networks_manual);

        recyclerView_complaints_history_manual_network=findViewById(R.id.recyclerview_complaints_history_manual_network);
        reference_complaints_history_manual_network= FirebaseDatabase.getInstance().getReference("Manual complaints").child("Network");

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
        toolbar= findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Complaints_HistoryDetails_Networks_manual.this, dashboard.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) adapterView.getChildAt(0)).setTextSize(20);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}