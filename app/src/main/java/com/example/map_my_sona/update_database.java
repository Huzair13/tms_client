package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class update_database extends AppCompatActivity {

    private EditText sn,make,model,procurement,powerRating,wexpiry,wperiod,ins_by,ins_date,mob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_database);

        sn=(EditText) findViewById(R.id.sn_unit_update_ad);
        make=(EditText) findViewById(R.id.make_unit_update_ad);
        model=(EditText) findViewById(R.id.model_unit_update_ad);
        procurement=(EditText) findViewById(R.id.procurement_unit_update_ad);
        powerRating=(EditText) findViewById(R.id.powerRating_unit_update_ad);
        wexpiry=(EditText) findViewById(R.id.warranty_exp_unit_update_ad);
        wperiod=(EditText) findViewById(R.id.warranty_unit_update_ad);
        ins_by=(EditText) findViewById(R.id.ins_by_unit_update_ad);
        ins_date=(EditText) findViewById(R.id.ins_date_unit_update_ad);
        mob=(EditText) findViewById(R.id.mob_unit_update_ad);



    }
}