package com.ma.pingan.comprehensive.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.bilientity.RegionDetailsInfo;
import com.ma.pingan.comprehensive.bilientity.RegionRecommendInfo;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/29 0029.
 */

public class RegionTypeRecommendAdapter extends BaseMultiItemQuickAdapter<RegionRecommendInfo,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RegionTypeRecommendAdapter(List<RegionRecommendInfo> data) {
        super(data);

        addItemType(RegionRecommendInfo.FIRST, R.layout.item_types_icon);
    }



    @Override
    protected void convert(BaseViewHolder helper, RegionRecommendInfo item) {
        switch (helper.getItemViewType()){
            case RegionRecommendInfo.FIRST:
                break;
            case RegionRecommendInfo.SECOND:
                break;
            case RegionRecommendInfo.THRID:
                break;
            case RegionRecommendInfo.FOURTH:
                break;
            default:
                break;
    }
}
