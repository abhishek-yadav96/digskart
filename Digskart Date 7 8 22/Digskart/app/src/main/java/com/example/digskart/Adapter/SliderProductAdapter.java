package com.example.digskart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.digskart.Model.SliderData;
import com.example.digskart.Model.SliderData_Product;
import com.example.digskart.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderProductAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private final List<SliderData_Product> mSliderItems;
    private Context context;

    public SliderProductAdapter(Context context, ArrayList<SliderData_Product> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
        this.context = context;
    }

    @Override
    public SliderAdapter.SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapter.SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.SliderAdapterViewHolder viewHolder, final int position) {

        final SliderData_Product sliderItem = mSliderItems.get(position);
        String url = sliderItem.getImgUrl();
        Glide.with(viewHolder.itemView)
                .load(url)
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }
}
