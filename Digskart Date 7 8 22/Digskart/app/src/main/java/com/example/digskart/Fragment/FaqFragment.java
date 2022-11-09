package com.example.digskart.Fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.digskart.Adapter.CustomExpandableListAdapter;
import com.example.digskart.App.AppController;
import com.example.digskart.Model.DataListModel;
import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FaqFragment extends Fragment {

    public static ArrayList<DataListModel> arrayList = new ArrayList<DataListModel>();
    public static ExpandableListView expandableListView;
    public static CustomExpandableListAdapter expandableListAdapter;
    ImageView back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faq_fragment,container,false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        back = view.findViewById(R.id.back);
        if (Utils.isNetworkAvailable(getActivity())) {
            Utils.getUserDataValueName(getActivity());
            Utils.getUserDataToken(getActivity());
            String token = Settings.TOKEN;
            String userid = Settings.USERID;
            RunApi(userid,token);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            expandableListView.setIndicatorBounds(width - getPixelValue(80), width - getPixelValue(20));
        } else {
            expandableListView.setIndicatorBoundsRelative(width - getPixelValue(80), width - getPixelValue(20));
        }
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

    private void RunApi(String userid, String token) {
        String req = "ref";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.badaconsultant.com/api/GetFaq", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE","RESPONSE"+response);
                expandableListView.setVisibility(View.VISIBLE);
                expandableListAdapter = new CustomExpandableListAdapter(getContext(), arrayList);
                expandableListView.setAdapter(expandableListAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE","RESPONSE"+error);

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String >headers=new HashMap<String,String>();
                headers.put("UserId",userid);
                headers.put("Token",token);
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest,req);
    }
    public int getPixelValue(int dp) {
        final float scale = getActivity().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.3f);
    }
}
