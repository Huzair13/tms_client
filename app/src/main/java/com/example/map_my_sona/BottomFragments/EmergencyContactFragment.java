package com.example.map_my_sona.BottomFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.map_my_sona.R;
import com.example.map_my_sona.dashboard;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmergencyContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyContactFragment extends Fragment {

    private ImageView hodcall;
    private ImageView hod1call;
    private ImageView hod2call;
    private ImageView hod3call;
    private ImageView hod4call;
    private String mob;
    DatabaseReference dbref;
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

        dbref= FirebaseDatabase.getInstance().getReference().child("Emails");

        hodcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getElectricUnchargeMob();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //pannerselvam sir
                intent.setData(Uri.parse("tel:"+mob));
                startActivity(intent);
            }
        });

        hod1call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCarMobNumber();
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent2.setData(Uri.parse("tel:"+mob));
                startActivity(intent2);
            }
        });
        hod2call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getNetworkMob();
                Intent intent3 = new Intent(Intent.ACTION_DIAL);
                //sakthivel sir
                intent3.setData(Uri.parse("tel:"+mob));
                startActivity(intent3);
            }
        });
        hod3call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPlumbingMob();
                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent4.setData(Uri.parse("tel:"+mob));
                startActivity(intent4);
            }
        });
        hod4call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPaintingMob();
                Intent intent5 = new Intent(Intent.ACTION_DIAL);
                //adiyaman sir
                intent5.setData(Uri.parse("tel:"+mob));
                startActivity(intent5);
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

    private void getPaintingMob() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mob=snapshot.child("Painting").child("mobile").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getPlumbingMob() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mob=snapshot.child("Plumber").child("mobile").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getNetworkMob() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mob=snapshot.child("Network").child("mobile").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getCarMobNumber() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mob=snapshot.child("Carpenter").child("mobile").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getElectricUnchargeMob() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mob=snapshot.child("Electricity").child("mobile").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}