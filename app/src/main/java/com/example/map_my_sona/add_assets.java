package com.example.map_my_sona;

import static android.R.layout.simple_spinner_dropdown_item;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class add_assets extends AppCompatActivity {

    Spinner asset_type;
    EditText ref_no,model,quantity,supp_name,supp_add,bill_no,dop,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assets);

        asset_type=findViewById(R.id.asset_type);
        ref_no=(EditText)findViewById(R.id.ref_no);
        model=(EditText) findViewById(R.id.model);
        quantity=(EditText)findViewById(R.id.quantity);
        supp_name=(EditText)findViewById(R.id.supplier_name);
        supp_add=(EditText)findViewById(R.id.supplier_address);
        bill_no=(EditText)findViewById(R.id.bill_no);
        dop=(EditText)findViewById(R.id.date_of_purchase);
        amount=(EditText)findViewById(R.id.amount);

        String[] asset_type_list={"Device","Laptop","Mobile","Tablet"};
        asset_type.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,asset_type_list));

    }
}