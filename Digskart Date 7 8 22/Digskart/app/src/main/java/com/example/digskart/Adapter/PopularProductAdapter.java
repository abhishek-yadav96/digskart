package com.example.digskart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digskart.Model.PopularProductModel;
import com.example.digskart.R;

import java.util.ArrayList;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.Viewholder> {

    ArrayList<PopularProductModel> arrayList;
    Context context;

    public PopularProductAdapter(ArrayList<PopularProductModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_product_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        PopularProductModel data = arrayList.get(position);
        String Url = data.getImageUrl();
        String name = data.getProductName();
        String categoryname = data.getCategoryName();
        String price = data.getNewPrice();
        Glide.with(holder.itemView)
                .load(Url)
                .fitCenter()
                .into(holder.image);
        holder.tittle.setText(name);
        holder.text1.setText(categoryname);
        holder.text2.setText(price);

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
