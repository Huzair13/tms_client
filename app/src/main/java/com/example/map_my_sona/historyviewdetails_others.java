package com.example.map_my_sona;

import static android.R.layout.simple_spinner_dropdown_item;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.Dep_wise_history;
import com.example.map_my_sona.complaints.viewDetails.historyviewdetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import papaya.in.sendmail.SendMail;

public class historyviewdetails_others extends AppCompatActivity {
    private DatabaseReference refemail;
    private DatabaseReference reference_complaints_history_fullView;

    private TextView pro_id,com_status_his;
    private String pro_id_str;
    private Button comp_close;
    private String status;
    private Spinner feedback;
    private String rating_str;
    Float rating_p;
    String rat;
    TextView rating_dep;
    RatingBar ratingBar;
    String uref_h;
    MaterialToolbar toolbar;

    Spinner feedBack_box;
    TextView feedBack_txtView;
    String FeedBack_str;
    TextView feedBack_txtView_head;

    String ReceiverEmail;

    private EditText other_feedback;

    AlertDialog.Builder builder;
    private DatabaseReference refDash;

    private TextView staff_name,staff_dep,com_id,staff_mob,powerRating,wexpiry,wperiod,ins_by,ins_date,mob,com_txt,location;

    private String location_str,uid_str,staff_name_str,staff_dep_str,com_id_str,staff_mob_str,powerRating_str,wexpiry_str,wperiod_str,ins_by_str,ins_date_str,mob_str,com_txt_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyviewdetails_others);

        builder=new AlertDialog.Builder(this);

        Intent intent=getIntent();
        String com_id_new=intent.getStringExtra("com_ID");

        refDash= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        staff_name=(TextView)findViewById(R.id.staff_name_unit_his_others);
        staff_dep=(TextView)findViewById(R.id.dep_unit_his_others);
        com_id=(TextView)findViewById(R.id.Comid_unit_his_others);
        staff_mob=(TextView)findViewById(R.id.staff_mob_history_com_others);
        powerRating=(TextView)findViewById(R.id.powerRating_unit_his_others);
        wexpiry=(TextView)findViewById(R.id.warranty_exp_unit_his_others);
        wperiod=(TextView)findViewById(R.id.warranty_unit_his_others);
        ins_by=(TextView)findViewById(R.id.ins_by_unit_his_others);
        ins_date=(TextView)findViewById(R.id.ins_date_unit_his_others);
        mob=(TextView)findViewById(R.id.mob_unit_his_others);
        com_txt=(TextView)findViewById(R.id.com_txt_history_others);
        location=(TextView)findViewById(R.id.location_unit_his_others);
        rating_dep=(TextView)findViewById(R.id.rating_dep_others);
        other_feedback=(EditText)findViewById(R.id.other_feedback_others);


        ratingBar=(RatingBar) findViewById(R.id.rating_others);

        refDash.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String posm=snapshot.child("position").getValue(String.class);
                if(snapshot.exists()){
                    if(posm.equals("admin")) {
                        rating_dep.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        uref_h= FirebaseAuth.getInstance().getUid();

        pro_id=(TextView) findViewById(R.id.Product_ID_history_others);
        com_status_his=(TextView)findViewById(R.id.complaint_status_his_others);

        comp_close=(Button)findViewById(R.id.close_the_com_his_others);

        feedBack_box=(Spinner)findViewById(R.id.com_his_feedBack_spinner_others);
        String[] FeedBack_dropdown={"FeedBack","Excellent","Very Good","Good","Bad","Worst","Others"};
        feedBack_box.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,FeedBack_dropdown));

        feedBack_txtView=(TextView)findViewById(R.id.com_txt_feedback_elec_txtView_others);
        feedBack_txtView_head=(TextView)findViewById(R.id.his_elec_feedBack_head_txt_others);

        reference_complaints_history_fullView= FirebaseDatabase.getInstance()
                .getReference("complaints").child("Others").child(com_id_new);

        reference_complaints_history_fullView.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complaint_details complaint_details=snapshot.getValue(Complaint_details.class);

                staff_name_str=complaint_details.getCom_by_name();
                staff_mob_str=complaint_details.getCom_by_mob();
                staff_dep_str=complaint_details.getCom_by_dep();
                com_id_str=complaint_details.getKey();
                powerRating_str=complaint_details.getPower_rating();
                wexpiry_str=complaint_details.getWexpiry();
                wperiod_str=complaint_details.getWperiod();
                ins_by_str=complaint_details.getIns_by();
                ins_date_str=complaint_details.getIns_date();
                mob_str=complaint_details.getMob();
                com_txt_str=complaint_details.getCom_txt();
                pro_id_str=complaint_details.getUniqueId();
                uid_str=complaint_details.getUID();
                location_str=complaint_details.getLocation();

                //get_rating
                rating_str=complaint_details.getRating();

                FeedBack_str=complaint_details.getFeedBack();

                status=complaint_details.getStatus();

                staff_name.setText(staff_name_str);
                staff_mob.setText(staff_mob_str);
                staff_dep.setText(staff_dep_str);
                com_id.setText(com_id_str);
                powerRating.setText(powerRating_str);
                wexpiry.setText(wexpiry_str);
                wperiod.setText(wperiod_str);
                ins_by.setText(ins_by_str);
                ins_date.setText(ins_date_str);
                mob.setText(mob_str);
                com_txt.setText(com_txt_str);
                location.setText(location_str);

                //rating_set
                rating_dep.setText(rating_str);
                ratingBar.setRating(Float.parseFloat(rating_str));

                feedBack_txtView.setText(FeedBack_str);

                if(status.equals("Completed")){
//                    ratingBar.setClickable(false);
//                    ratingBar.setFocusable(false);
                    ratingBar.setIsIndicator(true);
                    feedBack_box.setVisibility(View.GONE);
                    feedBack_txtView_head.setVisibility(View.VISIBLE);
                    feedBack_txtView.setVisibility(View.VISIBLE);

                }

                pro_id.setText(pro_id_str);
                com_status_his.setText(status);

                feedBack_box.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        FeedBack_str=feedBack_box.getSelectedItem().toString();
                        if(FeedBack_str.equals("Others")){
                            other_feedback.setVisibility(View.VISIBLE);
                        }
                        else{
                            other_feedback.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                if (status.equals("Pending")){
                    com_status_his.setBackgroundResource(R.color.Red);

                }else{
                    com_status_his.setBackgroundResource(R.color.green);
                }
                com_status_his.setTextColor(getResources().getColor(R.color.white));

//                toolbar= findViewById(R.id.topAppBar);
//                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(historyviewdetails.this, dashboard.class));
//                    }
//                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(historyviewdetails_others.this, "Something Went Wrong !!! ", Toast.LENGTH_SHORT).show();

            }
        });

        comp_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating_p=Float.valueOf(ratingBar.getRating());
                rat=rating_p.toString();
                ratingBar.setRating(rating_p);

                if(FeedBack_str.equals("Others") && !other_feedback.getText().toString().isEmpty()){
                    FeedBack_str=other_feedback.getText().toString();
                }

                if(FeedBack_str.equals("Others") && other_feedback.getText().toString().isEmpty()){
                    Toast.makeText(historyviewdetails_others.this,"Please specify your feedback",Toast.LENGTH_SHORT).show();
                    other_feedback.requestFocus();
                }
                else if (status.equals("Pending")){
                    HashMap hp1=new HashMap();
                    hp1.put("status","Completed");

                    HashMap hp=new HashMap();
                    hp.put("status","Completed");
                    hp.put("rating",rat);
                    hp.put("FeedBack",FeedBack_str);

                    refDash.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String pos=snapshot.child("position").getValue(String.class);
                            if(snapshot.exists()){
                                if(pos.equals("admin")){
                                    builder.setTitle("Alert")
                                            .setMessage("Are you sure to close the complaint ??")
                                            .setCancelable(true)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {


                                                    reference_complaints_history_fullView.updateChildren(hp1).addOnSuccessListener(new OnSuccessListener() {
                                                        @Override
                                                        public void onSuccess(Object o) {
                                                            Toast.makeText(historyviewdetails_others.this, "Complaint closed", Toast.LENGTH_SHORT).show();
                                                            if(ContextCompat.checkSelfPermission(historyviewdetails_others.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                                                                sendMessage();
                                                            }
                                                            else{
                                                                ActivityCompat.requestPermissions(historyviewdetails_others.this,
                                                                        new String[]{Manifest.permission.SEND_SMS},
                                                                        100);
                                                            }
                                                            Intent intent=new Intent(historyviewdetails_others.this, Dep_wise_history.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(historyviewdetails_others.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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

                                else if(uid_str.equals(uref_h)){
                                    builder.setTitle("Alert")
                                            .setMessage("Are you sure to close the complaint ??")
                                            .setCancelable(true)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    reference_complaints_history_fullView.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                                        @Override
                                                        public void onSuccess(Object o) {
                                                            Toast.makeText(historyviewdetails_others.this, "Complaint closed", Toast.LENGTH_SHORT).show();
                                                            Intent intent=new Intent(historyviewdetails_others.this, Dep_wise_history.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(historyviewdetails_others.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(historyviewdetails_others.this, "You are unable to close this complaint , as it is not filled by you.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    if(uid_str.equals(uref_h)){
                        HashMap hp=new HashMap();
                        hp.put("status","Pending");
                        hp.put("rating","0.0");

                        builder.setTitle("Alert")
                                .setMessage("Are you sure to open the complaint again ??")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        //getting technicians email id
                                        getReciveremailadd();
                                        reference_complaints_history_fullView.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {
                                                //Sending email to the technicians
                                                SendEmail();
                                                Toast.makeText(historyviewdetails_others.this, "Complaint opened Again", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(historyviewdetails_others.this, Dep_wise_history.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(historyviewdetails_others.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(historyviewdetails_others.this, "You are unable to close this complaint , as it is not filled by you.", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(historyviewdetails_others.this, "It has already been solved and closed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void SendEmail() {
        SendMail mail=new SendMail("mapmysona@gmail.com",
                "ywfcjyswheezxmde",
                ReceiverEmail,
                "Complaint Reopened",
                "Complaint which is closed by you has been reopened by the person " +
                        "who has filed the complaint\n"+"Please Recheck the complaint and give a solution as soon as possible"
        );
        mail.execute();
    }

    private void getReciveremailadd() {
        refemail=FirebaseDatabase.getInstance().getReference("Emails");
        refemail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReceiverEmail = snapshot.child("other").child("email").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage() {
        String sphone=staff_mob.getText().toString().trim();
        String sMessage="The complaint filled has been solved. \n check before closing the complaint \n If not, place it as pending";

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