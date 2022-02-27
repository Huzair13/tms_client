package com.example.map_my_sona.complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints_HistoryDetails extends AppCompatActivity {

    RecyclerView recyclerView_complaints_history;
    DatabaseReference reference_complaints_history;
    complaints_history_Adapter adapter_complaint_history;
    ArrayList<Complaint_details> arrayList_complaints_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);

        // calling the action bar
   //     ActionBar actionBar = getSupportActionBar();

        // Customize the back button
//        actionBar.setHomeAsUpIndicator(R.drawable.mybutton);

        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);
  //      getActionBar().setHomeButtonEnabled(true);

    //    getSupportActionBar().setTitle("HISTORY DETAILS");  // provide compatibility to all the versions

        recyclerView_complaints_history=findViewById(R.id.recyclerview_complaints_history);
        reference_complaints_history= FirebaseDatabase.getInstance().getReference("complaints");

        recyclerView_complaints_history.setHasFixedSize(true);
        recyclerView_complaints_history.setLayoutManager(new LinearLayoutManager(this));

        arrayList_complaints_history=new ArrayList<>();
        adapter_complaint_history = new complaints_history_Adapter(arrayList_complaints_history,this);
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
                Toast.makeText(Complaints_HistoryDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}