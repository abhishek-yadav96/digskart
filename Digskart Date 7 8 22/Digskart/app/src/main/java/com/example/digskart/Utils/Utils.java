package com.example.digskart.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.digskart.R;

import java.util.Calendar;

public class Utils {

    static ProgressDialog progressDialog;

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }
    public static void setLogin(Context context, boolean isLoggedIn) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("com.NFMAINIsLoggedIn", isLoggedIn);
        editor.commit();
    }

    public static boolean isLoggedIn(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("com.NFMAINIsLoggedIn", false);
    }

    public static void showOKDialog(final Activity context, final String dlgText){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.ok_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.windowAnimations = R.style.DialogAnimation;
        TextView optiontext = (TextView) dialog.findViewById(R.id.optiontext);
        TextView btnOk = (TextView) dialog.findViewById(R.id.btnOk);
        optiontext.setText(dlgText);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout((int) (Utils.getScreenWidth(context) * 1), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    public static final void setUserDataValueName(String ID, String Name, String Email, String Password, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("acID", ID);
        editor.putString("acName", Name);
        editor.putString("acEmail", Email);
        editor.putString("acPassword", Password);
        editor.commit();
    }


    public static boolean isNetworkAvailable(Context mActivity) {
        if(mActivity!=null){
            ConnectivityManager connectivityManager = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
             NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    public static void NetworkErrorDialog(final Activity activity, final String dlgText) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.permission_error_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.windowAnimations = R.style.DialogAnimation;
        TextView optiontitle = (TextView) dialog.findViewById(R.id.optiontitle);
        final TextView optiontext = (TextView) dialog.findViewById(R.id.optiontext);
        TextView btnCancel = (TextView) dialog.findViewById(R.id.btnCancel);
        TextView btnOk = (TextView) dialog.findViewById(R.id.btnOk);
        optiontitle.setText("Network Error!");
        optiontext.setText(dlgText);
        btnCancel.setVisibility(View.GONE);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout((int) (Utils.getScreenWidth(activity) * 1), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
    public static final void setUserDataValueName(String ID, String UserId, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("DKID", ID);
        editor.putString("DKUserId", UserId);
        editor.commit();
    }

    public static void getUserDataValueName(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String uID = prefs.getString("DKID", "");
        String uName = prefs.getString("DKUserId", "");
        Settings.ID = uID;
        Settings.USERID = uName;
    }
    public static final void setUserDataToken(String TOKEN, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("DKTOKEN", TOKEN);
        editor.commit();
    }

    public static void getUserDataToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String uToken = prefs.getString("DKTOKEN", "");
        Settings.TOKEN = uToken;
    }

    public static void getUserData(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String USEWRID = prefs.getString("DKUSEWRID", "");
        String NAME = prefs.getString("DKNAME", "");
        String REFERRALCODE = prefs.getString("DKREFERRALCODE", "");
        String EMAIL = prefs.getString("DKEMAIL", "");
        String MOBILE = prefs.getString("DKMOBILE", "");
        String PASSWORD = prefs.getString("DKPASSWORD", "");
        String FRIENDSCODE = prefs.getString("DKFRIENDSCODE", "");
        String FCMID = prefs.getString("DKFCMID", "");
        String PROFILEIMG = prefs.getString("DKPROFILEIMG", "");
        String COUNTRYCODE = prefs.getString("DKCOUNTRYCODE", "");
        String BALANCE = prefs.getString("DKBALANCE", "");
        String MESSAGE = prefs.getString("DKMESSAGE", "");
        Settings.USEWRID = USEWRID;
        Settings.NAME = NAME;
        Settings.REFERRALCODE = REFERRALCODE;
        Settings.EMAIL = EMAIL;
        Settings.MOBILE = MOBILE;
        Settings.PASSWORD = PASSWORD;
        Settings.FRIENDSCODE = FRIENDSCODE;
        Settings.FCMID = FCMID;
        Settings.PROFILEIMG = PROFILEIMG;
        Settings.COUNTRYCODE = COUNTRYCODE;
        Settings.BALANCE = BALANCE;
        Settings.MESSAGE = MESSAGE;
    }
    public static final void setUserData(String USEWRID,String NAME,String REFERRALCODE,String EMAIL,String MOBILE,String PASSWORD,String FRIENDSCODE,String FCMID,String PROFILEIMG,String COUNTRYCODE,String BALANCE,String MESSAGE, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("DKUSEWRID", USEWRID);
        editor.putString("DKNAME", NAME);
        editor.putString("DKREFERRALCODE", REFERRALCODE);
        editor.putString("DKEMAIL", EMAIL);
        editor.putString("DKMOBILE", MOBILE);
        editor.putString("DKPASSWORD", PASSWORD);
        editor.putString("DKFRIENDSCODE", FRIENDSCODE);
        editor.putString("DKFCMID", FCMID);
        editor.putString("DKPROFILEIMG", PROFILEIMG);
        editor.putString("DKCOUNTRYCODE", COUNTRYCODE);
        editor.putString("DKBALANCE", BALANCE);
        editor.putString("DKMESSAGE", MESSAGE);
        editor.commit();
    }
    public final static String copyrights() {
        String yearfinal = yearValue();
        String copyR = "\u00A9";
        String appname = "DIGKART";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String option = "<font color=\"#FFFFFF\">"+copyR+"</font>";
        builder.append(option+" ");
        String option2 = "<font color=\"#FFFFFF\">"+yearfinal+"</font>";
        builder.append(option2+" ");
        String option3 = "<font color=\"#03DAC5\">"+appname+"</font>";
        builder.append(option3+" ");
        String option4 = "<font color=\"#FFFFFF\">All Rights Reserved</font>";
        builder.append(option4+" ");
        String finaltxt = String.valueOf(builder);
        return finaltxt;
    }

    public final static String yearValue() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String yearfinal = String.valueOf(year);
        return yearfinal;
    }

    public static void showRequestDialog(Activity activity) {

        try {
            if (!((Activity) activity).isFinishing()) {

                if (progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                    progressDialog=null;
                }


                progressDialog = new ProgressDialog(activity);
                progressDialog.setCancelable(false);
                progressDialog.setMessage(activity.getString(R.string.pleaseWait));
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
