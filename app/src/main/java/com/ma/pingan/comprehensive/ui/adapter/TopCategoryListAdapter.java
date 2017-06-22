package com.ma.pingan.comprehensive.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.bean.CategoryList;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/22 0022.
 */

public class TopCategoryListAdapter extends BaseQuickAdapter<CategoryList.MaleBean,BaseViewHolder> {
    public TopCategoryListAdapter(int layoutResId, List<CategoryList.MaleBean> data) {
        super(R.layout.item_top_category_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryList.MaleBean item) {
        helper.setText(R.id.tvName, item.name)
                .setText(R.id.tvBookCount, String.format(mContext.getString(R.string
                        .category_book_count), item.bookCount));


    }
}
