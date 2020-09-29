package com.example.testlauncher;

import android.graphics.drawable.Drawable;

public class Item {
    public Item(String pmName, String pmPackageNam, Drawable icon) {
        this.pmName = pmName;
        this.pmPackageNam = pmPackageNam;
        this.icon = icon;
    }

    private String pmName="";
    private String pmPackageNam="";
    private Drawable icon;

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmPackageNam() {
        return pmPackageNam;
    }

    public void setPmPackageNam(String pmPackageNam) {
        this.pmPackageNam = pmPackageNam;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
