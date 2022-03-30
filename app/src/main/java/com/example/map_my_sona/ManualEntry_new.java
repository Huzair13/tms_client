package com.example.map_my_sona;

import static android.R.layout.simple_spinner_dropdown_item;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ManualEntry_new extends AppCompatActivity {

    private Spinner manualdeptresposible ,manualbranch ,manuallocation,manualobject,manualcomdetails,manualcompriority;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_entry_new);

        manualdeptresposible = (Spinner) findViewById(R.id.manualdept_responsible);
        manualbranch = (Spinner) findViewById(R.id.manual_branch);
        manuallocation = (Spinner) findViewById(R.id.manual_location);
        manualobject = (Spinner) findViewById(R.id.manual_object);
        manualcomdetails = (Spinner) findViewById(R.id.manual_com_details);
        manualcompriority = (Spinner) findViewById(R.id.manual_com_priority);


        String[] manual_dept_resposible={"Dept Responsible ","electronics 💡","watersupply 🚿","Network 📶","wiring 🪢","painting 🎨","computer 🖥️","carpenting 🪚"};
        manualdeptresposible.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_dept_resposible));


        String[] manual_branch={"Branch ","UniversityBlock 🏢","ItBlock 🏫","MechanicalBlock 🏠","MBA 🏠","Office 🏢"};
        manualbranch.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_branch));

        String[] manual_location={"Location ","Floor1, room200","Floor2, room201","Floor3, room202","Floor4, room203","Floor5, room204"};
        manuallocation.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_location));


        String[] manual_object={"Object ","Light 💡","Fan 🍃","Computer 🖥️","Bench 🪑","Painting 🎨"};
        manualobject.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_object));

        String[] manual_comdetails={"Complaint Details ","Light Not working 💡","Network issue 💻","Fan not working 💸" ,"Bathroom Problem 🛁" ,"Furniture defects 🪑"};
        manualcomdetails.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_comdetails));


        String[] manual_compriority={"Level of Complaint ","Very High ⚠️","High 😥 ","Low 🙂"};
        manualcompriority.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_compriority));











    }
}