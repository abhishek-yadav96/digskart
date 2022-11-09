package com.example.digskart.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digskart.Activity.WebViewActivity;
import com.example.digskart.R;

public class LefgalFragment extends Fragment {

    TextView navopt4,navopt5,navopt6;
    ImageView cancdel,back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.legal_fragment,container,false);
        navopt4 = view.findViewById(R.id.navopt4);
        navopt5 = view.findViewById(R.id.navopt5);
        navopt6 = view.findViewById(R.id.navopt6);
        back =    view.findViewById(R.id.back);
        navopt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra("TEXT", "privacy_policy");
                startActivity(i);
            }
        });

        navopt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra("TEXT", "terms_and_conditions");
                startActivity(i);
            }
        });

        navopt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra("TEXT", "Refund_policy");
                startActivity(i);
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
