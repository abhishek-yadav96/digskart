package com.example.digskart.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.digskart.Activity.LoginActivity;
import com.example.digskart.Activity.OtpVerificationActivity;
import com.example.digskart.Adapter.SliderAdapter;
import com.example.digskart.Adapter.SliderProductAdapter;
import com.example.digskart.App.AppController;
import com.example.digskart.Model.ImageVideModel;
import com.example.digskart.Model.ProductdetailsModel;
import com.example.digskart.Model.SliderData;
import com.example.digskart.Model.SliderData_Product;
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

public class ProductDetails extends Fragment {

    String id;
    public static AlertDialog.Builder builder;
    public static AlertDialog alertDialog;
    String Id,PackageText1,ImageUrl,ReviewTexts, ProductId ,ProductParentId, CategoryId, ProductName, OldPrice, NewPrice, LongDesc, Measurement, Unit, CategoryName, Message, ProductType ,OfferDetails, DeliveryDays, RevisionTimes, PackageText, UnitId, SampleVideoUrl;
    Boolean IsActive, IsDeleted;
    ArrayList<ProductdetailsModel> arrayList = new ArrayList<>();
    ArrayList<ImageVideModel> arrayList1 = new ArrayList<>();
    ArrayList<SliderData_Product> sliderDataArrayList = new ArrayList<>();
    public static SliderView sliderView;
    public static SliderProductAdapter adapter;
    public static VideoView videoview;
    public static ImageView iamgeview;
    public static RelativeLayout topVideoview;
    RecyclerView recycler;
    TextView text1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_details,container,false);
        sliderView = (SliderView) view.findViewById(R.id.slider);
        recycler = view.findViewById(R.id.recycler);
        text1 = view.findViewById(R.id.text1);
        videoview = view.findViewById(R.id.videoview);
        iamgeview = view.findViewById(R.id.iamgeview);
        topVideoview = view.findViewById(R.id.topVideoview);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        Bundle args = getArguments();
        if (args  != null){
            id = args.getString("ID");
            Log.e("id","id"+id);
        }
        if(Utils.isNetworkAvailable(getActivity())){
            Utils.getUserDataValueName(getActivity());
            Utils.getUserDataToken(getActivity());
            String token = Settings.TOKEN;
            String userid = Settings.USERID;
            GetProductDetails(id,token,userid);
        }
        return view;
    }

    private void GetProductDetails(final String id, final String token, final  String userid) {
        String tag_string_req = "req_register";
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.progress_dialog,(ViewGroup) getActivity().findViewById(R.id.layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Registering...");
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        StringBuilder sb = new StringBuilder("?");
        sb.append("ProductId").append("=").append(id);
        sb.append("&").append("ProductType").append("=").append("Basic");
        Log.e("STRING","STRING"+sb);
        StringRequest strReq = new StringRequest(Request.Method.GET, "https://api.badaconsultant.com/api/GetProductByProductId"+sb, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE","RESPONSE25.08>>>>"+response);
                alertDialog.dismiss();
                try {
                    arrayList1.clear();
                    arrayList.clear();
                    JSONObject jsonObject = new JSONObject(response);
                    String USERID = jsonObject.getString("ProductImages");
                    JSONArray jsonArray = new JSONArray(USERID);
                    for(int i =0; i<jsonArray.length(); i++){
                        JSONObject jso = jsonArray.getJSONObject(i);
                        ImageUrl = jso.getString("ImageUrl");
                        Log.e("ProductId","ProductId"+ImageUrl);
                    }
                    String PacKageConfigs = jsonObject.getString("PacKageConfigs");
                    Log.e("PacKageConfigs","PacKageConfigs"+PacKageConfigs);
                    JSONArray jsoarra = new JSONArray(PacKageConfigs);
                    for(int i = 0; i<jsoarra.length(); i ++){
                        JSONObject jsu = jsoarra.getJSONObject(i);
                        PackageText1 = jsu.getString("PackageText1");
                    }
                    ReviewTexts = jsonObject.getString("ReviewTexts");
                    ProductId = jsonObject.getString("ProductId");
                    ProductParentId = jsonObject.getString("ProductParentId");
                    CategoryId = jsonObject.getString("CategoryId");
                    ProductName = jsonObject.getString("ProductName");
                    OldPrice = jsonObject.getString("OldPrice");
                    NewPrice = jsonObject.getString("NewPrice");
                    LongDesc = jsonObject.getString("LongDesc");
                    Measurement = jsonObject.getString("Measurement");
                    Unit = jsonObject.getString("Unit");
                    CategoryName = jsonObject.getString("CategoryName");
                    Message = jsonObject.getString("Message");
                    ProductType = jsonObject.getString("ProductType");
                    OfferDetails = jsonObject.getString("OfferDetails");
                    DeliveryDays = jsonObject.getString("DeliveryDays");
                    RevisionTimes = jsonObject.getString("RevisionTimes");
                    PackageText = jsonObject.getString("PackageText");
                    UnitId = jsonObject.getString("UnitId");
                    SampleVideoUrl = jsonObject.getString("SampleVideoUrl");
                    IsActive = jsonObject.getBoolean("IsActive");
                    IsDeleted = jsonObject.getBoolean("IsDeleted");
                    arrayList.add(new ProductdetailsModel(ImageUrl,PackageText1,ReviewTexts,ProductId,ProductParentId,CategoryId,ProductName,OldPrice,NewPrice,LongDesc,Measurement,Unit,CategoryName,Message,ProductType,OfferDetails,DeliveryDays,RevisionTimes,PackageText,UnitId,SampleVideoUrl,IsActive,IsDeleted));
                    arrayList1.add(new ImageVideModel(ImageUrl,SampleVideoUrl));
                    PdroductViewadapter pdroductViewadapter = new PdroductViewadapter(getActivity(),arrayList1);
                    recycler.setAdapter(pdroductViewadapter);
                    if(!TextUtils.isEmpty(ProductName)){
                        text1.setText(ProductName);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("dgfcgf", "RESPONCE>>2>212"+error);
                alertDialog.dismiss();
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
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public class PdroductViewadapter extends RecyclerView.Adapter<PdroductViewadapter.Viewholde>{
        Context context;
        ArrayList<ImageVideModel> arrayList;

        public PdroductViewadapter(Context context, ArrayList<ImageVideModel> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public Viewholde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.productdetailslist,parent,false);
            return new Viewholde(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Viewholde holder, int position) {
            ImageVideModel data = arrayList.get(position);
            String Image = data.getImage();
            String Video = data.getVideo();
            Log.e("INMAG1","IMHFD"+Image);
            if(Image.equalsIgnoreCase("Image")){
                Glide.with(context).load(Image)
                        .into(holder.iamgeview);
            }else if(Video.equalsIgnoreCase("Video")){
                Glide.with(context).load(Video)
                        .into(holder.iamgeview);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Image.equalsIgnoreCase("Image")){
                        topVideoview.setVisibility(View.GONE);
                        iamgeview.setVisibility(View.VISIBLE);
                        Log.e("SETTT","SETT"+Image);
                        Glide.with(context).load(Image)
                                .into(iamgeview);
                    }else {
                        topVideoview.setVisibility(View.VISIBLE);
                        iamgeview.setVisibility(View.GONE);
                        Log.e("SETTT","SETT"+Video);
                        videoview.setVideoPath(Video);
                        videoview.start();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


        public class Viewholde extends RecyclerView.ViewHolder{

            ImageView iamgeview;

            public Viewholde(@NonNull View itemview){
                super(itemview);
                iamgeview = itemview.findViewById(R.id.iamgeview);
            }
        }
    }
}
