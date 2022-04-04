package com.example.map_my_sona;

import static android.R.layout.simple_spinner_dropdown_item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.map_my_sona.complaints.complaint_Page;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManualEntry_new extends AppCompatActivity {

    private Spinner manualdeptresposible ,manualbranch ,manuallocation,manualobject,manualcomdetails,manualcompriority;
    private EditText manualIntercomNumber,manualPhNumber;
    private MaterialButton manualEntrySubmit;
    private String manualdeptresposible_str ,manualbranch_str ,manuallocation_str,manualobject_str,manualcomdetails_str,manualcompriority_str,manualIntercomNumber_str,manualPhNumber_str;

    DatabaseReference dbRef2;
    AlertDialog.Builder builder_mc;
  public   MaterialAlertDialogBuilder materialAlertDialogBuilder;

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

        manualIntercomNumber=(EditText)findViewById(R.id.manual_intercom_number);
        manualPhNumber=(EditText)findViewById(R.id.manual_phn_number);

        manualEntrySubmit=(MaterialButton) findViewById(R.id.manualentrysubmit);

        materialAlertDialogBuilder=new MaterialAlertDialogBuilder(this);

        String[] manual_dept_resposible={"Dept Responsible","electronics","watersupply","Network","wiring","painting","computer","carpenting"};
        manualdeptresposible.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_dept_resposible));

        String[] manual_branch={"Branch","UniversityBlock","ItBlock","MechanicalBlock","MBA","Office"};
        manualbranch.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_branch));

        String[] manual_location={"Location","Floor1, room200","Floor2, room201","Floor3, room202","Floor4, room203","Floor5, room204"};
        manuallocation.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_location));

        String[] manual_object={"Object","Light","Fan","Computer","Bench","Painting"};
        manualobject.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_object));

        String[] manual_comdetails={"Complaint Details","Light Not working","Network issue","Fan not working" ,"Bathroom Problem" ,"Furniture defects"};
        manualcomdetails.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_comdetails));

        String[] manual_compriority={"Level of Complaint","Very High ","High","Low"};
        manualcompriority.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,manual_compriority));

        manualdeptresposible.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manualdeptresposible_str=manualdeptresposible.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        manualbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manualbranch_str=manualbranch.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        manuallocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manuallocation_str=manuallocation.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        manualobject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manualobject_str=manualobject.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        manualcomdetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manualcomdetails_str=manualcomdetails.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        manualcompriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                manualcompriority_str=manualcompriority.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        manualEntrySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manualdeptresposible_str.equals("Dept Responsible")){
                    Toast.makeText(ManualEntry_new.this, "Please select the Department", Toast.LENGTH_SHORT).show();
                    manualdeptresposible.requestFocus();
                }else if(manualbranch_str.equals("Branch")){
                    Toast.makeText(ManualEntry_new.this, "Please select the Branch", Toast.LENGTH_SHORT).show();
                    manualbranch.requestFocus();
                }else if(manuallocation_str.equals("Location")){
                    Toast.makeText(ManualEntry_new.this, "Please select the Location", Toast.LENGTH_SHORT).show();
                    manuallocation.requestFocus();
                }else if(manualobject_str.equals("Object")){
                    Toast.makeText(ManualEntry_new.this, "Please select the Object", Toast.LENGTH_SHORT).show();
                    manualobject.requestFocus();
                }else if(manualcomdetails_str.equals("Complaint Details")){
                    Toast.makeText(ManualEntry_new.this, "Please select the Complaint", Toast.LENGTH_SHORT).show();
                    manualcomdetails.requestFocus();
                }else if(manualcompriority_str.equals("Level of Complaint")){
                    Toast.makeText(ManualEntry_new.this, "Please select the Priority", Toast.LENGTH_SHORT).show();
                    manualcompriority.requestFocus();
                }else if(manualIntercomNumber.getText().toString().isEmpty()){
                    manualIntercomNumber.setError("Enter the Intercom Number");
                    manualIntercomNumber.requestFocus();
                }else if(manualPhNumber.getText().toString().isEmpty()){
                    manualPhNumber.setError("Enter the Phone Number");
                    manualPhNumber.requestFocus();
                }
                else{
                    materialAlertDialogBuilder
                            .setTitle("Alert")
                            .setMessage("Are you sure to submit the complaint?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    submitManualComplaint();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }

            }
        });

    }

    private void submitManualComplaint() {
        dbRef2 = FirebaseDatabase.getInstance().getReference().child("Manual complaints").child(manualdeptresposible_str);
        final String uniqueKey = dbRef2.push().getKey();

        manualIntercomNumber_str=manualIntercomNumber.getText().toString();
        manualPhNumber_str=manualPhNumber.getText().toString();

        ManualComplaint_details manualComplaint_details=new ManualComplaint_details(manualdeptresposible_str ,manualbranch_str ,manuallocation_str,manualobject_str,manualcomdetails_str,manualcompriority_str,manualIntercomNumber_str,manualPhNumber_str);

        dbRef2.child(uniqueKey).setValue(manualComplaint_details).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ManualEntry_new.this, "Complaint Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManualEntry_new.this, dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ManualEntry_new.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}