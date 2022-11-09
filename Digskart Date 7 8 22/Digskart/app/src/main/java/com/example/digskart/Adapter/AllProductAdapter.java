package com.example.digskart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digskart.Activity.UploadViedo;
import com.example.digskart.Fragment.ProductDetails;
import com.example.digskart.Fragment.ProfileSetting;
import com.example.digskart.Model.AllProductModel;
import com.example.digskart.Model.BestForYoumodel;
import com.example.digskart.R;

import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.Viewholder>{

    ArrayList<AllProductModel> arrayList;
    Context context;

    public AllProductAdapter(ArrayList<AllProductModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        Log.e("ARRAYA","ARRAY "+arrayList);
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_product_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        AllProductModel data = arrayList.get(position);
        String Url = data.getImageUrl();
        String name = data.getProductName();
        String categoryname = data.getCategoryName();
        String price = data.getNewPrice();
        String Id = data.getProductId();
        Glide.with(holder.itemView)
                .load(Url)
                .fitCenter()
                .into(holder.image);
        holder.tittle.setText(name);
        holder.text1.setText(categoryname);
        holder.text2.setText(price);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
//                ProductDetails tab1 = new ProductDetails();
//                final Bundle bundle = new Bundle();
//                bundle.putString("ID",Id);
//                Log.e("id","idada"+Id);
//                tab1.setArguments(bundle);
//                manager.beginTransaction().replace(R.id.content,tab1)
//                        .commit();
                Intent intent = new Intent(context, UploadViedo.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView tittle,text1,text2;

        public Viewholder(@NonNull View itemview){
            super(itemview);
            image = itemview.findViewById(R.id.image);
            tittle = itemview.findViewById(R.id.tittle);
            text1 = itemview.findViewById(R.id.text1);
            text2 = itemview.findViewById(R.id.text2);
        }
    }

}
