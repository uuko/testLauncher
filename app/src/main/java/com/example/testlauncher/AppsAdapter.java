package com.example.testlauncher;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AppsAdapter extends BaseAdapter {
    private List<Item> apps;
    private  Context context;
    int cellHeight;
    //构造器中传入app列表信息
    public AppsAdapter(List<Item> apps,Context context,int cellHeight){
        this.apps = apps;
        this.context=context;
        this.cellHeight = cellHeight;
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int i) {
        return apps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }




    @Override
    public View getView(int i, View view, ViewGroup parent) {

        View v;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.icon_layout, parent, false);
        }else{
            v = view;
        }

        LinearLayout mLayout = v.findViewById(R.id.layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, cellHeight);
        mLayout.setLayoutParams(lp);

        ImageView iv = (ImageView) v.findViewById(R.id.action_image);
        //设置图标适应方式和大小
//        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        iv.setLayoutParams(new LinearLayout.LayoutParams(70, 70));
        TextView tv = (TextView) v.findViewById(R.id.name);

        final Item info = apps.get(i);

        //显示app图标
        iv.setImageDrawable(info.getIcon());
        //显示app名
        String appName = info.getPmName();
        tv.setText(appName);

        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).itemPress(info);
            }
        });
        return v;
    }
}