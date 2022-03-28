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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Complaints_HistoryDetails_Electricity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    RecyclerView recyclerView_complaints_history;
    DatabaseReference reference_complaints_history;
    complaints_history_Adapter_Electricity adapter_complaint_history;
    ArrayList<Complaint_details> arrayList_complaints_history;

    //fliter
    TextInputLayout hisfliter;
    AutoCompleteTextView hisflitertext;
    Spinner spin;
//    TextView  TextView;



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        String message =getIntent().getStringExtra("message");

        //filter
//        hisfliter=findViewById(R.id.historyfliter);
//        hisflitertext=findViewById(R.id.historyflitertext);


//        String[] fliter={"All","Past 10 days","Last Month"};
//        ArrayAdapter<String> fliterAdapter=new ArrayAdapter<>(Complaints_HistoryDetails_Electricity.this,R.layout.dropdownfliter,fliter);
//        hisflitertext.setAdapter(fliterAdapter);

            // Spinner element
            Spinner spinner = (Spinner) findViewById(R.id.historyfliter);

            // Spinner click listener
            spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("All");
            categories.add("Past 10 Days ");
            categories.add("Last Month");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);

        recyclerView_complaints_history=findViewById(R.id.recyclerview_complaints_history);
        reference_complaints_history= FirebaseDatabase.getInstance().getReference("complaints").child("Electricity");

        recyclerView_complaints_history.setHasFixedSize(true);
        recyclerView_complaints_history.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history=new ArrayList<>();
        adapter_complaint_history = new complaints_history_Adapter_Electricity(arrayList_complaints_history,this);
        recyclerView_complaints_history.setAdapter(adapter_complaint_history);

        reference_complaints_history.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Complaint_details user =dataSnapshot.getValue(Complaint_details.class);
                    arrayList_complaints_history.add(user);
                }
                adapter_complaint_history.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Complaints_HistoryDetails_Electricity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(20);

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}