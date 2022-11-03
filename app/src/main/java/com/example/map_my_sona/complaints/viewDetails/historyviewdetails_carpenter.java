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
import android.os.StrictMode;
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

import org.apache.poi.ss.formula.functions.T;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import papaya.in.sendmail.SendMail;

//import papaya.in.sendmail.SendMail;

public class historyviewdetails_carpenter extends AppCompatActivity {

    private static long difference_In_Days,difference_In_Hours,difference_In_Minutes,difference_In_Seconds,difference_In_Time;
    private static long difference_In_Years;

    private DatabaseReference reference_complaints_history_fullView;

    private String ReceiverEmail;
    private DatabaseReference refemail;

    private TextView pro_id,com_status_his,make,model;

    private TextView sn_snNum,sn_make,sn_model,sn_proc,sn_powerRat,sn_wperiod,sn_wexpiry,sn_ins_by,sn_ins_date,sn_dep_of_pro,sn_config,sn_loc,sn_name,sn_id,sn_mob;
    private int snNumber=4;

    private String pro_id_str;
    private Button comp_close;
    private String status;
    private String rating_str;
    Float rating_p;
    String rat;
    TextView rating_dep;
    RatingBar ratingBar;
    private Spinner feedback;
    private DatabaseReference refDash;
    String uref_h;
    MaterialToolbar toolbar;
//    private EditText other_feedback;
//
//    Spinner feedBack_box;
//    TextView feedBack_txtView;
//    String FeedBack_str;
//    TextView feedBack_txtView_head;

    AlertDialog.Builder builder_carpenter;

    private TextView staff_name,com_id,staff_mob,powerRating,wexpiry,wperiod,ins_by,ins_date,com_txt,location,config;

    private TableRow name_row,mob_row,com_IDrow,makeRow,modelRow,procRow,powerRatRow,wperiodrow,wexpiryrow,ins_byRow,ins_dateRow,dep_of_proRow,configRow,locationRow;

    private String config_str,location_Str,uid_str,staff_name_str,com_id_str,staff_mob_str,powerRating_str,wexpiry_str,wperiod_str,ins_by_str,ins_date_str,com_txt_str;

    private String make_str,model_str;

    private String Date_str;
    private String time_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyviewdetails_carpenter);

        builder_carpenter=new AlertDialog.Builder(this);

        Intent intent=getIntent();
        String com_id_new=intent.getStringExtra("com_ID");

        refDash=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());

        staff_name=(TextView)findViewById(R.id.staff_name_unit_his_carpenter);
        //staff_dep=(TextView)findViewById(R.id.dep_unit_his_carpenter);
        com_id=(TextView)findViewById(R.id.Comid_unit_his_carpenter);
        staff_mob=(TextView)findViewById(R.id.staff_mob_history_com_carpenter);
        powerRating=(TextView)findViewById(R.id.powerRating_unit_his_carpenter);
        wexpiry=(TextView)findViewById(R.id.warranty_exp_unit_his_carpenter);
        wperiod=(TextView)findViewById(R.id.warranty_unit_his_carpenter);
        ins_by=(TextView)findViewById(R.id.ins_by_unit_his_carpenter);
        ins_date=(TextView)findViewById(R.id.ins_date_unit_his_carpenter);
        //mob=(TextView)findViewById(R.id.mob_unit_his_carpenter);
        com_txt=(TextView)findViewById(R.id.com_txt_history_carpenter);
        location=(TextView)findViewById(R.id.location_unit_his_carpenter);
        make=(TextView)findViewById(R.id.make_unit_elech);
        model=(TextView)findViewById(R.id.model_unit_elech);
        config=(TextView)findViewById(R.id.Config_elech);

        com_IDrow=(TableRow)findViewById(R.id.comID_Row);
        name_row=(TableRow)findViewById(R.id.ComBY_nameRow);
        mob_row=(TableRow)findViewById(R.id.com_byMOb_row);
        makeRow=(TableRow) findViewById(R.id.makeRow_elech);
        modelRow=(TableRow) findViewById(R.id.modelRow_elech);
        powerRatRow=(TableRow) findViewById(R.id.powerRat_row_elech);
        wperiodrow=(TableRow) findViewById(R.id.warrantyPeriodRow_elech);
        wexpiryrow=(TableRow) findViewById(R.id.wexpiryRow_elech);
        ins_byRow=(TableRow) findViewById(R.id.ins_by_Row_elech);
        ins_dateRow=(TableRow) findViewById(R.id.ins_dateRow_elech);
        locationRow=(TableRow) findViewById(R.id.LocRow_elech);
        configRow=(TableRow) findViewById(R.id.configrow_elech);


        sn_make=(TextView)findViewById(R.id.sn_make_elech);
        sn_model=(TextView)findViewById(R.id.sn_model_elech);
        sn_powerRat=(TextView)findViewById(R.id.sn_powerRat_elech);
        sn_wperiod=(TextView)findViewById(R.id.sn_warperiod_elech);
        sn_wexpiry=(TextView)findViewById(R.id.sn_wexpiry_elech);
        sn_ins_by=(TextView)findViewById(R.id.sn_ins_by_elech);
        sn_ins_date=(TextView)findViewById(R.id.sn_ins_date_elech);
        sn_loc=(TextView)findViewById(R.id.sn_loc_elech);
        sn_config= (TextView)findViewById(R.id.sn_config_elech);
        sn_name=(TextView)findViewById(R.id.sn_name_elech);
        sn_mob=(TextView)findViewById(R.id.sn_mob_elech);
        sn_id=(TextView)findViewById(R.id.sn_id_elech);

        //other_feedback=(EditText)findViewById(R.id.other_feedback_carpenter );

        rating_dep=(TextView)findViewById(R.id.rating_dep_car);

        ratingBar=(RatingBar) findViewById(R.id.rating_car);

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


        pro_id=(TextView) findViewById(R.id.Product_ID_history_carpenter);
        com_status_his=(TextView)findViewById(R.id.complaint_status_his_carpenter);

        comp_close=(Button)findViewById(R.id.close_the_com_his_carpenter);

//        feedBack_box=(Spinner)findViewById(R.id.com_his_feedBack_spinner_car);
//        String[] FeedBack_dropdown={"FeedBack","Excellent","Very Good","Good","Bad","Worst","Others"};
//        feedBack_box.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,FeedBack_dropdown));
//
//        feedBack_txtView=(TextView)findViewById(R.id.com_txt_feedback_elec_txtView_car);
//        feedBack_txtView_head=(TextView)findViewById(R.id.his_elec_feedBack_head_txt_car);

//        feedback =(Spinner)findViewById(R.id.feedback);
//
//
//        String[] feebac={"Feedback ","Excellent","Good","Not bad" ,"Bad"};
//        feedback.setAdapter(new ArrayAdapter<String>(this, simple_spinner_dropdown_item,feebac));

        reference_complaints_history_fullView= FirebaseDatabase.getInstance().getReference("complaints").child("Carpenter").child(com_id_new);

        reference_complaints_history_fullView.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Complaint_details complaint_details=snapshot.getValue(Complaint_details.class);

                staff_name_str=complaint_details.getCom_by_name();
                staff_mob_str=complaint_details.getCom_by_mob();
                //staff_dep_str=complaint_details.getCom_by_dep();
                com_id_str=complaint_details.getKey();
                powerRating_str=complaint_details.getPower_rating();
                wexpiry_str=complaint_details.getWexpiry();
                wperiod_str=complaint_details.getWperiod();
                ins_by_str=complaint_details.getIns_by();
                ins_date_str=complaint_details.getIns_date();
                //mob_str=complaint_details.getMob();
                com_txt_str=complaint_details.getCom_txt();
                pro_id_str=complaint_details.getUniqueId();
                location_Str=complaint_details.getLocation();
                Date_str=complaint_details.getDate();
                make_str=complaint_details.getMake();
                model_str=complaint_details.getModel();
                config_str=complaint_details.getConfig();
                time_str=complaint_details.getTime();

                //uid_str=complaint_details.getUID();
                uref_h= FirebaseAuth.getInstance().getUid();

                //get_rating
                rating_str=complaint_details.getRating();
                //FeedBack_str=complaint_details.getFeedBack();

                status=complaint_details.getStatus();


                staff_name.setText(staff_name_str);
                staff_mob.setText(staff_mob_str);
//                //staff_dep.setText(staff_dep_str);
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

                //CONFIG
                if(!config_str.equals("NIL")){
                    config.setText(config_str);
                    sn_config.setText(String.valueOf(snNumber));
                    snNumber++;
                }
                else{
                    configRow.setVisibility(View.GONE);
                }


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

                pro_id.setText(pro_id_str);
                com_status_his.setText(status);

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

                if (status.equals("Pending")){
                    com_status_his.setBackgroundResource(R.color.Red);

                }else{
                    com_status_his.setBackgroundResource(R.color.green);
                }
                com_status_his.setTextColor(getResources().getColor(R.color.white));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(historyviewdetails_carpenter.this, "Something Went Wrong !!! ", Toast.LENGTH_SHORT).show();

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
//                    Toast.makeText(historyviewdetails_carpenter.this,"Please specify your feedback",Toast.LENGTH_SHORT).show();
//                    other_feedback.requestFocus();
//                }

                if (status.equals("Pending")){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime now = LocalDateTime.now();
                    String presntdANDt= dtf.format(now);

                    //Long dateDifference = (Long) getDateDiff(new SimpleDateFormat("dd-MM-yyyy"), Date_str, date1);

                    Boolean bool = getBool();

                    findDifference(Date_str+" "+time_str,presntdANDt);

                    if (bool) {
                        if(difference_In_Days==0){
                            if(difference_In_Hours<=3){
                                hashm(5);
                            }
                            else if(difference_In_Hours>3 && difference_In_Hours<=6){
                                hashm(4);
                            }
                            else if(difference_In_Hours>6 && difference_In_Hours<=9){
                                hashm(3);
                            }else{
                                hashm(2);
                            }
                        }else if (difference_In_Days==1){
                            hashm(1);
                        }else{
                            hashm(0);
                        }
                    }
                    else{
                        if(difference_In_Days==0){
                            if(difference_In_Hours<=15){
                                hashm(5);
                            }
                            else if(difference_In_Hours>15 && difference_In_Hours<=18){
                                hashm(4);
                            }
                            else if(difference_In_Hours>18 && difference_In_Hours<=21){
                                hashm(3);
                            }
                            else{
                                hashm(2);
                            }
                        }
                        else if(difference_In_Days==1){
                            hashm(1);
                        }else{
                            hashm(0);
                        }
                    }

                }
                else{
                    Toast.makeText(historyviewdetails_carpenter.this, "It has already been solved and closed", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        toolbar= findViewById(R.id.topAppBar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(historyviewdetails_carpenter.this, dashboard.class));
//            }
//        });
    }

    private void sendEmail() {
        SendMail mail=new SendMail("mapmysona@gmail.com",
                "ywfcjyswheezxmde",
                "ahamedhuzair13@gmail.com",
                "Complaint Reopened",
                "Complaint which is closed by you has been reopened by the person " +
                        "who has filed the complaint\n"+"Please Recheck the complaint and give a solution as soon as possible"
        );
        mail.execute();
    }

    private void hashm(int i) {
        HashMap hp = new HashMap();
        hp.put("status", "Completed");

        HashMap hp5 = new HashMap();
        hp5.put("status", "Completed");
        hp5.put("rating", String.valueOf(i));

        closeact(hp,hp5);
    }

    private void closeact(HashMap hp, HashMap hp5) {
        refDash.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pos=snapshot.child("position").getValue(String.class);
                if (snapshot.exists()){
                    if(pos.equals("admin")){
                        builder_carpenter.setTitle("Alert")
                                .setMessage("Are you sure to close the complaint ??")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        reference_complaints_history_fullView.updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {

                                                sendMessage();
                                                Toast.makeText(historyviewdetails_carpenter.this, "Complaint closed", Toast.LENGTH_SHORT).show();
//                                                if(ContextCompat.checkSelfPermission(historyviewdetails_carpenter.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
//
//                                                }
//                                                else{
//                                                    ActivityCompat.requestPermissions(historyviewdetails_carpenter.this,
//                                                            new String[]{Manifest.permission.SEND_SMS},
//                                                            100);
//                                                }

                                                Intent intent=new Intent(historyviewdetails_carpenter.this, Complaints_HistoryDetails_Carpenter.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);


                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(historyviewdetails_carpenter.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                    else {
                        builder_carpenter.setTitle("Alert")
                                .setMessage("Are you sure to close the complaint ??")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        reference_complaints_history_fullView.updateChildren(hp5).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {
                                                sendMessage();
                                                Toast.makeText(historyviewdetails_carpenter.this, "Complaint closed", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(historyviewdetails_carpenter.this, Complaints_HistoryDetails_Carpenter.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(historyviewdetails_carpenter.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
    }

    private void getReciveremailadd() {
        refemail=FirebaseDatabase.getInstance().getReference("Emails");
        refemail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReceiverEmail = snapshot.child("Carpenter").child("email").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private Boolean getBool() {
        try {
            String string1 = "08:00";
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);

            String string2 = "18:00";
            Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            String someRandomTime = time_str;
            Date d = new SimpleDateFormat("HH:mm").parse(someRandomTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void sendMessage()  {
        // Replace with your username
        String user = "Sonatech";

        // Replace with your API KEY (We have sent API KEY on activation email, also available on panel)
        String apikey = "Y7VVzSPtX3vfsq5AKYCG";

        // Replace with the destination mobile Number to which you want to send sms
        String mobile = staff_mob_str;

        // Replace if you have your own Sender ID, else donot change
        String senderid = "SONACT";

        String str="Your complaint has been closed and solved";

        // Replace with your Message content
        String message = "For request initiated through Sonasoft the one time password is :\" +closed+\". Do not share it with anyone for security reason";

        // For Plain Text, use "txt" ; for Unicode symbols or regional Languages like hindi/tamil/kannada use "uni"
        String type="txt";

        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        //encoding message
        String encoded_message= URLEncoder.encode(message);

        //Send SMS API
        String mainUrl="https://smshorizon.co.in/api/sendsms.php?user=Sonatech&apikey=Y7VVzSPtX3vfsq5AKYCG&mobile=" +mobile+ "&senderid=SONACT&type=txt&tid=1507159884977405639&message="+message;

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        try
        {
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null)
                //print response
                System.out.println(response);

            //finally close connection
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
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

    static void findDifference(String start_date,
                               String end_date) {
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm");

        // Try Class
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in seconds,
            // minutes, hours, years, and days
            difference_In_Seconds
                    = TimeUnit.MILLISECONDS
                    .toSeconds(difference_In_Time)
                    % 60;

            difference_In_Minutes
                    = TimeUnit
                    .MILLISECONDS
                    .toMinutes(difference_In_Time)
                    % 60;

            difference_In_Hours
                    = TimeUnit
                    .MILLISECONDS
                    .toHours(difference_In_Time)
                    % 24;

            difference_In_Days
                    = TimeUnit
                    .MILLISECONDS
                    .toDays(difference_In_Time)
                    % 365;

            difference_In_Years
                    = TimeUnit
                    .MILLISECONDS
                    .toDays(difference_In_Time)
                    / 365l;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds
            System.out.print(
                    "Difference"
                            + " between two dates is: ");

            // Print result
            System.out.println(
                    "                                                       "+
                            difference_In_Years
                            + " years, "
                            + difference_In_Days
                            + " days, "
                            + difference_In_Hours
                            + " hours, "
                            + difference_In_Minutes
                            + " minutes, "
                            + difference_In_Seconds
                            + " seconds");
            System.out.println(difference_In_Hours+" : "+difference_In_Minutes);
        }
        catch ( ParseException e) {
            e.printStackTrace();
        }
    }

}