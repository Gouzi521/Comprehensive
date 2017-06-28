package com.ma.pingan.comprehensive.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.SearchDetail;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/28 0028.
 */

public class SearchAuthorAdapter extends BaseQuickAdapter<SearchDetail.SearchBooks,BaseViewHolder> {
    public SearchAuthorAdapter(int layoutResId, List<SearchDetail.SearchBooks> data) {
        super(R.layout.item_search_result_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchDetail.SearchBooks item) {
        Glide.with(mContext).
                load(Constant.IMG_BASE_URL + item.cover)
                .error(R.drawable.cover_default)
                .into((ImageView) helper.getView(R.id.ivBookCover));

        helper .setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvLatelyFollower, String.format(mContext.getString(R.string.search_result_lately_follower), item.latelyFollower))
                .setText(R.id.tvRetentionRatio, (TextUtils.isEmpty(item.retentionRatio) ? String.format(mContext.getString(R.string.search_result_retention_ratio), "0")
                        : String.format(mContext.getString(R.string.search_result_retention_ratio), item.retentionRatio)))
                .setText(R.id.tvBookListAuthor, String.format(mContext.getString(R.string.search_result_author), item.author));
    }
}
