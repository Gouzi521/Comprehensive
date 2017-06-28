package com.ma.pingan.comprehensive.dagger.component;

import com.ma.pingan.comprehensive.mvp.contract.SearchAuthorContract;
import com.ma.pingan.comprehensive.ui.activity.BookDetailActivity;
import com.ma.pingan.comprehensive.ui.activity.RankingActivity;
import com.ma.pingan.comprehensive.ui.activity.ReadActivity;
import com.ma.pingan.comprehensive.ui.activity.SearchAuthorActivity;
import com.ma.pingan.comprehensive.ui.activity.SubjectBookListActivity;
import com.ma.pingan.comprehensive.ui.activity.TopCategoryListActivity;

import dagger.Component;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    RankingActivity inject(RankingActivity activity);
    TopCategoryListActivity inject(TopCategoryListActivity activity);
    BookDetailActivity inject(BookDetailActivity activity);

    ReadActivity inject(ReadActivity activity);

    SearchAuthorActivity inject(SearchAuthorActivity activity);
    SubjectBookListActivity inject(SubjectBookListActivity activity);
}
