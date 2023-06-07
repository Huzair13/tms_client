package com.example.tms_sona2.assets.tablets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tms_sona2.R;
import com.example.tms_sona2.assets.AssetDeatils1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Asset_Tablet_detail_view extends AppCompatActivity {

    private DatabaseReference dbRef;

    String Amount_str,dop_str,suppAdd_str,Sup_name_str,model_str,ref_no_str,billNo_str,quantity_str;
    int billNo_int,quantity_int;
    TextView Amount,billNo,dop,quantity,suppAdd,Sup_name,model,ref_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_tablet_detail_view);

        Amount=findViewById(R.id.amount_asset_tab);
        billNo=findViewById(R.id.bill_asset_tab);
        dop=findViewById(R.id.dop_asset_tab);
        quantity=findViewById(R.id.quantity_asset_tab);
        suppAdd=findViewById(R.id.supp_add_assets_tab);
        Sup_name=findViewById(R.id.supp_name_asset_tab);
        model=findViewById(R.id.model_asset_tab);
        ref_no=findViewById(R.id.ref_asset_tab);

        Intent intent=getIntent();
        String mob_id_new=intent.getStringExtra("tab_ID");

        dbRef= FirebaseDatabase.getInstance()
                .getReference("Assets").child("Tablets").child(mob_id_new);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AssetDeatils1 assetDeatils1=snapshot.getValue(AssetDeatils1.class);
                Amount_str=assetDeatils1.getAmount().toString();

                billNo_int=assetDeatils1.getBill_No();
                billNo_str=Integer.toString(billNo_int);

                dop_str=assetDeatils1.getDOP().toString();

                quantity_int=assetDeatils1.getQuantity();
                quantity_str=Integer.toString(quantity_int);

                Sup_name_str=assetDeatils1.getSupplier_Name();
                suppAdd_str=assetDeatils1.getSupplier_Address();
                model_str=assetDeatils1.getModel();
                ref_no_str=assetDeatils1.getRef_no();

                Amount.setText(Amount_str);
                billNo.setText(billNo_str);
                billNo.setText(billNo_str);
                quantity.setText(quantity_str);
                Sup_name.setText(Sup_name_str);
                suppAdd.setText(suppAdd_str);
                model.setText(model_str);
                ref_no.setText(ref_no_str);
                dop.setText(dop_str);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}