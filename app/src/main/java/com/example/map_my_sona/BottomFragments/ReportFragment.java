package com.example.map_my_sona.BottomFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.map_my_sona.admin.AdminDashboard;
import com.example.map_my_sona.R;
import com.example.map_my_sona.Report_Details;
import com.example.map_my_sona.dashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import papaya.in.sendmail.SendMail;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    EditText report;
    Button report_btn;
    DatabaseReference dbRef;
    String report_str;
    String userEmail;
    MaterialToolbar toolbar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_report, container, false);

        report=(EditText) view.findViewById(R.id.report_text1);
        report_btn=(Button) view.findViewById(R.id.report_submit_btn1);
        toolbar= view.findViewById(R.id.reporttopAppBar1);

        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), AdminDashboard.class));
            }
        });

        return view;
    }

    private void checkValidation() {
        report_str=report.getText().toString();
        if(report_str.isEmpty()){
            report.setError("Required");
            report.requestFocus();
        }
        else{
            submitReport();
        }
    }

    private void submitReport() {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Reports");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userEmail = user.getEmail();
        }
        final String uniqueKey = dbRef.push().getKey();

        Report_Details report_details = new Report_Details(report_str,userEmail);
        dbRef.child(uniqueKey).setValue(report_details).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getActivity(), "Report Submitted Successfully", Toast.LENGTH_SHORT).show();

//                SendMail mail1=new SendMail("mapmysona@gmail.com",
//                        "mms@2022",
//                        "ahamedhuzair13@gmail.com",
//                        "A Report has been submitted",
//                        "A Report has been submitted by the user "+userEmail+" . Please check report : "+report_str
//                );
//                mail1.execute();
//
//                SendMail mail2=new SendMail("mapmysona@gmail.com",
//                        "mms@2022",
//                        "srisanjanaarunkumar@gmail.com",
//                        "A Report has been submitted",
//                        "A Report has been submitted by the user "+userEmail+". Please check report : "+report_str
//                );
//                mail2.execute();
//
//                SendMail mail3=new SendMail("mapmysona@gmail.com",
//                        "mms@2022",
//                        "bs.vigneshwaran@gmail.com",
//                        "A Report has been submitted",
//                        "A Report has been submitted by the user "+userEmail+" . Please check report : "+report_str
//                );
//                mail3.execute();
//
//                SendMail mail4=new SendMail("mapmysona@gmail.com",
//                        "mms@2022",
//                        "shreedev2k3@gmail.com",
//                        "A Report has been submitted",
//                        "A Report has been submitted by the user "+userEmail+" . Please check report : "+report_str
//                );
//                mail4.execute();

                Intent intent = new Intent(getActivity(), dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}