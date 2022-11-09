package com.example.digskart.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.digskart.App.AppConfig;
import com.example.digskart.App.AppController;
import com.example.digskart.R;
import com.example.digskart.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname,Email,Password,mobilenumber;
    Button signupbtn;
    public static AlertDialog.Builder builder;
    public static AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstname = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        mobilenumber = findViewById(R.id.mobilenumber);
        signupbtn = findViewById(R.id.signupbtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = firstname.getText().toString();
                String user_email = Email.getText().toString();
                String user_pass = Password.getText().toString();
                String number = mobilenumber.getText().toString();
                if(!TextUtils.isEmpty(name)){
                    if(!TextUtils.isEmpty(user_email)){
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if (user_email.matches(emailPattern)){
                            if(!TextUtils.isEmpty(user_pass)){
                                if(!TextUtils.isEmpty(number)){
                                    RunApi(name,user_email,user_pass,number);
                                }else {
                                    Utils.showOKDialog(RegisterActivity.this,"Please Enter the Number");
                                }
                            }else {
                                Utils.showOKDialog(RegisterActivity.this,"Please Enter the Password");
                            }
                        }else {
                            Utils.showOKDialog(RegisterActivity.this,"Please Enter the Proper Email");
                        }
                    }else {
                        Utils.showOKDialog(RegisterActivity.this,"Please Enter the Email");
                    }
                }else {
                    Utils.showOKDialog(RegisterActivity.this,"Please Enter the Name");
                }
            }
        });
    }

    private void RunApi(final String name, final String user_email, final String user_pass, final String number) {
        Log.e("NUMBER","NUMBER"+name);
        Log.e("NUMBER","NUMBER"+user_email);
        Log.e("NUMBER","NUMBER"+user_pass);
        Log.e("NUMBER","NUMBER"+number);
        String URL = "https://api.badaconsultant.com/api/Registration";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Name",name);
        params.put("ReferralCode","");
        params.put("Email",user_email);
        params.put("Mobile",number);
        params.put("Password",user_pass);
        params.put("FriendsCode","");
        params.put("FcmId","");
        params.put("ProfileImg","");
        params.put("CountryCode","");
        params.put("Balance","");
        Log.e("RESPONSE","DATA"+new JSONObject(params));
        builder = new AlertDialog.Builder(RegisterActivity.this);
        LayoutInflater inflater = (LayoutInflater) RegisterActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.progress_dialog,(ViewGroup) findViewById(R.id.layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Registering...");
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        JsonObjectRequest request_json   = new JsonObjectRequest(URL, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("RESPONSE","RESPONSE"+response);
                alertDialog.dismiss();
            try {
                    String USERId = response.getString("UserId");
                    String ERRORCODE = response.getString("ErrorCode");
                    String MESSAGE = response.getString("Message");
                    if(ERRORCODE.equalsIgnoreCase("101")){
                        Utils.showOKDialog(RegisterActivity.this,MESSAGE);
                        Intent intent = new Intent(RegisterActivity.this,OtpVerificationActivity.class);
                        intent.putExtra("NUMBER",number);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE","RESPONSE11"+error);
                alertDialog.dismiss();

            }
        });
        AppController.getInstance().addToRequestQueue(request_json);
    }

}
