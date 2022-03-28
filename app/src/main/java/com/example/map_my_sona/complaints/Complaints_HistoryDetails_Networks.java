package com.example.map_my_sona.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.complaints.Electricity.complaints_history_Adapter_Electricity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Complaints_HistoryDetails_Networks extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerView_complaints_history_networks;
    DatabaseReference reference_complaints_history_netwroks;
    complaints_history_Adapter_Networks adapter_complaint_history_networks;
    ArrayList<Complaint_details> arrayList_complaints_history_networks;

    TextInputLayout hisfliter_networks;
    AutoCompleteTextView hisflitertext_networks;
    Spinner spin_networks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_history_details_networks);
        String message =getIntent().getStringExtra("message");

        Spinner spinner = (Spinner) findViewById(R.id.historyfliter);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("All");
        categories.add("Past 10 Days ");
        categories.add("Last Month");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        recyclerView_complaints_history_networks=findViewById(R.id.recyclerview_complaints_history);
        reference_complaints_history_netwroks= FirebaseDatabase.getInstance().getReference("complaints").child("Networks");

        recyclerView_complaints_history_networks.setHasFixedSize(true);
        recyclerView_complaints_history_networks.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history_networks=new ArrayList<>();
        adapter_complaint_history_networks = new complaints_history_Adapter_Networks(arrayList_complaints_history_networks,this);
        recyclerView_complaints_history_networks.setAdapter(adapter_complaint_history_networks);

        reference_complaints_history_netwroks.addValueEventListener(new ValueEventListener() {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(20);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}