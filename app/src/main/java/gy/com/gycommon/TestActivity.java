package gy.com.gycommon;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import gy.com.gycommon.activity.TabFragmentActivity;
import gy.com.gycommon.activity.ViewPagerFragmentActivity;
import gy.com.gycommon.base.BaseActivity;
import gy.com.gycommon.base.BasePermissionActivity;
import gy.com.gycommon.base.BaseRxBindingActivity;
import gy.com.gycommon.constant.TransitionMode;
import gy.com.gycommon.permission.EasyPermissions;

public class TestActivity extends BaseRxBindingActivity {

    @Override
    protected void init() {

    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.BOTTOM;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
           RxOnClick(findViewById(R.id.btn_tab_fragment));
           RxOnClick(findViewById(R.id.btn_tab_viewpager_fragment));
    }

    @Override
    protected boolean isLoadDefaultTitleBar() {
        return true;
    }

    @Override
    protected void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_tab_fragment:
                intent.setClass(this,TabFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_tab_viewpager_fragment:
                intent.setClass(this,ViewPagerFragmentActivity.class);
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
