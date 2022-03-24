package com.example.map_my_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class manualentry extends AppCompatActivity {

    TextInputLayout deptres;
    AutoCompleteTextView deptrestext;

    //
    TextInputLayout branch;
    AutoCompleteTextView branchtext;

    //
    TextInputLayout location;
    AutoCompleteTextView locationtext;

    //
    TextInputLayout object;
    AutoCompleteTextView objecttext;
    ///
    TextInputLayout complaintmanualcontent;
    AutoCompleteTextView complaintmanualcontenttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualentry);

        deptres=findViewById(R.id.deptresponsible);
        deptrestext=findViewById(R.id.deptresponsibletext);

        //
        branch=findViewById(R.id.branch);
        branchtext =findViewById(R.id.branchtext);

        //
        location =findViewById(R.id.location);
        locationtext=findViewById(R.id.locationtext);

        //
        object=findViewById(R.id.object);
        objecttext=findViewById(R.id.objecttext);

        complaintmanualcontent=findViewById(R.id.complaintmanualcontent);
        complaintmanualcontenttext =findViewById(R.id.complaintmanualcontenttext);


        String[] dept={"electronics 💡","watersupply 🚿","Network 📶","wiring 🪢","painting 🎨","computer 🖥️","carpenting 🪚"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdowndept,dept);
        deptrestext.setAdapter(itemAdapter);

        String[] branch={"UniversityBlock 🏢","ItBlock 🏫","MechanicalBlock 🏠","MBA 🏠","Office 🏢"};
        ArrayAdapter<String> branchAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownbranch,branch);
        branchtext.setAdapter(branchAdapter);

        String[] location={"Floor1, room200","Floor2, room201","Floor3, room202","Floor4, room203","Floor5, room204"};
        ArrayAdapter<String> locationAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownlocation,location);
        locationtext.setAdapter(locationAdapter);

        String[] object={"Light 💡","Fan 🍃","Computer 🖥️","Bench 🪑","Painting 🎨"};
        ArrayAdapter<String> objectAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownobject,object);
        objecttext.setAdapter(objectAdapter);


        String[] complaint={"Light Not working","Network issue","Fan not working" ,"Bathroom Problem" ,"Furniture defects"};
        ArrayAdapter<String> complaintAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdowncomplaintcontent,complaint);
        complaintmanualcontenttext.setAdapter(complaintAdapter);


    }
}