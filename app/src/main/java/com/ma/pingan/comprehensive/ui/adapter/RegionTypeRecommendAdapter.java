package com.ma.pingan.comprehensive.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.bilientity.RegionDetailsInfo;
import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;
import com.ma.pingan.comprehensive.utils.NumberUtil;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/29 0029.
 */

public class RegionTypeRecommendAdapter extends BaseMultiItemQuickAdapter<RegionRecommendInfo, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RegionTypeRecommendAdapter(List<RegionRecommendInfo> data) {
        super(data);

        //addItemType(RegionRecommendInfo.FOURTH, R.layout.layout_region_recommend_hot_head);
        addItemType(RegionRecommendInfo.FIRST, R.layout.layout_region_recommend_card_item);
        //addItemType(RegionRecommendInfo.SIXTH, R.layout.layout_region_recommend_new_head);
        addItemType(RegionRecommendInfo.SECOND, R.layout.layout_region_recommend_card_item);
        //addItemType(RegionRecommendInfo.FIFTH, R.layout.layout_region_recommend_dynamic_head);
        addItemType(RegionRecommendInfo.THRID, R.layout.layout_region_recommend_card_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, RegionRecommendInfo item) {
        switch (helper.getItemViewType()) {
            case RegionRecommendInfo.FIRST:
                RegionRecommendInfo.DataBean.RecommendBean recommendBean = item.getData().getRecommend().get(helper.getLayoutPosition());

                Glide.with(mContext)
                        .load(recommendBean.getCover())
                        .into((ImageView) helper.getView(R.id.item_img));

                helper.setText(R.id.item_title, recommendBean.getTitle())
                        .setText(R.id.item_play, NumberUtil.converString(recommendBean.getPlay()))
                        .setText(R.id.item_review, NumberUtil.converString(recommendBean.getDanmaku()));
                break;
            case RegionRecommendInfo.SECOND:
                RegionRecommendInfo.DataBean.NewBean newBean = item.getData().getNewX().get(helper.getLayoutPosition());
                Glide.with(mContext)
                        .load(newBean.getCover())
                        .into((ImageView) helper.getView(R.id.item_img));

                helper.setText(R.id.item_title, newBean.getTitle())
                        .setText(R.id.item_play, NumberUtil.converString(newBean.getPlay()))
                        .setText(R.id.item_review, NumberUtil.converString(newBean.getDanmaku()));

                break;


            case RegionRecommendInfo.THRID:
                RegionRecommendInfo.DataBean.DynamicBean dynamicBean = item.getData().getDynamic().get(helper.getLayoutPosition());
                Glide.with(mContext)
                        .load(dynamicBean.getCover())
                        .into((ImageView) helper.getView(R.id.item_img));

                helper.setText(R.id.item_title, dynamicBean.getTitle())
                        .setText(R.id.item_play, NumberUtil.converString(dynamicBean.getPlay()))
                        .setText(R.id.item_review, NumberUtil.converString(dynamicBean.getDanmaku()));

                break;
            default:
                break;
        }
    }


}
