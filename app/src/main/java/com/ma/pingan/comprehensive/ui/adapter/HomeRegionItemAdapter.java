package com.ma.pingan.comprehensive.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.bilientity.IconInfo;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/29 0029.
 */

public class HomeRegionItemAdapter extends BaseQuickAdapter<IconInfo,BaseViewHolder> {


    public HomeRegionItemAdapter(int layoutResId, List<IconInfo> data) {
        super(R.layout.item_home_region, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IconInfo item) {
        helper.setText(R.id.item_title,item.getName());
        ImageView image = helper.getView(R.id.item_icon);
        image.setImageResource(item.getIcon());


    }
}
