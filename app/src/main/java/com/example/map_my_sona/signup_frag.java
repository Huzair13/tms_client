package com.example.map_my_sona;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class signup_frag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_signup_frag,container,false);

        return root;
    }
}