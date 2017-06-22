package com.ma.pingan.comprehensive.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ma.pingan.comprehensive.bean.Ranking;

import java.util.List;

/**
 * Created by PingAn
 * on 2017/6/20 0020
 */

public class RankingAdapter extends BaseQuickAdapter<Ranking.RankingBean.BooksBean,BaseViewHolder>{

    public RankingAdapter(int layoutResId, List<Ranking.RankingBean.BooksBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Ranking.RankingBean.BooksBean item) {

    }
}
