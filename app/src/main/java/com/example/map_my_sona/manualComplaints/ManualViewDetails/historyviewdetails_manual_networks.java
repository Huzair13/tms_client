package com.example.map_my_sona.manualComplaints.ManualViewDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.manualComplaints.ManualComplaint_details;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Electricity_manual;
import com.example.map_my_sona.manualComplaints.ManualHistoryDetails.Complaints_HistoryDetails_Networks_manual;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import papaya.in.sendmail.SendMail;

public class historyviewdetails_manual_networks extends AppCompatActivity {

    private DatabaseReference reference_complaints_history_fullView_manual;

    private TextView pro_id_manual,com_status_his_manual;
    private String pro_id_str_manual;
    private Button comp_close_manual;
    private TextView com_txt_manual;
    private String com_txt_manual_str;
    private String status_manual;
    private Spinner feedback_manual;
    String uref_m;

    AlertDialog.Builder builder_manual;
    private DatabaseReference refDash;

    private TextView com_id_manual,staffName_manual,Dep_Res_manual,Branch_manual,loc_manual,object_manual,
            com_det_manual,level_of_com_manual,intercomNum_manual,phone_num_manual;

    private String  uid_manual_str,com_id_manual_str,staffName_manual_str,Dep_Res_manual_str,Branch_manual_str,loc_manual_str,object_manual_str,
            com_det_manual_str,level_of_com_manual_str,intercomNum_manual_str,phone_num_manual_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyviewdetails_manual_networks);

        builder_manual=new AlertDialog.Builder(this);

        Intent intent=getIntent();
        String com_id_new=intent.getStringExtra("com_IDM");

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        com_id_manual=(TextView) findViewById(R.id.Comid_unit_his_manual_networks);
        staffName_manual=(TextView) findViewById(R.id.staff_name_unit_his_manual_networks);
        Dep_Res_manual=(TextView) findViewById(R.id.dep_unit_his_manual_networks);
        Branch_manual=(TextView) findViewById(R.id.branch_history_com_manual_networks);
        loc_manual=(TextView) findViewById(R.id.loc_unit_his_manual_networks);
        object_manual=(TextView) findViewById(R.id.object_unit_his_manual_networks);
        com_det_manual=(TextView) findViewById(R.id.com_det_unit_his_manual_networks);
        level_of_com_manual=(TextView) findViewById(R.id.level_of_com_unit_his_manual_networks);
        intercomNum_manual=(TextView) findViewById(R.id.intercom_num_unit_his_manual_networks);
        phone_num_manual=(TextView) findViewById(R.id.phone_unit_his_manual_networks);

        uref_m= FirebaseAuth.getInstance().getUid();

        //pro_id_manual=(TextView) findViewById(R.id.Product_ID_history_manual_networks);
        com_status_his_manual=(TextView) findViewById(R.id.complaint_status_his_manual_networks);

        comp_close_manual=(Button)findViewById(R.id.close_the_com_his_manual_networks);

        com_txt_manual=(TextView) findViewById(R.id.com_txt_history_manual_networks);

        reference_complaints_history_fullView_manual= FirebaseDatabase.getInstance()
                .getReference("Manual complaints").child("Network").child(com_id_new);
        reference_complaints_history_fullView_manual.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ManualComplaint_details manualComplaint_details=snapshot.getValue(ManualComplaint_details.class);

                com_id_manual_str=manualComplaint_details.getKey();
                staffName_manual_str=manualComplaint_details.getCom_by_Name();
                Dep_Res_manual_str=manualComplaint_details.getDep_res();
                Branch_manual_str=manualComplaint_details.getBranch();
                loc_manual_str=manualComplaint_details.getLocation();
                object_manual_str=manualComplaint_details.getObject();
                level_of_com_manual_str=manualComplaint_details.getPriority();
                intercomNum_manual_str=manualComplaint_details.getIntercomNum();
                phone_num_manual_str=manualComplaint_details.getPhoneNum();
                uid_manual_str=manualComplaint_details.getUID();
                com_txt_manual_str=manualComplaint_details.getCom_details();

                status_manual=manualComplaint_details.getStatus();

                com_id_manual.setText(com_id_manual_str);
                staffName_manual.setText(staffName_manual_str);
                Dep_Res_manual.setText(Dep_Res_manual_str);
                Branch_manual.setText(Branch_manual_str);
                loc_manual.setText(loc_manual_str);
                object_manual.setText(object_manual_str);
                level_of_com_manual.setText(level_of_com_manual_str);
                intercomNum_manual.setText(intercomNum_manual_str);
                phone_num_manual.setText(phone_num_manual_str);
                com_det_manual.setText(com_txt_manual_str);
                com_txt_manual.setText(com_txt_manual_str);
                com_status_his_manual.setText(status_manual);

                if (status_manual.equals("Pending")){
                    com_status_his_manual.setBackgroundResource(R.color.Red);

                }else{
                    com_status_his_manual.setBackgroundResource(R.color.green);
                }
                com_status_his_manual.setTextColor(getResources().getColor(R.color.white));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(historyviewdetails_manual_networks.this, "Something Went Wrong !!! ", Toast.LENGTH_SHORT).show();
            }
        });

        comp_close_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status_manual.equals("Pending")){
                    HashMap hp=new HashMap();
                    hp.put("status","Completed");

                    refDash.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String pos=snapshot.child("position").getValue(String.class);
                            if(snapshot.exists()){
                                if(pos.equals("admin")){

                                    builder_manual.setTitle("Alert")
                                            .setMessage("Are you sure to close the complaint ??")
                                            .setCancelable(true)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {


                                                    reference_complaints_history_fullView_manual.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                                        @Override
                                                        public void onSuccess(Object o) {
                                                            Toast.makeText(historyviewdetails_manual_networks.this, "Complaint closed", Toast.LENGTH_SHORT).show();
                                                            if(ContextCompat.checkSelfPermission(historyviewdetails_manual_networks.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                                                                sendMessage();
                                                            }
                                                            else{
                                                                ActivityCompat.requestPermissions(historyviewdetails_manual_networks.this,
                                                                        new String[]{Manifest.permission.SEND_SMS},
                                                                        100);
                                                            }
                                                            Intent intent=new Intent(historyviewdetails_manual_networks.this, Complaints_HistoryDetails_Networks_manual.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(historyviewdetails_manual_networks.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

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

                                else if(uid_manual_str.equals(uref_m)){
                                    builder_manual.setTitle("Alert")
                                            .setMessage("Are you sure to close the complaint ??")
                                            .setCancelable(true)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    reference_complaints_history_fullView_manual.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                                        @Override
                                                        public void onSuccess(Object o) {
                                                            Toast.makeText(historyviewdetails_manual_networks.this, "Complaint closed", Toast.LENGTH_SHORT).show();
                                                            Intent intent=new Intent(historyviewdetails_manual_networks.this, Complaints_HistoryDetails_Networks_manual.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(historyviewdetails_manual_networks.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            })
                                            .show();
                                }else{
                                    Toast.makeText(historyviewdetails_manual_networks.this, "Complaint is not made by you so cant close it", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    if(uid_manual_str.equals(uref_m)){
                        HashMap hp=new HashMap();
                        hp.put("status","Pending");

                        builder_manual.setTitle("Alert")
                                .setMessage("Are you sure to open the complaint again ??")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        reference_complaints_history_fullView_manual.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {

                                                SendMail mail=new SendMail("mapmysona@gmail.com",
                                                        "mms@2022",
                                                        "ahamedhuzair13@gmail.com",
                                                        "Complaint Reopened",
                                                        "Complaint which is closed by you has been reopened by the person " +
                                                                "who has filed the complaint\n"+"Please Recheck the complaint and give a solution as soon as possible"
                                                );
                                                mail.execute();

                                                Toast.makeText(historyviewdetails_manual_networks.this, "Complaint opened Again", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(historyviewdetails_manual_networks.this, Complaints_HistoryDetails_Networks_manual.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(historyviewdetails_manual_networks.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                                .show();

                    }else{
                        Toast.makeText(historyviewdetails_manual_networks.this, "This complaint is not made by you so you cant close it", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(historyviewdetails_manual_networks.this, "This complaint is already solved and closed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void sendMessage() {
        String sphone=phone_num_manual.getText().toString().trim();
        String sMessage="Complaint Registered by you in MAP MY SONA has been solved Please check \n If not solved you can make the complaint again pending";

        if(!sphone.equals("") && !sMessage.equals("")){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(sphone,null,sMessage,null,null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }
    }
}