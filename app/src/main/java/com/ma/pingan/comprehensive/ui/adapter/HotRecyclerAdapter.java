package com.ma.pingan.comprehensive.ui.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;
import com.ma.pingan.comprehensive.utils.NumberUtil;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/30 0030.
 */

public class HotRecyclerAdapter extends BaseQuickAdapter<RegionRecommendInfo.DataBean.RecommendBean, BaseViewHolder> {
    public HotRecyclerAdapter(int layoutResId, List<RegionRecommendInfo.DataBean.RecommendBean> data) {
        super(R.layout.layout_region_recommend_card_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RegionRecommendInfo.DataBean.RecommendBean item) {

        Glide.with(mContext)
                .load(item.getCover())
                .into((ImageView) helper.getView(R.id.item_img));

        helper.setText(R.id.item_title, item.getTitle())
                .setText(R.id.item_play, NumberUtil.converString(item.getPlay()))
                .setText(R.id.item_review, NumberUtil.converString(item.getDanmaku()));



    }
}
