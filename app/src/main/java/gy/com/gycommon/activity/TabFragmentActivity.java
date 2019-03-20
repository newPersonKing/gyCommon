package gy.com.gycommon.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.FrameLayout;

import com.aspsine.fragmentnavigator.FragmentAdapter;
import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import gy.com.gycommon.R;
import gy.com.gycommon.Test.FragmentA;
import gy.com.gycommon.base.BaseActivity;
import gy.com.gycommon.preview.tab.TabEntity;

/*replace 切换Fragment 的页面*/
public class TabFragmentActivity extends BaseActivity implements OnTabSelectListener {


    private FragmentNavigator mNavigator;/*Fragment 管理器*/
    private FrameLayout container;/*Fragment 容器*/
    private CommonTabLayout commonTabLayout;
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentAS = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    @Override
    protected void init() {
        fragmentAdapter = new FragmentAdapter();
        for(int i=0;i< mTitles.length;i++){
            fragmentAS.add(FragmentA.instance(i));
        }
        fragmentAdapter.setFragments(fragmentAS);
        mNavigator = new FragmentNavigator(getSupportFragmentManager(),fragmentAdapter,R.id.fragment_container);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_tab_fragment;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        commonTabLayout = findViewById(R.id.tab_common);
        commonTabLayout.setOnTabSelectListener(this);
        commonTabLayout.setTabData(mTabEntities);
        mNavigator.showFragment(0);
    }

    @Override
    public void onTabSelect(int position) {
        Log.i("ccccccccccccc","onTabSelect"+position);
        mNavigator.showFragment(position);

    }

    /*多次点击同一个item回调*/
    @Override
    public void onTabReselect(int position) {
        Log.i("ccccccccccccc","onTabReselect"+position);
    }
}
