package com.example.digskart.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.digskart.App.AppController;
import com.example.digskart.Fragment.ContactUs;
import com.example.digskart.Fragment.HomeFragment;
import com.example.digskart.Fragment.ProfileFragment;
import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.navigation);
        GetHome();
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.firstFragment:
                        GetHome();
                        // do something here
                        return true;
                    case R.id.secondFragment:
                       // GetCatogery();
                        // do something here
                        return true;
                    case R.id.thirdFragment:
                        //GetPassbook();
                        // do something here
                        return true;
                    case R.id.fourFragment:
                       // startActivity(new Intent(HomeActivity.this,CreateidActivity.class));
                        return true;
                    case R.id.fiveFragment:
                        GetProfile();
                        return true;
                    default: return true;
                }
            }
        });
    }

    public void GetHome() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        HomeFragment N = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, N, HomeFragment.class.getName());
        transaction.commit();
    }
    public void GetProfile() {
        ProfileFragment N = new ProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, N, ProfileFragment.class.getName());
        transaction.commit();
    }

}
