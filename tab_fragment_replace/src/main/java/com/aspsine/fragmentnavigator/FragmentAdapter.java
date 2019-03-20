package com.aspsine.fragmentnavigator;

import android.support.v4.app.Fragment;

import java.util.List;

public class FragmentAdapter implements FragmentNavigatorAdapter {

    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments){
        this.fragments = fragments;
    }

    @Override
    public Fragment onCreateFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public String getTag(int position) {
        return ""+position;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
