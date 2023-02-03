package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ulist_Activity extends AppCompatActivity {

    private RecyclerView ulist;
    private List<UDetails_class> list1;
    private Ulist_Adapter adapter;
    MaterialToolbar toolbar;

    private DatabaseReference reference,dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulist);

        ulist=findViewById(R.id.recyclerview_ulist);

        reference= FirebaseDatabase.getInstance().getReference().child("userDetails");

        ulistv();

        toolbar= findViewById(R.id.topAppBar_Ulist);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ulist_Activity.this,dashboard.class));
            }
        });

    }

    private void ulistv() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String uname=dataSnapshot.getKey();
                    String pos=dataSnapshot.child("position").getValue(String.class);
                    String uID=dataSnapshot.child("userID").getValue(String.class);
                    UDetails_class uDetails_class=new UDetails_class(uname,pos,uID);

                    UDetails_class data = uDetails_class;
                    list1.add(data);

                }
                ulist.setHasFixedSize(true);
                ulist.setLayoutManager(new LinearLayoutManager(Ulist_Activity.this));
                adapter= new Ulist_Adapter(list1,Ulist_Activity.this);
                ulist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Ulist_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}