package com.example.tms_sona2.assets.tv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tms_sona2.R;
import com.example.tms_sona2.assets.AssetDeatils1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class asset_tv_recycle extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference reference;
    AssetTvAdapter adapter;
    ArrayList<AssetDeatils1> asset_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_tv_recycle);

        recyclerView=findViewById(R.id.assets_tv);
        reference= FirebaseDatabase.getInstance().getReference("Assets").child("Televisions");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        asset_details=new ArrayList<AssetDeatils1>();
        adapter = new AssetTvAdapter(asset_details,this);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    AssetDeatils1 user =dataSnapshot.getValue(AssetDeatils1.class);
                    asset_details.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(asset_tv_recycle.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}