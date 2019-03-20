package gy.com.gycommon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.yarolegovich.slidingrootnav.SlidingRootNav;

import gy.com.gycommon.base.BaseRxBindingActivity;
import gy.com.gycommon.preview.drawerLayout.SlidingRootNavActivity;
import gy.com.gycommon.preview.tab.FlycoTabLayoutCommonPreActivity;
import gy.com.gycommon.preview.tab.FlycoTablayoutSegmentPreActivity;
import gy.com.gycommon.preview.tab.FlycoTablayoutSlidingActivity;

public class PreActivity extends BaseRxBindingActivity {

    Button pre_tab_common;

    @Override
    protected void init() {
        RxOnClick(findViewById(R.id.pre_tab_common));
        RxOnClick(findViewById(R.id.pre_tab_segment));
        RxOnClick(findViewById(R.id.pre_tab_sliding));
        RxOnClick(findViewById(R.id.drawerlayout_fantasy_slide));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_pre;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        pre_tab_common = findViewById(R.id.pre_tab_common);
    }

    @Override
    protected void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.pre_tab_common:
                intent.setClass(this,FlycoTabLayoutCommonPreActivity.class);
                startActivity(intent);
                break;
            case R.id.pre_tab_segment:
                intent.setClass(this,FlycoTablayoutSegmentPreActivity.class);
                startActivity(intent);
                break;
            case R.id.pre_tab_sliding:
                intent.setClass(this,FlycoTablayoutSlidingActivity.class);
                startActivity(intent);
                break;
            case R.id.drawerlayout_fantasy_slide:
                intent.setClass(this,SlidingRootNavActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onLongClick(View view) {

    }

    @Override
    protected void onHovers(MotionEvent motionEvent) {

    }
}
