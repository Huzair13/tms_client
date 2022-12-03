package com.example.map_my_sona;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Period;

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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class EsclationService_new extends Service {
    DatabaseReference dbref,dbref2;

    String mobile_admin;
    String mobile_superAdmin;

    private static long difference_In_Days,difference_In_Hours,difference_In_Minutes,difference_In_Seconds,difference_In_Time;
    private static long difference_In_Years;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                esclate();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000000);

//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        esclate();
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        ).start();
        return android.app.Service.START_STICKY;
    }

    private void esclate() {
        dbref2=FirebaseDatabase.getInstance().getReference().child("Emails");
        dbref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mobile_admin=snapshot.child("Admin").child("mobile").getValue(Long.class).toString();
                mobile_superAdmin=snapshot.child("SuperAdmin").child("mobile").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        dbref= FirebaseDatabase.getInstance().getReference("complaints");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    for(DataSnapshot ds1 : ds.getChildren()){
                        //DATABASE COMPLAINT TIME
                        String date_old=ds1.child("date").getValue(String.class);
                        String time_old=ds1.child("time").getValue(String.class);

                        //CURRENT DATE
                        String pattern = "dd-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        String date_current = simpleDateFormat.format(new Date());

                        //CURRENT TIME
                        String pattern1 = "HH:mm";
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
                        String time_current = simpleDateFormat1.format(new Date());

                        //ESCALATE MESSAGE
                        String escalate1=ds1.child("escalated1").getValue(String.class);
                        String escalate2=ds1.child("escalated2").getValue(String.class);

//                        int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd-MM-yyyy"), date_old, date_current);
//                        System.out.println("dateDifference: " + dateDifference);
//
//                        String timeDifference=getTimeDiff(new SimpleDateFormat("HH:mm"),time_old,time_current);
//                        System.out.println("Timedifference: " + timeDifference);


                        //YEARS DAYS HOURS MINUTES CALCULATOR
                        findDifference(date_old+" "+time_old,date_current+" "+time_current);

                        //TOTAL HOURS DIFFERENCE
                        long hour_diff=(difference_In_Years*(365*24))+(difference_In_Days*24)+ difference_In_Hours;

                        //ESCALATION CONDITION
                        if(hour_diff>24 && escalate1.equals("NO") && escalate2.equals("NO")){
                            dbref.child(ds.getKey()).child(ds1.getKey()).child("escalated1").setValue("YES");
                            sendMessage(mobile_admin);
                        }
                        else if(hour_diff>48 && escalate2.equals("NO") && escalate1.equals("YES")){
                            dbref.child(ds.getKey()).child(ds1.getKey()).child("escalated2").setValue("YES");
                            sendMessage(mobile_superAdmin);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(String mobile_admin) {

        int upper=100000;
        int lower=900000;
        int r = (int) (Math.random() * (upper - lower)) + lower;

        // Replace with your username
        String user = "Sonatech";

        // Replace with your API KEY (We have sent API KEY on activation email, also available on panel)
        String apikey = "Y7VVzSPtX3vfsq5AKYCG";

        // Replace with the destination mobile Number to which you want to send sms
        String mobile = mobile_admin;

        // Replace if you have your own Sender ID, else donot change
        String senderid = "SONACT";

        String str="Your complaint has been closed and solved";

        // Replace with your Message content
        String message = "For request initiated through Sonasoft the one time password is :" +r+". Do not share it with anyone for security reason";

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
            while ((response = reader.readLine()) != null){
                //print response
                System.out.println(response);
            }

            //finally close connection
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            difference_In_Time
                    = d2.getTime() - d1.getTime();

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
        }
        catch ( ParseException e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
