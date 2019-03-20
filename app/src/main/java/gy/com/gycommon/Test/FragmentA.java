package gy.com.gycommon.Test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gy.com.gycommon.R;

public class FragmentA extends Fragment {


    private TextView tv_index;

    public int index;
    public static FragmentA instance(int index){
        FragmentA fragmentA = new FragmentA();
        fragmentA.index = index;
        return fragmentA;
    }

    public FragmentA(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        tv_index = view.findViewById(R.id.tv_index);
        tv_index.setText("这是第"+index+"个");
        return view;
    }
}
