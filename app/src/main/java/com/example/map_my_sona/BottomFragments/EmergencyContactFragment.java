package com.example.map_my_sona.BottomFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.map_my_sona.AdminDashboard;
import com.example.map_my_sona.R;
import com.example.map_my_sona.dashboard;
import com.example.map_my_sona.emergencyContact;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmergencyContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyContactFragment extends Fragment {

    ImageView hodcall;
    ImageView hod1call;
    ImageView hod2call;
    ImageView hod3call;
    ImageView hod4call;
    MaterialToolbar toolbar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmergencyContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmergencyContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmergencyContactFragment newInstance(String param1, String param2) {
        EmergencyContactFragment fragment = new EmergencyContactFragment();
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
        View view= inflater.inflate(R.layout.fragment_emergency_contact, container, false);

        hodcall=view.findViewById(R.id.hodcall1);
        hod1call=view.findViewById(R.id.hod1call1);
        hod2call=view.findViewById(R.id.hod2call1);
        hod3call=view.findViewById(R.id.hod3call1);
        hod4call=view.findViewById(R.id.hod4call1);

        hodcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //pannerselvam sir
                intent.setData(Uri.parse("tel:7418009997"));
                startActivity(intent);
            }
        });

        hod1call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:9894341589"));
                startActivity(intent1);
            }
        });
        hod2call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //sakthivel sir
                intent1.setData(Uri.parse("tel:9442531522"));
                startActivity(intent1);
            }
        });
        hod3call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:9894341589"));
                startActivity(intent1);
            }
        });
        hod4call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent1.setData(Uri.parse("tel:9894341589"));
                startActivity(intent1);
            }
        });

        toolbar= view.findViewById(R.id.topAppBar1);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"your icon was clicked",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),dashboard.class));
            }
        });

        return view;
    }
}