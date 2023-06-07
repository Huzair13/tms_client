package com.example.tms_sona2;

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

    //
    TextInputLayout compriority;
    AutoCompleteTextView comprioritytext;

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

        compriority=findViewById(R.id.compriority);
        comprioritytext =findViewById(R.id.comprioritytext);

        String[] dept={"electronics 💡","watersupply 🚿","Network 📶","wiring 🪢","painting 🎨","computer 🖥️","carpenting 🪚"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdowndept,dept);
        deptrestext.setAdapter(itemAdapter);

        String[] branch={"UniversityBlock 🏢","ItBlock 🏫","MechanicalBlock 🏠","MBA 🏠","Office 🏢"};
        ArrayAdapter<String> branchAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownbranch,branch);
        branchtext.setAdapter(branchAdapter);

        String[] location={"PrincipalRoom","Principal PA Room","Principal Conference Hall","Principal Rest Room","Director Room","Waiting Room" , "Inspection Room", "Finance Manager"};
        ArrayAdapter<String> locationAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownlocation,location);
        locationtext.setAdapter(locationAdapter);

        String[] object={"Light 💡","Fan 🍃","Computer 🖥️","Bench 🪑","Painting 🎨"};
        ArrayAdapter<String> objectAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownobject,object);
        objecttext.setAdapter(objectAdapter);


        String[] complaint={"Light Not working 💡","Network issue 💻","Fan not working 💸" ,"Bathroom Problem 🛁" ,"Furniture defects 🪑"};
        ArrayAdapter<String> complaintAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdowncomplaintcontent,complaint);
        complaintmanualcontenttext.setAdapter(complaintAdapter);


        String[] priority={"Very High ⚠","High 😥 ","Low 🙂"};
        ArrayAdapter<String> priorityAdapter=new ArrayAdapter<>(manualentry.this,R.layout.dropdownpriority,priority);
        comprioritytext.setAdapter(priorityAdapter);



    }
}