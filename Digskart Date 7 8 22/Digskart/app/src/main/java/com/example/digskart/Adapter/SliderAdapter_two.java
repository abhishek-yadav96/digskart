package com.example.digskart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.digskart.Model.SliderData;
import com.example.digskart.Model.SliderData_Two;
import com.example.digskart.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter_two extends SliderViewAdapter<SliderAdapter_two.SliderAdapterViewHolder> {

    private final List<SliderData_Two> mSliderItems;

    public SliderAdapter_two(ArrayList<SliderData_Two> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {

        final SliderData_Two sliderItem = mSliderItems.get(position);
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
