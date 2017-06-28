package com.ma.pingan.comprehensive.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.bean.FindBean;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/28 0028.
 */

public class FindAdapter extends BaseQuickAdapter<FindBean,BaseViewHolder> {
    public FindAdapter(int layoutResId, List<FindBean> data) {
        super(R.layout.item_find, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindBean item) {
        Glide.with(mContext)
                .load(item.getIconResId())
                .into((ImageView) helper.getView(R.id.ivIcon));
        helper.setText(R.id.tvTitle, item.getTitle());
    }
}
