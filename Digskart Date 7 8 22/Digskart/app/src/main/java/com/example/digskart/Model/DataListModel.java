package com.example.digskart.Model;

import java.util.ArrayList;

public class DataListModel {
    String demo;
    ArrayList<DataModelChild> childList;

    public DataListModel(String demo, ArrayList<DataModelChild> childList) {
        this.demo = demo;
        this.childList = childList;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public ArrayList<DataModelChild> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<DataModelChild> childList) {
        this.childList = childList;
    }
}
