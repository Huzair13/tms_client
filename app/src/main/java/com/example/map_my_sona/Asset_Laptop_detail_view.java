package com.example.map_my_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Asset_Laptop_detail_view extends AppCompatActivity {

    private DatabaseReference dbRef;

    String Amount_str,billNo_str,dop_str,quantity_str,suppAdd_str,Sup_name_str,model_str,ref_no_str;
    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_laptop_detail_view);

        tv1=findViewById(R.id.textView1231);
        tv2=findViewById(R.id.textView1232);
        tv3=findViewById(R.id.textView1233);

        Intent intent=getIntent();
        String lap_id_new=intent.getStringExtra("Lap_ID");

        dbRef= FirebaseDatabase.getInstance()
                .getReference("Assets").child("Laptops").child(lap_id_new);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AssetDeatils1 assetDeatils1=snapshot.getValue(AssetDeatils1.class);
                Amount_str=assetDeatils1.getAmount().toString();
                dop_str=assetDeatils1.getDOP().toString();
                suppAdd_str=assetDeatils1.getSupplier_Address().toString();

                tv1.setText(Amount_str);
                tv2.setText(suppAdd_str);
                tv3.setText(dop_str);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}