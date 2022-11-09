package com.example.digskart.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;

public class ProfileSetting extends Fragment {

    EditText username,password,Email,mobile;
    ImageView cancdel,back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile_settings,container,false);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        Email = view.findViewById(R.id.Email);
        mobile = view.findViewById(R.id.mobile);
        back =    view.findViewById(R.id.back);
        Utils.getUserData(getActivity());
        String username1 = Settings.NAME;
        String email = Settings.EMAIL;
        String mobile1 = Settings.MOBILE;
        username.setText(username1);
        Email.setText(email);
        mobile.setText(mobile1);
        mobile.setEnabled(false);
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
