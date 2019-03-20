package gy.com.gycommon.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;
import gy.com.gycommon.R;
import gy.com.gycommon.Test.FragmentA;
import gy.com.gycommon.adapter.ViewPagerAdapter;
import gy.com.gycommon.base.BaseActivity;

/*viewpager 切换 Fragment 的 界面*/
public class ViewPagerFragmentActivity extends BaseActivity {

    private ViewPager viewpager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<Fragment> fragments;
    private SpaceTabLayout spaceTabLayout;
    @Override
    protected void init() {
        viewPagerAdapter = new ViewPagerAdapter();
        fragments = new ArrayList<>();
        for(int i=0;i<4;i++){
            fragments.add(FragmentA.instance(i));
        }
        viewPagerAdapter.setFragments(fragments);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_viewpager_fragment;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        viewpager = findViewById(R.id.viewpager);
        spaceTabLayout = findViewById(R.id.spaceTabLayout);
        spaceTabLayout.initialize(viewpager,getSupportFragmentManager(),fragments);

        spaceTabLayout.setTabOneOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "==" + spaceTabLayout.getCurrentPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        spaceTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "" + spaceTabLayout.getCurrentPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
