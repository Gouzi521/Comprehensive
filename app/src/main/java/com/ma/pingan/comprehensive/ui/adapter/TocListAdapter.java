package com.ma.pingan.comprehensive.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.bean.BookMixAToc;
import com.ma.pingan.comprehensive.utils.FileUtils;

import java.util.List;

/**
 * Created by mapingan
 * on 2017/6/27 0027.
 */

public class TocListAdapter extends BaseQuickAdapter<BookMixAToc.mixToc.Chapters,BaseViewHolder> {

    private int currentChapter;
    private String bookId;

    private boolean isEpub = false;

    public TocListAdapter(int layoutResId, List<BookMixAToc.mixToc.Chapters> data,String bookId, int currentChapter) {
        super(R.layout.item_book_read_toc_list, data);
        this.currentChapter = currentChapter;
        this.bookId = bookId;
    }

    @Override
    protected void convert(BaseViewHolder helper, BookMixAToc.mixToc.Chapters chapters) {

        TextView tvTocItem = helper.getView(R.id.tvTocItem);
        tvTocItem.setText(chapters.title);
        Drawable drawable;

        if (currentChapter == helper.getLayoutPosition() + 1) {
            tvTocItem.setTextColor(ContextCompat.getColor(mContext, R.color.light_red));
            drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_toc_item_activated);
        } else if (isEpub || FileUtils.getChapterFile(bookId, helper.getLayoutPosition() + 1).length() > 10) {
            tvTocItem.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
            drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_toc_item_download);
        } else {
            tvTocItem.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
            drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_toc_item_normal);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvTocItem.setCompoundDrawables(drawable, null, null, null);
    }

    public void setCurrentChapter(int chapter) {
        currentChapter = chapter;
        notifyDataSetChanged();
    }

    public void setEpub(boolean isEpub) {
        this.isEpub = isEpub;
    }
}
