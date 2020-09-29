package com.example.testlauncher;

import java.util.ArrayList;

public class PagerObject {
    private ArrayList<Item> appList;

    public PagerObject(ArrayList<Item> appList){
        this.appList = appList;
    }

    public ArrayList<Item> getAppList() {
        return appList;
    }
}

