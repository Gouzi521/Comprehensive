package com.ma.pingan.comprehensive.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.bean.BooksByTag;

import java.util.List;

/**
 * Created by PingAn
 * on 2017/6/27 0027
 */

public class SearchByAuthorAdapter extends BaseQuickAdapter<BooksByTag.TagBook,BaseViewHolder> {
    public SearchByAuthorAdapter(int layoutResId, List<BooksByTag.TagBook> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BooksByTag.TagBook item) {

    }
}
