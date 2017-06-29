package com.ma.pingan.comprehensive.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BookListDetail;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/29 0029.
 */

public class SubjectBookListDetailBooksAdapter extends BaseQuickAdapter<BookListDetail.BookListBean.BooksBean,BaseViewHolder> {
    public SubjectBookListDetailBooksAdapter(int layoutResId, List<BookListDetail.BookListBean.BooksBean> data) {
        super(R.layout.item_subject_book_list_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookListDetail.BookListBean.BooksBean item) {

        Glide.with(mContext)
                .load( Constant.IMG_BASE_URL + item.getBook().getCover())
                .into((ImageView) helper.getView(R.id.ivBookCover));

        helper.setText(R.id.tvBookListTitle, item.getBook().getTitle())
                .setText(R.id.tvBookAuthor, item.getBook().getAuthor())
                .setText(R.id.tvBookLatelyFollower, String.format(mContext.getResources().getString(R.string.subject_book_list_detail_book_lately_follower),
                        item.getBook().getLatelyFollower()))
                .setText(R.id.tvBookWordCount, String.format(mContext.getResources().getString(R.string.subject_book_list_detail_book_word_count),
                        item.getBook().getWordCount() / 10000))
                .setText(R.id.tvBookDetail, item.getBook().getLongIntro());
    }
}
