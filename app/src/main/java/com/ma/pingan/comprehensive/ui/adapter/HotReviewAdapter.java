package com.ma.pingan.comprehensive.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.HotReview;
import com.ma.pingan.comprehensive.widget.XLHRatingBar;

import java.util.List;

/**
 * Created by PingAn
 * on 2017/6/22 0022
 */

public class HotReviewAdapter extends BaseQuickAdapter<HotReview.Reviews,BaseViewHolder> {
    public HotReviewAdapter(int layoutResId, List<HotReview.Reviews> data) {
        super(R.layout.item_book_detai_hot_review_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotReview.Reviews item) {
        helper.setText(R.id.tvBookTitle, item.author.nickname)
                .setText(R.id.tvBookType, String.format(mContext.getString(R.string
                        .book_detail_user_lv), item.author.lv))
                .setText(R.id.tvTitle, item.title)
                .setText(R.id.tvContent, String.valueOf(item.content))
                .setText(R.id.tvHelpfulYes, String.valueOf(item.helpful.yes));

        //setCircleImageUrl(R.id.ivBookCover, Constant.IMG_BASE_URL + item.author.avatar, R.drawable.avatar_default)
        Glide.with(mContext)
                .load(Constant.IMG_BASE_URL + item.author.avatar)
                .error(R.drawable.avatar_default)
                .placeholder(R.drawable.avatar_default)
                .into((ImageView) helper.getView(R.id.ivBookCover));
        XLHRatingBar ratingBar = helper.getView(R.id.rating);
        ratingBar.setCountSelected(item.rating);

    }
}
