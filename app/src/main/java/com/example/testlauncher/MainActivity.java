package com.example.testlauncher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int cellHeight;
    int DRAWER_PEEK_HEIGHT = 100;
    int numRow = 4, numColumn = 4;
    private  List<Item> ll=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final LinearLayout mTopDrawerLayout = findViewById(R.id.topDrawerLayout);
//        mTopDrawerLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                DRAWER_PEEK_HEIGHT = mTopDrawerLayout.getHeight();
//                initializeHome();
////                initializeDrawer();
//            }
//        });
        getSupportActionBar().hide();
        getApps();
//        cellHeight = (getDisplayContentHeight() - DRAWER_PEEK_HEIGHT) / numRow ;
//        GridView appsGrid =  findViewById(R.id.drawerGrid);
//        appsGrid.setAdapter(new AppsAdapter(ll,this,cellHeight));
//        appsGrid.setOnItemClickListener(clickListener);
        initializeHome();
    }
    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    private void initializeHome() {
        ArrayList<PagerObject> pagerAppList = new ArrayList<>();
        //5 11 3 1
        int pageSize=ll.size()/(numRow*numColumn);
        int remainSize=ll.size() % (numRow*numColumn);
        ArrayList<Item> appList=new ArrayList<>();
        for (int i=0;i<pageSize-1;i++){
            appList = new ArrayList<>();
            for (int j = numColumn*numRow*(i); j < numColumn*numRow*(i+1) ;j++){
                appList.add(new Item(ll.get(j).getPmName(),
                        ll.get(j).getPmPackageNam(),
                        ll.get(j).getIcon()
                ));
            }
            pagerAppList.add(new PagerObject(appList));
        }
        for (int j = numColumn*numRow*(pageSize-1); j < numColumn*numRow*(pageSize-1)+remainSize ;j++){
            appList = new ArrayList<>();
            appList.add(new Item(ll.get(j).getPmName(),
                    ll.get(j).getPmPackageNam(),
                    ll.get(j).getIcon()
            ));
        }
        pagerAppList.add(new PagerObject(appList));

//        ArrayList<Item> appList1 = new ArrayList<>();
//        ArrayList<Item> appList2 = new ArrayList<>();
//        ArrayList<Item> appList3 = new ArrayList<>();
//        for (int i = 0; i < numColumn*numRow ;i++)
//            appList1.add(new Item(ll.get(i).getPmName(),
//                    ll.get(i).getPmPackageNam(),
//                   ll.get(i).getIcon()
//            ));
//        for (int i = numColumn*numRow; i < numColumn*numRow*2 ;i++)
//            appList2.add(new Item(ll.get(i).getPmName(),
//                    ll.get(i).getPmPackageNam(),
//                    ll.get(i).getIcon()
//            ));
//        for (int i = numColumn*numRow*2; i < numColumn*numRow*3 ;i++)
//            appList3.add(new Item(ll.get(i).getPmName(),
//                    ll.get(i).getPmPackageNam(),
//                    ll.get(i).getIcon()
//            ));
//
//        pagerAppList.add(new PagerObject(appList1));
//        pagerAppList.add(new PagerObject(appList2));
//        pagerAppList.add(new PagerObject(appList3));

        cellHeight = (getDisplayContentHeight() - DRAWER_PEEK_HEIGHT) / numRow ;

        mViewPager = findViewById(R.id.viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(this, pagerAppList, cellHeight, numColumn);
        mViewPager.setAdapter(mViewPagerAdapter);
    }
    GridView mDrawerGridView;
//    BottomSheetBehavior mBottomSheetBehavior;
//    private void initializeDrawer() {
//        View mBottomSheet =findViewById(R.id.bottomSheet);
//        mDrawerGridView = findViewById(R.id.drawerGrid);
//        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
//        mBottomSheetBehavior.setHideable(false);
//        mBottomSheetBehavior.setPeekHeight(DRAWER_PEEK_HEIGHT);
//
////        installedAppList = getInstalledAppList();
//        cellHeight = (getDisplayContentHeight() - DRAWER_PEEK_HEIGHT) / numRow ;
//        GridView appsGrid =  findViewById(R.id.drawerGrid);
//        appsGrid.setAdapter(new AppsAdapter(ll,this,cellHeight));
//        appsGrid.setOnItemClickListener(clickListener);
//        mDrawerGridView.setAdapter(new AppsAdapter(ll,this, cellHeight));
//
//        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//
//
//                if(newState == BottomSheetBehavior.STATE_COLLAPSED && mDrawerGridView.getChildAt(0).getY() != 0)
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                if(newState == BottomSheetBehavior.STATE_DRAGGING && mDrawerGridView.getChildAt(0).getY() != 0)
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//            }
//        });
//
//    }

    public void  itemPress(Item item){
        openApps(item.getPmPackageNam());
    }
    private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            openApps(ll.get(i).getPmPackageNam());
        }
    };
    private void openApps(String pmName) {
        Intent intent=getPackageManager().getLaunchIntentForPackage(pmName);
        if (intent!=null){
            startActivity(intent);
        }
    }
    private int getDisplayContentHeight() {
        final WindowManager windowManager = getWindowManager();
        final Point size = new Point();
        int screenHeight = 0, actionBarHeight = 0, statusBarHeight = 0;
        if(getActionBar()!=null){
            actionBarHeight = getActionBar().getHeight();
        }

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0){
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        int contentTop = (findViewById(android.R.id.content)).getTop();
        windowManager.getDefaultDisplay().getSize(size);
        screenHeight = size.y;
        return screenHeight - contentTop - actionBarHeight - statusBarHeight;
    }
    private void getApps() {

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> list=pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo:list){
            ll.add(new Item(pm.getApplicationLabel(applicationInfo).toString(),
                    applicationInfo.packageName,
                    pm.getApplicationIcon(applicationInfo)
                    ));
        }


    }

}
