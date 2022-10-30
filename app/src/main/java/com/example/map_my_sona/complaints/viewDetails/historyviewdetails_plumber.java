package com.example.map_my_sona.complaints.viewDetails;

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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.map_my_sona.R;
import com.example.map_my_sona.complaints.Complaint_details;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Carpenter;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Electricity;
import com.example.map_my_sona.complaints.HistoryDetails.Complaints_HistoryDetails_Plumber;
import com.example.map_my_sona.dashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import papaya.in.sendmail.SendMail;

//import papaya.in.sendmail.SendMail;

public class historyviewdetails_plumber extends AppCompatActivity {

    private DatabaseReference reference_complaints_history_fullView;

    private TextView sn_snNum,sn_make,sn_model,sn_proc,sn_powerRat,sn_wperiod,sn_wexpiry,sn_ins_by,sn_ins_date,sn_dep_of_pro,sn_config,sn_loc,sn_name,sn_id,sn_mob;
    private int snNumber=4;
    private TableRow name_row,mob_row,com_IDrow,makeRow,modelRow,procRow,powerRatRow,wperiodrow,wexpiryrow,ins_byRow,ins_dateRow,dep_of_proRow,configRow,locationRow;
    private String make_str,model_str;

    private String ReceiverEmail;
    private DatabaseReference refemail;

    private TextView pro_id,com_status_his;
    private String pro_id_str;
    private Button comp_close;
    private String status;
    private Spinner feedback;
    Float rating_p;
    String rat;
    TextView rating_dep;
    private String rating_str;
    private String Date_str;
    RatingBar ratingBar;
    String uref_h;
    MaterialToolbar toolbar;

//    Spinner feedBack_box;
//    TextView feedBack_txtView;
//    String FeedBack_str;
//    TextView feedBack_txtView_head;

    private DatabaseReference refDash;

    //private EditText other_feedback;

    AlertDialog.Builder builder_plumber;

    private TextView config,make,model,location,staff_name,com_id,staff_mob,powerRating,wexpiry,wperiod,ins_by,ins_date,com_txt;

    private String location_Str,uid_str,staff_name_str,com_id_str,staff_mob_str,powerRating_str,wexpiry_str,wperiod_str,ins_by_str,ins_date_str,com_txt_str;
    private String config_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyviewdetails_plumber);

        builder_plumber = new AlertDialog.Builder(this);

        Intent intent = getIntent();
        String com_id_new = intent.getStringExtra("com_ID");

        refDash = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        staff_name = (TextView) findViewById(R.id.staff_name_unit_his_plumber);
        //staff_dep = (TextView) findViewById(R.id.dep_unit_his_plumber);
        com_id = (TextView) findViewById(R.id.Comid_unit_his_plumber);
        staff_mob = (TextView) findViewById(R.id.staff_mob_history_com_plumber);
        powerRating = (TextView) findViewById(R.id.powerRating_unit_his_plumber);
        wexpiry = (TextView) findViewById(R.id.warranty_exp_unit_his_plumber);
        wperiod = (TextView) findViewById(R.id.warranty_unit_his_plumber);
        ins_by = (TextView) findViewById(R.id.ins_by_unit_his_plumber);
        ins_date = (TextView) findViewById(R.id.ins_date_unit_his_plumber);
        //mob = (TextView) findViewById(R.id.mob_unit_his_plumber);
        com_txt = (TextView) findViewById(R.id.com_txt_history_plumber);
        location=(TextView)findViewById(R.id.location_unit_his_plumber);
        make=(TextView)findViewById(R.id.make_unit_plumh);
        model=(TextView)findViewById(R.id.model_unit_plumh);
        config=(TextView)findViewById(R.id.sn_config_plumh);


        com_IDrow=(TableRow)findViewById(R.id.com_id_plumh);
        name_row=(TableRow)findViewById(R.id.com_by_name_plumh);
        mob_row=(TableRow)findViewById(R.id.com_by_mob_plumh);
        makeRow=(TableRow) findViewById(R.id.makeRow_plumh);
        modelRow=(TableRow) findViewById(R.id.modelRow_plumh);
        powerRatRow=(TableRow) findViewById(R.id.powerRat_row_plumh);
        wperiodrow=(TableRow) findViewById(R.id.warrantyPeriodRow_plumh);
        wexpiryrow=(TableRow) findViewById(R.id.wexpiryRow_plumh);
        ins_byRow=(TableRow) findViewById(R.id.ins_by_Row_plumh);
        ins_dateRow=(TableRow) findViewById(R.id.ins_dateRow_plumh);
        locationRow=(TableRow) findViewById(R.id.LocRow_plumh);
        configRow=(TableRow) findViewById(R.id.configrow_plumh);


        sn_make=(TextView)findViewById(R.id.sn_make_plumh);
        sn_model=(TextView)findViewById(R.id.sn_model_plumh);
        sn_powerRat=(TextView)findViewById(R.id.sn_powerRat_plumh);
        sn_wperiod=(TextView)findViewById(R.id.sn_warperiod_plumh);
        sn_wexpiry=(TextView)findViewById(R.id.sn_wexpiry_plumh);
        sn_ins_by=(TextView)findViewById(R.id.sn_ins_by_plumh);
        sn_ins_date=(TextView)findViewById(R.id.sn_ins_date_plumh);
        sn_loc=(TextView)findViewById(R.id.sn_loc_plumh);
        sn_config= (TextView)findViewById(R.id.sn_config_plumh);
        sn_name=(TextView)findViewById(R.id.sn_name_plumh);
        sn_mob=(TextView)findViewById(R.id.sn_mob_plumh);
        sn_id=(TextView)findViewById(R.id.sn_id_plumh);

        //other_feedback=(EditText)findViewById(R.id.other_feedback_plumber);

        rating_dep=(TextView)findViewById(R.id.rating_dep_plum);

        ratingBar=(RatingBar) findViewById(R.id.rating_plum);

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

        pro_id = (TextView) findViewById(R.id.Product_ID_history_plumber);
        com_status_his = (TextView) findViewById(R.id.complaint_status_his_plumber);

        comp_close = (Button) findViewById(R.id.close_the_com_his_plumber);


//        feedBack_box=(Spinner)findViewById(R.id.com_his_feedBack_spinner_plum);
//        String[] FeedBack_dropdown={"FeedBack","Excellent","Very Good","Good","Bad","Worst","Others"};
//        feedBack_box.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,FeedBack_dropdown));
//
//        feedBack_txtView=(TextView)findViewById(R.id.com_txt_feedback_elec_txtView_plum);
//        feedBack_txtView_head=(TextView)findViewById(R.id.his_elec_feedBack_head_txt_plum);

//        feedback = (Spinner) findViewById(R.id.feedback);
//
//        String[] feebac = {"Feedback ", "Excellent", "Good", "Not bad", "Bad"};
//        feedback.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item, feebac));

        reference_complaints_history_fullView = FirebaseDatabase.getInstance().getReference("complaints").child("Pluming").child(com_id_new);

        reference_complaints_history_fullView.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complaint_details complaint_details = snapshot.getValue(Complaint_details.class);

                staff_name_str = complaint_details.getCom_by_name();
                staff_mob_str = complaint_details.getCom_by_mob();
                //staff_dep_str = complaint_details.getCom_by_dep();
                com_id_str = complaint_details.getKey();
                powerRating_str = complaint_details.getPower_rating();
                wexpiry_str = complaint_details.getWexpiry();
                wperiod_str = complaint_details.getWperiod();
                ins_by_str = complaint_details.getIns_by();
                ins_date_str = complaint_details.getIns_date();
                //mob_str = complaint_details.getMob();
                com_txt_str = complaint_details.getCom_txt();
                pro_id_str = complaint_details.getUniqueId();
                location_Str=complaint_details.getLocation();
                Date_str=complaint_details.getDate();
                make_str=complaint_details.getMake();
                model_str=complaint_details.getModel();
                config_str=complaint_details.getConfig();

                status = complaint_details.getStatus();

                //get_rating
                rating_str=complaint_details.getRating();
                //FeedBack_str=complaint_details.getFeedBack();

                staff_name.setText(staff_name_str);
                staff_mob.setText(staff_mob_str);
                //staff_dep.setText(staff_dep_str);
                com_id.setText(com_id_str);
//                powerRating.setText(powerRating_str);
//                wexpiry.setText(wexpiry_str);
//                wperiod.setText(wperiod_str);
//                ins_by.setText(ins_by_str);
//                ins_date.setText(ins_date_str);
//                //mob.setText(mob_str);
//                com_txt.setText(com_txt_str);
//                location.setText(location_Str);

                //MAKE
                if(!make_str.equals("NIL")){
                    make.setText(make_str);
                    sn_make.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    makeRow.setVisibility(View.GONE);
                }

                //MODEL
                if(!model_str.equals("NIL")){
                    model.setText(model_str);
                    sn_model.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    modelRow.setVisibility(View.GONE);
                }


                //POWER RATING
                if(!powerRating_str.equals("NIL")){
                    powerRating.setText(powerRating_str);
                    sn_powerRat.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    powerRatRow.setVisibility(View.GONE);
                }

                //WPERIOD
                if(!wperiod_str.equals("NIL")){
                    wperiod.setText(wperiod_str);
                    sn_wperiod.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    wperiodrow.setVisibility(View.GONE);
                }


                //WEXPIRY
                if(!wexpiry_str.equals("NIL")){
                    wexpiry.setText(wexpiry_str);
                    sn_wexpiry.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    wexpiryrow.setVisibility(View.GONE);
                }

                //INS_DATE
                if(!ins_date_str.equals("NIL")){
                    ins_date.setText(ins_date_str);
                    sn_ins_date.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    ins_dateRow.setVisibility(View.GONE);
                }

                //INS_BY
                if(!ins_by_str.equals("NIL")){
                    ins_by.setText(ins_by_str);
                    sn_ins_by.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    ins_byRow.setVisibility(View.GONE);
                }



                //LOCATION
                if(!location_Str.equals("NIL")){
                    location.setText(location_Str);
                    sn_loc.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    locationRow.setVisibility(View.GONE);
                }

                //DEP_OF_PRO
                if(!config_str.equals("NIL")){
                    config.setText(config_str);
                    sn_config.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    configRow.setVisibility(View.GONE);
                }

                pro_id.setText(pro_id_str);
                com_status_his.setText(status);

                //rating_set
                rating_dep.setText(rating_str);
                ratingBar.setRating(Float.parseFloat(rating_str));

                //feedBack_txtView.setText(FeedBack_str);

                if(status.equals("Completed")){
//                    ratingBar.setClickable(false);
//                    ratingBar.setFocusable(false);
                    ratingBar.setIsIndicator(true);
//                    feedBack_box.setVisibility(View.GONE);
//                    feedBack_txtView_head.setVisibility(View.VISIBLE);
//                    feedBack_txtView.setVisibility(View.VISIBLE);
                }

//                feedBack_box.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        FeedBack_str=feedBack_box.getSelectedItem().toString();
//                        if(FeedBack_str.equals("Others")){
//                            other_feedback.setVisibility(View.VISIBLE);
//                        }
//                        else{
//                            other_feedback.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });

                if (status.equals("Pending")) {
                    com_status_his.setBackgroundResource(R.color.Red);

                } else {
                    com_status_his.setBackgroundResource(R.color.green);
                }
                com_status_his.setTextColor(getResources().getColor(R.color.white));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(historyviewdetails_plumber.this, "Something Went Wrong !!! ", Toast.LENGTH_SHORT).show();

            }
        });

        comp_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating_p=Float.valueOf(ratingBar.getRating());
                rat=rating_p.toString();
                ratingBar.setRating(rating_p);

//                if(FeedBack_str.equals("Others") && !other_feedback.getText().toString().isEmpty()){
//                    FeedBack_str=other_feedback.getText().toString();
//                }
//
//                if(FeedBack_str.equals("Others") && other_feedback.getText().toString().isEmpty()){
//                    Toast.makeText(historyviewdetails_plumber.this,"Please specify your feedback",Toast.LENGTH_SHORT).show();
//                    other_feedback.requestFocus();
//                }

                if (status.equals("Pending")) {
                    final HashMap[] hpnew = {new HashMap()};

                    HashMap hp=new HashMap();
                    hp.put("status","Completed");

                    HashMap hp5=new HashMap();
                    hp5.put("status","Completed");
                    hp5.put("rating","5.0");

                    HashMap hp4=new HashMap();
                    hp4.put("status","Completed");
                    hp4.put("rating","4.0");

                    HashMap hp3=new HashMap();
                    hp3.put("status","Completed");
                    hp3.put("rating","3.0");

                    HashMap hp2=new HashMap();
                    hp2.put("status","Completed");
                    hp2.put("rating","2.0");

                    String pattern = "dd-MM-yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date1 = simpleDateFormat.format(new Date());

                    Long dateDifference = (Long) getDateDiff(new SimpleDateFormat("dd-MM-yyyy"), Date_str, date1);
                    //hp.put("FeedBack",FeedBack_str);

                    refDash.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String pos = snapshot.child("position").getValue(String.class);
                            if (snapshot.exists()) {
                                if (pos.equals("admin")) {
                                    builder_plumber.setTitle("Alert")
                                            .setMessage("Are you sure to close the complaint ??")
                                            .setCancelable(true)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    reference_complaints_history_fullView.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                                        @Override
                                                        public void onSuccess(Object o) {
                                                            Toast.makeText(historyviewdetails_plumber.this, "Complaint closed", Toast.LENGTH_SHORT).show();
                                                            if (ContextCompat.checkSelfPermission(historyviewdetails_plumber.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                                                                sendMessage();
                                                            } else {
                                                                ActivityCompat.requestPermissions(historyviewdetails_plumber.this,
                                                                        new String[]{Manifest.permission.SEND_SMS},
                                                                        100);
                                                            }
                                                            Intent intent = new Intent(historyviewdetails_plumber.this, Complaints_HistoryDetails_Plumber.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);


                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(historyviewdetails_plumber.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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

                                } else {
                                    builder_plumber.setTitle("Alert")
                                            .setMessage("Are you sure to close the complaint ??")
                                            .setCancelable(true)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    reference_complaints_history_fullView.updateChildren(hpnew[0]).addOnSuccessListener(new OnSuccessListener() {
                                                        @Override
                                                        public void onSuccess(Object o) {
                                                            Toast.makeText(historyviewdetails_plumber.this, "Complaint closed", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(historyviewdetails_plumber.this, Complaints_HistoryDetails_Plumber.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);


                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(historyviewdetails_plumber.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Toast.makeText(historyviewdetails_plumber.this, "It has already been solved and closed", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        toolbar= findViewById(R.id.topAppBar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(historyviewdetails_plumber.this, dashboard.class));
//            }
//        });
    }

    private void sendEmail() {
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
                ReceiverEmail = snapshot.child("Plumber").child("email").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage() {
        String sphone=staff_mob.getText().toString().trim();
        String sMessage="The complaint filled has been solved. \n" +
                " check before closing the complaint \n" +
                " If not, place it as pending";

        try{
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(sphone,null,sMessage,null,null);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show();
        }
    }

    private Object getDateDiff(SimpleDateFormat simpleDateFormat, String date_str, String date1) {
        try {
            return TimeUnit.DAYS.convert(simpleDateFormat.parse(date1).getTime() - simpleDateFormat.parse(date1).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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