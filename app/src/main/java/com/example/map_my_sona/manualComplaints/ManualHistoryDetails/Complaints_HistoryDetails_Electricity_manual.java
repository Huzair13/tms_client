package com.example.map_my_sona.manualComplaints.ManualHistoryDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.manualComplaints.ManualComplaint_details;
import com.example.map_my_sona.manualComplaints.manual_complaints_history_adapter;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails_Electricity_manual extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history_manual;
    DatabaseReference reference_complaints_history_manual;
    manual_complaints_history_adapter adapter_complaint_history_manual;
    ArrayList<ManualComplaint_details> arrayList_complaints_history_manual;

    TextInputLayout hisfliter_manual;
    AutoCompleteTextView hisflitertext_manual;
    Spinner spin_manual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_electricity_manual);

        recyclerView_complaints_history_manual=findViewById(R.id.recyclerview_complaints_history_manual);
        reference_complaints_history_manual= FirebaseDatabase.getInstance().getReference("Manual complaints").child("electronics");

        recyclerView_complaints_history_manual.setHasFixedSize(true);
        recyclerView_complaints_history_manual.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history_manual=new ArrayList<>();
        adapter_complaint_history_manual = new manual_complaints_history_adapter(arrayList_complaints_history_manual,this);
        recyclerView_complaints_history_manual.setAdapter(adapter_complaint_history_manual);

        reference_complaints_history_manual.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ManualComplaint_details user =dataSnapshot.getValue(ManualComplaint_details.class);
                    arrayList_complaints_history_manual.add(user);
                }
                adapter_complaint_history_manual.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Electricity_manual.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}