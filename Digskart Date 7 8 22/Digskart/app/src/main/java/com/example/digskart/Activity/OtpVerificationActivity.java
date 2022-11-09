package com.example.digskart.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
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
import androidx.core.content.res.ResourcesCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.digskart.App.AppController;
import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;
import com.goodiebag.pinview.Pinview;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class OtpVerificationActivity extends AppCompatActivity {
    TextView setmobile,resend,resendtimer;
    String number ;
    public static Pinview codeview;
    Button signupbtn;
    public static AlertDialog.Builder builder;
    public static AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        setmobile = findViewById(R.id.setmobile);
        resend = findViewById(R.id.resend);
        resendtimer = findViewById(R.id.resendtimer);
        signupbtn = findViewById(R.id.signupbtn);
        codeview = (Pinview) findViewById(R.id.codeview);
        Intent intent = getIntent();
        if(intent != null){
            number = intent.getStringExtra("NUMBER");
        }
        setmobile.setText(number);
        traverseEditTexts(OtpVerificationActivity.this, codeview);
        ClickableSpan Resend = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(Utils.isNetworkAvailable(OtpVerificationActivity.this)){
                    resendCode(number);
                }else{
                    Utils.NetworkErrorDialog(OtpVerificationActivity.this, "Please Connect The Internet");
                }
            }
            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(OtpVerificationActivity.this.getResources().getColor(R.color.teal_200));
                textPaint.setUnderlineText(true);
            }
        };
        String text_terms_string = resend.getText().toString();
        SpannableString ss = new SpannableString(text_terms_string);
        int termStart = text_terms_string.indexOf("Resend Code");
        int termStop = termStart + "Resend Code".length();
        ss.setSpan(Resend, termStart, termStop, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.NORMAL),termStart,termStop,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        resend.setText(ss);
        resend.setMovementMethod(LinkMovementMethod.getInstance());
        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                if(millisUntilFinished / 1000==0){
                    resend.setVisibility(View.VISIBLE);
                    resendtimer.setVisibility(View.GONE);
                }else{
                    resendtimer.setText("We Are About To Send You Code Within "+String.valueOf(millisUntilFinished / 1000)+" Sec.");
                }
            }
            public  void onFinish(){
                resend.setVisibility(View.VISIBLE);
                resendtimer.setVisibility(View.GONE);
            }
        }.start();
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utils.isNetworkAvailable(OtpVerificationActivity.this)){
                    if(!codeview.getValue().isEmpty() && codeview.getValue().length()==4){
                        Utils.getUserDataValueName(OtpVerificationActivity.this);
                        confirmCode(number, codeview.getValue());
                    }else{
                        Utils.showOKDialog(OtpVerificationActivity.this,"PLease Enter OTP");
                    }
                }else{
                    Utils.NetworkErrorDialog(OtpVerificationActivity.this, "Please Connect The Internet");
                }

            }
        });
    }

    private void resendCode(String number) {
        String req = "Checking";
        Log.e("NUMBER","NUMBER"+number);
        StringBuilder sb = new StringBuilder("?");
        sb.append("Mobile").append("=").append(number);
        Log.e("STRING","STRING"+sb);
        builder = new AlertDialog.Builder(OtpVerificationActivity.this);
        LayoutInflater inflater = (LayoutInflater) OtpVerificationActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.progress_dialog,(ViewGroup) findViewById(R.id.layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Login Processing...");
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.badaconsultant.com/api/ResendOtp"+sb, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE","RESPONSE>>>>"+response);
                alertDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Id = jsonObject.getString("Id");
                    String UserId = jsonObject.getString("UserId");
                    String ErrorCode = jsonObject.getString("ErrorCode");
                    String MESSAGE = jsonObject.getString("Message");
                    if(ErrorCode.equalsIgnoreCase("101")){
                        Utils.setUserDataValueName(Id,UserId,OtpVerificationActivity.this);
                        Intent intent = new Intent(OtpVerificationActivity.this,HomeActivity.class);
                        intent.putExtra("NUMBER",number);
                        startActivity(intent);
                    }else if(ErrorCode.equalsIgnoreCase("201")){
                        Utils.showOKDialog(OtpVerificationActivity.this,MESSAGE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE","RESPONSE121>>>>"+error);
                alertDialog.dismiss();

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest, req);
    }

    private void confirmCode(String number, String value) {
        Log.e("NUMBER","NUMBER"+number);
        Log.e("NUMBER","NUMBER"+value);
        String URL = "https://api.digskart.com/api/VerifyOtp";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Mobile",number);
        params.put("OTP",value);
        Log.e("PARARMS","PSARAMS"+params);
        builder = new AlertDialog.Builder(OtpVerificationActivity.this);
        LayoutInflater inflater = (LayoutInflater) OtpVerificationActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.progress_dialog,(ViewGroup) findViewById(R.id.layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Login Processing...");
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
                    String Id = response.getString("Id");
                    String UserId = response.getString("UserId");
                    String ErrorCode = response.getString("ErrorCode");
                    String MESSAGE = response.getString("Message");
                    if(ErrorCode.equalsIgnoreCase("101")){
                        Utils.setUserDataValueName(Id,UserId,OtpVerificationActivity.this);
                        RunApi(UserId);
                    }else if(ErrorCode.equalsIgnoreCase("201")){
                        Utils.showOKDialog(OtpVerificationActivity.this,MESSAGE);
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

    @SuppressLint("SoonBlockedPrivateApi")
    public void traverseEditTexts(Context context, ViewGroup v) {
        for (int i = 0; i < v.getChildCount(); i++) {
            Object child = v.getChildAt(i);
            if (child instanceof EditText) {
                EditText e = (EditText) child;
                e.setTypeface(ResourcesCompat.getFont(context, R.font.quicksandregular));
                e.setTextColor(context.getResources().getColor(R.color.white));
                Field f = null;
                try {
                    f = TextView.class.getDeclaredField("mCursorDrawableRes");
                    f.setAccessible(true);
                    f.set(e, R.drawable.edit_cursor);
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private void RunApi(String userId) {
        Utils.getUserDataValueName(OtpVerificationActivity.this);
        String req = "Checking";
        builder = new AlertDialog.Builder(OtpVerificationActivity.this);
        LayoutInflater inflater = (LayoutInflater) OtpVerificationActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.progress_dialog,(ViewGroup) findViewById(R.id.layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Loding Data...");
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.digskart.com/api/CreateTokenByUserid", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE","RESPONSE>TR>>>"+response);
                alertDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String MESSAGE = jsonObject.getString("message");
                    String STATUS = jsonObject.getString("status");
                    String TOKEN = jsonObject.getString("token");
                    if(STATUS.equalsIgnoreCase("Success")){
                        Utils.setLogin(OtpVerificationActivity.this,true);
                        Utils.setUserDataToken(TOKEN,OtpVerificationActivity.this);
                        Intent intent = new Intent(OtpVerificationActivity.this,HomeActivity.class);
                        intent.putExtra("NUMBER",number);
                        startActivity(intent);
                        // RunApiGetallProduct();
                    }else {
                        Utils.showOKDialog(OtpVerificationActivity.this,"Please Conatct with admin");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE","RESPONSE12Tr1>>>>"+error);
                alertDialog.dismiss();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String >headers=new HashMap<String,String>();
                headers.put("Userid", userId);
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, req);
    }
}
