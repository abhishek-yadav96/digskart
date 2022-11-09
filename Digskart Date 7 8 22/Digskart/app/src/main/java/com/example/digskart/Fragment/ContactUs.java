package com.example.digskart.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digskart.R;

public class ContactUs extends Fragment {

    TextView text1,text2;
    RelativeLayout chatdetails;
    ImageView cancdel,back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_contact_us,container,false);
        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        cancdel = view.findViewById(R.id.cancdel);
        back = view.findViewById(R.id.back);
        chatdetails = view.findViewById(R.id.chatdetails);
        chatdetails.setVisibility(View.GONE);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FaqFragment fragment2 = new FaqFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatdetails.setVisibility(View.VISIBLE);
            }
        });
        cancdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatdetails.setVisibility(View.GONE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment fragment2 = new ProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });
       return view;
    }
}

