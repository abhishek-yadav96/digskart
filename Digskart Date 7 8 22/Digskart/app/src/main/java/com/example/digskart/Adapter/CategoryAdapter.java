package com.example.digskart.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digskart.Model.CategoryModel;
import com.example.digskart.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {

    private Context context;
    ArrayList<CategoryModel> arrayList;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.categorylist,parent,false);
       return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        CategoryModel data = arrayList.get(position);
        String image = data.getCategoryImage();
        String subtittle = data.getCategorySubtitle();
        Log.e("TEXT","TEXT"+subtittle);
        Glide.with(holder.itemView)
                .load(image)
                .fitCenter()
                .into(holder.imageView2);
        holder.text1.setText(subtittle);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        ImageView imageView2;
        TextView text1;
        public Viewholder(@NonNull View itemview){
            super(itemview);
            imageView2 = itemview.findViewById(R.id.imageView2);
            text1 = itemview.findViewById(R.id.text1);
        }
    }
}
