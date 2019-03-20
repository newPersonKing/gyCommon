package gy.com.gycommon.preview.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;

import gy.com.gycommon.R;
import gy.com.gycommon.Test.FragmentA;
import gy.com.gycommon.base.BaseActivity;

public class FlycoTablayoutSegmentPreActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

    private String[] mTitles = {"首页", "消息"};
    private String[] mTitles_2 = {"首页", "消息", "联系人"};
    private String[] mTitles_3 = {"首页", "消息", "联系人", "更多"};
    private SegmentTabLayout mTabLayout_3;

    @Override
    protected void init() {
        
        for (int i=0;i<mTitles_3.length;i++){
            mFragments.add(FragmentA.instance(i));
            mFragments2.add(FragmentA.instance(i));
        }
        
        SegmentTabLayout tabLayout_1 = findViewById( R.id.tl_1);
        SegmentTabLayout tabLayout_2 = findViewById( R.id.tl_2);
        mTabLayout_3 = findViewById( R.id.tl_3);
        SegmentTabLayout tabLayout_4 = findViewById( R.id.tl_4);
        SegmentTabLayout tabLayout_5 = findViewById( R.id.tl_5);

        tabLayout_1.setTabData(mTitles);
        tabLayout_2.setTabData(mTitles_2);
        tl_3();
        tabLayout_4.setTabData(mTitles_2, this, R.id.fl_change, mFragments2);
        tabLayout_5.setTabData(mTitles_3);

        //显示未读红点
        tabLayout_1.showDot(2);
        tabLayout_2.showDot(2);
        mTabLayout_3.showDot(1);
        tabLayout_4.showDot(1);

        //设置未读消息红点
        mTabLayout_3.showDot(2);
        MsgView rtv_3_2 = mTabLayout_3.getMsgView(2);
        if (rtv_3_2 != null) {
            rtv_3_2.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.pre_flycotablayout_segment_activity;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    private void tl_3() {
        final ViewPager vp_3 = findViewById(R.id.vp_2);
        vp_3.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mTabLayout_3.setTabData(mTitles_3);
        mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp_3.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vp_3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_3.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_3.setCurrentItem(1);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles_3[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
