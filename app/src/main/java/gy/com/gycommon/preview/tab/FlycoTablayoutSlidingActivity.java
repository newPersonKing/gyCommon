package gy.com.gycommon.preview.tab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;

import gy.com.gycommon.R;
import gy.com.gycommon.Test.FragmentA;
import gy.com.gycommon.base.BaseActivity;

public class FlycoTablayoutSlidingActivity extends BaseActivity implements OnTabSelectListener {

    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private MyPagerAdapter mAdapter;

    @Override
    protected void init() {

        for (int i=0;i<mTitles.length;i++){
            mFragments.add(FragmentA.instance(i));
        }

        ViewPager vp = findViewById(R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);

        /** 默认 */
        SlidingTabLayout tabLayout_1 = findViewById(R.id.tl_1);
        /**自定义部分属性*/
        SlidingTabLayout tabLayout_2 = findViewById(R.id.tl_2);
        /** 字体加粗,大写 */
        SlidingTabLayout tabLayout_3 = findViewById(R.id.tl_3);
        /** tab固定宽度 */
        SlidingTabLayout tabLayout_4 = findViewById(R.id.tl_4);
        /** indicator固定宽度 */
        SlidingTabLayout tabLayout_5 = findViewById(R.id.tl_5);
        /** indicator圆 */
        SlidingTabLayout tabLayout_6 = findViewById(R.id.tl_6);
        /** indicator矩形圆角 */
        final SlidingTabLayout tabLayout_7 = findViewById(R.id.tl_7);
        /** indicator三角形 */
        SlidingTabLayout tabLayout_8 = findViewById(R.id.tl_8);
        /** indicator圆角色块 */
        SlidingTabLayout tabLayout_9 = findViewById(R.id.tl_9);
        /** indicator圆角色块 */
        SlidingTabLayout tabLayout_10 = findViewById(R.id.tl_10);

        tabLayout_1.setViewPager(vp);
        tabLayout_2.setViewPager(vp);
        tabLayout_2.setOnTabSelectListener(this);
        tabLayout_3.setViewPager(vp);
        tabLayout_4.setViewPager(vp);
        tabLayout_5.setViewPager(vp);
        tabLayout_6.setViewPager(vp);
        tabLayout_7.setViewPager(vp, mTitles);
        tabLayout_8.setViewPager(vp, mTitles, this, mFragments);
        tabLayout_9.setViewPager(vp);
        tabLayout_10.setViewPager(vp);

        vp.setCurrentItem(4);

        tabLayout_1.showDot(4);
        tabLayout_3.showDot(4);
        tabLayout_2.showDot(4);

        tabLayout_2.showMsg(3, 5);
        tabLayout_2.setMsgMargin(3, 0, 10);
        MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }

        tabLayout_2.showMsg(5, 5);
        tabLayout_2.setMsgMargin(5, 0, 10);
        
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.pre_flycotablayout_sliding_activity;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }


    @Override
    public void onTabSelect(int position) {
        Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
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
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
