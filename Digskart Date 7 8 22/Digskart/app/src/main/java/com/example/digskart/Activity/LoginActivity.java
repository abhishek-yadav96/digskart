package com.example.digskart.Activity;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.digskart.App.AppController;
import com.example.digskart.R;
import com.example.digskart.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText firstname;
    Button signupbtn;
    public static AlertDialog.Builder builder;
    public static AlertDialog alertDialog;
    TextView text2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firstname = findViewById(R.id.firstname);
        signupbtn = findViewById(R.id.signupbtn);
        text2 = findViewById(R.id.text2);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = firstname.getText().toString();
                if(!TextUtils.isEmpty(num)){
                    RunApi(num);
                }
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                LoginActivity.this.finish();
            }
        });
    }

    private void RunApi(final String num) {
        Log.e("NUMBER","NUMBER"+num);
        String tag_string_req = "req_register";
        builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = (LayoutInflater) LoginActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.progress_dialog,(ViewGroup) findViewById(R.id.layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Registering...");
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        StringBuilder sb = new StringBuilder("?");
        sb.append("Mobile").append("=").append(num);
        Log.e("STRING","STRING"+sb);
        StringRequest strReq = new StringRequest(Request.Method.POST, "https://api.digskart.com/api/CheckLogin"+sb, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE","RESPONSE>>>>"+response);
                alertDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String USERID = jsonObject.getString("UserId");
                    String ERROCODE = jsonObject.getString("ErrorCode");
                    String MESSAGE = jsonObject.getString("Message");
                    if(ERROCODE.equalsIgnoreCase("101")){
                        Intent intent = new Intent(LoginActivity.this,OtpVerificationActivity.class);
                        intent.putExtra("NUMBER",num);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }else if(ERROCODE.equalsIgnoreCase("201")){
                        Utils.showOKDialog(LoginActivity.this,"Please Register Yourslef");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("dgfcgf", "RESPONCE>>>212"+error);
                alertDialog.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
