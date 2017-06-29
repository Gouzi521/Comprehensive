package com.ma.pingan.comprehensive.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BookLists;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/29 0029.
 */

public class SubjectBookListAdapter extends BaseQuickAdapter<BookLists.BookListsBean,BaseViewHolder>{
    public SubjectBookListAdapter(int layoutResId, List<BookLists.BookListsBean> data) {
        super(R.layout.item_sub_category_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookLists.BookListsBean item) {

        Glide.with(mContext)
                .load( Constant.IMG_BASE_URL + item.cover)
                .error(R.drawable.cover_default)
                .into((ImageView) helper.getView(R.id.ivSubCateCover));

        helper.setText(R.id.tvSubCateTitle, item.title)
                .setText(R.id.tvSubCateAuthor, item.author)
                .setText(R.id.tvSubCateShort, item.desc)
                .setText(R.id.tvSubCateMsg, String.format(mContext.getResources().getString(R.string.subject_book_msg), item.bookCount, item.collectorCount));


    }
}
