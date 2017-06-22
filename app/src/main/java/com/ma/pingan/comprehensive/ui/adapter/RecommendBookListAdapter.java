package com.ma.pingan.comprehensive.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.RecommendBookList;

import java.util.List;

/**
 * Created by PingAn
 * on 2017/6/22 0022
 */

public class RecommendBookListAdapter extends BaseQuickAdapter<RecommendBookList.RecommendBook,BaseViewHolder>{
    public RecommendBookListAdapter(int layoutResId, List<RecommendBookList.RecommendBook> data) {
        super(R.layout.item_book_detail_recommend_book_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBookList.RecommendBook item) {

        Glide.with(mContext)
                .load(Constant.IMG_BASE_URL + item.cover)
                .placeholder(R.drawable.cover_default)
                .error(R.drawable.cover_default)
                .into((ImageView) helper.getView(R.id.ivBookListCover));


        helper.setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvBookAuthor, item.author)
                .setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvBookListDesc, item.desc)
                .setText(R.id.tvBookCount, String.format(mContext.getString(R.string
                        .book_detail_recommend_book_list_book_count), item.bookCount))
                .setText(R.id.tvCollectorCount, String.format(mContext.getString(R.string
                        .book_detail_recommend_book_list_collector_count), item.collectorCount));
    }
}
