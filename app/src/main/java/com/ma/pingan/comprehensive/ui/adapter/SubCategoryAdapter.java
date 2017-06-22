package com.ma.pingan.comprehensive.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BooksByCats;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/22 0022.
 */

public class SubCategoryAdapter  extends BaseQuickAdapter<BooksByCats.BooksBean,BaseViewHolder>{
    public SubCategoryAdapter(int layoutResId, List<BooksByCats.BooksBean> data) {
        super(R.layout.item_sub_category_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BooksByCats.BooksBean item) {

        Glide.with(mContext)
                .load(Constant.IMG_BASE_URL + item.cover)
                .placeholder(R.drawable.default_img)
                .into((ImageView) helper.getView(R.id.ivSubCateCover));

        helper.setText(R.id.tvSubCateTitle, item.title)
                .setText(R.id.tvSubCateAuthor, (item.author == null ? "未知" : item.author) + " | " + (item.majorCate == null ? "未知" : item.majorCate))
                .setText(R.id.tvSubCateShort, item.shortIntro)
                .setText(R.id.tvSubCateMsg, String.format(mContext.getResources().getString(R.string.category_book_msg),
                        item.latelyFollower,
                        TextUtils.isEmpty(item.retentionRatio) ? "0" : item.retentionRatio));

    }
}
