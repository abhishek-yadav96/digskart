package com.example.digskart.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.digskart.Activity.HomeActivity;
import com.example.digskart.Activity.LoginActivity;
import com.example.digskart.Activity.OtpVerificationActivity;
import com.example.digskart.Activity.RegisterActivity;
import com.example.digskart.Adapter.AllProductAdapter;
import com.example.digskart.Adapter.BestAdapter;
import com.example.digskart.Adapter.CategoryAdapter;
import com.example.digskart.Adapter.PopularProductAdapter;
import com.example.digskart.Adapter.SliderAdapter;
import com.example.digskart.Adapter.SliderAdapter_two;
import com.example.digskart.App.AppController;
import com.example.digskart.Model.AllProductModel;
import com.example.digskart.Model.BestForYoumodel;
import com.example.digskart.Model.CategoryModel;
import com.example.digskart.Model.PopularProductModel;
import com.example.digskart.Model.SliderData;
import com.example.digskart.Model.SliderData_Two;
import com.example.digskart.R;
import com.example.digskart.Utils.Settings;
import com.example.digskart.Utils.Utils;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    public static AlertDialog.Builder builder;
    public static AlertDialog alertDialog;
    public static ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
    public static ArrayList<CategoryModel> catarraylist = new ArrayList<>();
    public static ArrayList<SliderData_Two> sliderArrayList = new ArrayList<>();
    public static ArrayList<PopularProductModel> pparraylist = new ArrayList<>();
    public static ArrayList<BestForYoumodel> bparraylist = new ArrayList<>();
    public static ArrayList<AllProductModel> allarraylist = new ArrayList<>();
    public static SliderAdapter adapter;
    public static SliderAdapter_two sliderAdapter_two;
    public static SliderView sliderView,slider_two;
    public static RecyclerView recyclerView,recyclerView2,recyclerView3,recyclerView4;
    public static CategoryAdapter categoryAdapter;
    public static PopularProductAdapter popularProductAdapter;
    public static BestAdapter bestAdapter;
    public static AllProductAdapter allProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        sliderView = (SliderView) view.findViewById(R.id.slider);
        slider_two = (SliderView) view.findViewById(R.id.slider_two);
        recyclerView =  view.findViewById(R.id.recyclerView);
        recyclerView2 =  view.findViewById(R.id.recyclerView2);
        recyclerView3 =  view.findViewById(R.id.recyclerView3);
        recyclerView4 =  view.findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(new  LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView4.setLayoutManager(new GridLayoutManager(getActivity(),2));
        if(Utils.isNetworkAvailable(getActivity())){
            Utils.getUserDataValueName(getActivity());
            Utils.getUserDataToken(getActivity());
            String token = Settings.TOKEN;
            String userid = Settings.USERID;
            if(!TextUtils.isEmpty(token) && !TextUtils.isEmpty(userid) ){
                SlierApi(getActivity(),userid,token);
            }
        }
        return view;
    }

    synchronized static void SlierApi(final  FragmentActivity activity,final String userid, final String token) {
        Log.e("GEADER","HEADER"+token);
        String URL = "https://api.digskart.com/api/GetDashBord";
        String req = "Checking";
        JsonObjectRequest request_json   = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               Log.e("RESPONSE","RESPONSE"+response);
                bparraylist.clear();
                allarraylist.clear();
                pparraylist.clear();
                catarraylist.clear();
                sliderDataArrayList.clear();
                sliderArrayList.clear();
                try {
                    JSONArray jsonArray = response.getJSONArray("BestProduct");
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String ProductId = jsonObject.getString("ProductId");
                        String ProductName = jsonObject.getString("ProductName");
                        String ProductType = jsonObject.getString("ProductType");
                        String OldPrice = jsonObject.getString("OldPrice");
                        String NewPrice = jsonObject.getString("NewPrice");
                        String CategoryName = jsonObject.getString("CategoryName");
                        String ImageUrl = jsonObject.getString("ImageUrl");
                        String Message = jsonObject.getString("Message");
                        Log.e("GGGG","GGGG"+Message);
                        bparraylist.add(new BestForYoumodel(ProductId,ProductName,ProductType,OldPrice,NewPrice,CategoryName,ImageUrl,Message));
                    }

                    bestAdapter = new BestAdapter(bparraylist,activity);
                    recyclerView3.setAdapter(bestAdapter);

                    JSONArray jsonArray1 = response.getJSONArray("AllProduct");
                    for(int i = 0; i< jsonArray1.length(); i++){
                        JSONObject jsonObject = jsonArray1.getJSONObject(i);
                        String ProductId = jsonObject.getString("ProductId");
                        String ProductName = jsonObject.getString("ProductName");
                        String ProductType = jsonObject.getString("ProductType");
                        String OldPrice = jsonObject.getString("OldPrice");
                        String NewPrice = jsonObject.getString("NewPrice");
                        String CategoryName = jsonObject.getString("CategoryName");
                        String ImageUrl = jsonObject.getString("ImageUrl");
                        String Message = jsonObject.getString("Message");
                        allarraylist.add(new AllProductModel(ProductId,ProductName,ProductType,OldPrice,NewPrice,CategoryName,ImageUrl,Message));

                    }

                    allProductAdapter = new AllProductAdapter(allarraylist,activity);
                    recyclerView4.setAdapter(allProductAdapter);

                    JSONArray jsonArray2 = response.getJSONArray("PopularProduct");
                    for(int i = 0 ; i<jsonArray2.length(); i++){
                        JSONObject jsonObject = jsonArray2.getJSONObject(i);
                        String ProductId = jsonObject.getString("ProductId");
                        String ProductName = jsonObject.getString("ProductName");
                        String ProductType = jsonObject.getString("ProductType");
                        String OldPrice = jsonObject.getString("OldPrice");
                        String NewPrice = jsonObject.getString("NewPrice");
                        String CategoryName = jsonObject.getString("CategoryName");
                        String ImageUrl = jsonObject.getString("ImageUrl");
                        String Message = jsonObject.getString("Message");
                        pparraylist.add(new PopularProductModel(ProductId,ProductName,ProductType,OldPrice,NewPrice,CategoryName,ImageUrl,Message));

                    }

                    popularProductAdapter = new PopularProductAdapter(pparraylist,activity);
                    recyclerView2.setAdapter(popularProductAdapter);

                    JSONArray jsonArray3 = response.getJSONArray("CategogyList");
                    for(int i = 0; i <jsonArray3.length(); i++){
                        JSONObject jsonObject =jsonArray3.getJSONObject(i);
                        String CategoryId = jsonObject.getString("CategoryId");
                        String CategoryName = jsonObject.getString("CategoryName");
                        String CategorySubtitle = jsonObject.getString("CategorySubtitle");
                        String CategoryImage = jsonObject.getString("CategoryImage");
                        String MainImage = jsonObject.getString("MainImage");
                        String TotalProduct = jsonObject.getString("TotalProduct");
                        Boolean IsActive = jsonObject.getBoolean("IsActive");
                        Boolean IsDeleted = jsonObject.getBoolean("IsDeleted");
                        String CreatedById = jsonObject.getString("CreatedById");
                        String CreatedOn = jsonObject.getString("CreatedOn");
                        String ModifiedById = jsonObject.getString("ModifiedById");
                        String ModifiedOn = jsonObject.getString("ModifiedOn");
                        catarraylist.add(new CategoryModel(CategoryId,CategoryName,CategorySubtitle,CategoryImage,MainImage,TotalProduct,IsActive,IsDeleted,CreatedById,CreatedOn,ModifiedById,ModifiedOn));

                    }

                    categoryAdapter = new CategoryAdapter(activity,catarraylist);
                    recyclerView.setAdapter(categoryAdapter);

                    JSONArray jsonArray4 = response.getJSONArray("MainSlider");
                    for(int i = 0 ; i< jsonArray4.length(); i++){
                        JSONObject jsonObject = jsonArray4.getJSONObject(i);
                        String SliderId = jsonObject.getString("SliderId");
                        String ImgType = jsonObject.getString("ImgType");
                        String Title = jsonObject.getString("Title");
                        String ImgUrl = jsonObject.getString("ImgUrl");
                        sliderDataArrayList.add(new SliderData(ImgUrl));
                    }

                    adapter = new SliderAdapter(sliderDataArrayList);
                    sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                    sliderView.setSliderAdapter(adapter);
                    sliderView.setScrollTimeInSec(3);
                    sliderView.setIndicatorSelectedColor(Color.parseColor("#a3211c"));
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();

                    JSONArray jsonArray5 = response.getJSONArray("Slider2");
                    for(int i = 0 ; i<jsonArray5.length();i++){
                        JSONObject jsonObject = jsonArray5.getJSONObject(i);
                        String SliderId = jsonObject.getString("SliderId");
                        String ImgType = jsonObject.getString("ImgType");
                        String Title = jsonObject.getString("Title");
                        String ImgUrl = jsonObject.getString("ImgUrl");
                        sliderArrayList.add(new SliderData_Two(ImgUrl));
                    }

                    sliderAdapter_two = new SliderAdapter_two(sliderArrayList);
                    slider_two.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                    slider_two.setSliderAdapter(sliderAdapter_two);
                    slider_two.setScrollTimeInSec(3);
                    slider_two.setIndicatorSelectedColor(Color.parseColor("#a3211c"));
                    slider_two.setAutoCycle(true);
                    slider_two.startAutoCycle();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String,String >headers=new HashMap<String,String>();
                headers.put("Userid", userid);
                headers.put("token", token);
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(request_json);
    }
}
