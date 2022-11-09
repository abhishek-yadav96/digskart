package com.example.digskart.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.digskart.Activity.HomeActivity;
import com.example.digskart.Activity.SplashActivity;
import com.example.digskart.Activity.WebViewActivity;
import com.example.digskart.Adapter.AllProductAdapter;
import com.example.digskart.App.AppController;
import com.example.digskart.MainActivity;
import com.example.digskart.Model.AllProductModel;
import com.example.digskart.Model.UserDetailModel;
import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    public static TextView username;
    ArrayList<UserDetailModel> arrayList = new ArrayList<>();
    LinearLayout contactus,legal,aboutus,rateus,logout,profile_setting,invite;
    private SharedPreferences sharedPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
        username = view.findViewById(R.id.username);
        contactus = view.findViewById(R.id.contactus);
        legal = view.findViewById(R.id.legal);
        aboutus = view.findViewById(R.id.aboutus);
        rateus = view.findViewById(R.id.rateus);
        logout = view.findViewById(R.id.logout);
        invite = view.findViewById(R.id.invite);
        profile_setting = view.findViewById(R.id.profile_setting);
        //mainActivityView = new MainActivity();
        if(Utils.isNetworkAvailable(getActivity())){
            Utils.getUserDataValueName(getActivity());
            Utils.getUserDataToken(getActivity());
            String token = Settings.TOKEN;
            String userid = Settings.USERID;
            if(!TextUtils.isEmpty(token) && !TextUtils.isEmpty(userid) ){
                GetProfileApi(getActivity(),userid,token);
            }
        }
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("kkkk","kkkkk");
                ContactUs fragment2 = new ContactUs();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });
        legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("kkkk","kkkkk");
                LefgalFragment fragment2 = new LefgalFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra("TEXT", "About Us");
                startActivity(i);
            }
        });
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra("TEXT", "Rate_us_star");
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog(getActivity());
            }
        });
        profile_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("kkkk","kkkkk");
                ProfileSetting fragment2 = new ProfileSetting();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("kkkk","kkkkk");
                Invitefriend fragment2 = new Invitefriend();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment2);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public static void MyDialog(final Activity context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.okcancel_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.windowAnimations = R.style.DialogAnimation;
        TextView btnCancel = (TextView) dialog.findViewById(R.id.btnCancel);
        TextView btnOk = (TextView) dialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();
                context.startActivity(new Intent(context, SplashActivity.class));
                context.finish();
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout((int) (Utils.getScreenWidth(context) * 1), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void GetProfileApi(final FragmentActivity activity, final String userid, final String token) {
        String req = "Checking";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.digskart.com/api/GETBYID", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE","RESPONSE>129>>>"+response);
                try {
                    arrayList.clear();
                    JSONObject jsonObject = new JSONObject(response);
                    String USEWRID = jsonObject.getString("UserId");
                    String NAME = jsonObject.getString("Name");
                    String REFERRALCODE = jsonObject.getString("ReferralCode");
                    String EMAIL = jsonObject.getString("Email");
                    String MOBILE = jsonObject.getString("Mobile");
                    String PASSWORD = jsonObject.getString("Password");
                    String FRIENDSCODE = jsonObject.getString("FriendsCode");
                    String FCMID = jsonObject.getString("FcmId");
                    String PROFILEIMG = jsonObject.getString("ProfileImg");
                    String COUNTRYCODE = jsonObject.getString("CountryCode");
                    String BALANCE = jsonObject.getString("Balance");
                    String MESSAGE = jsonObject.getString("Message");
                    Utils.setUserData(USEWRID,NAME,REFERRALCODE,EMAIL,MOBILE,PASSWORD,FRIENDSCODE,FCMID,PROFILEIMG,COUNTRYCODE,BALANCE,MESSAGE,activity);
                    username.setText("HI "+NAME);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE","RESPONSE129>>>>"+error);

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String >headers=new HashMap<String,String>();
                headers.put("Userid", userid);
                headers.put("Token", token);
                Log.e("GEADER","HEADER"+headers);
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, req);
    }
}
