package gy.com.gycommon.activity.recyclerview.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gy.com.gycommon.R;
import gy.com.gycommon.activity.recyclerview.data.DataServer;
import gy.com.gycommon.activity.recyclerview.entiry.Status;

public class AnimationAdapter extends BaseQuickAdapter<Status,BaseViewHolder> {

    public AnimationAdapter(){
        super(R.layout.recycler_item_layout_animation,DataServer.getSampleData(100));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {

    }
}
