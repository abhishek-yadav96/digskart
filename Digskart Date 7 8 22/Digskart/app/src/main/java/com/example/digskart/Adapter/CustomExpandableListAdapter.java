package com.example.digskart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.digskart.Model.DataListModel;
import com.example.digskart.R;

import java.util.ArrayList;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<DataListModel> listList;

    public CustomExpandableListAdapter(Context context, ArrayList<DataListModel> listList) {
        this.context = context;
        this.listList = listList;
    }
    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        // ArrayList<DataModelChild> childList = listList.get(listPosition).getChildList();
        return listList.get(listPosition).getChildList().get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.customer_list_items_main, null);
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return listList.get(listPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return listList.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return listList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.customer_list_item_group, null);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
