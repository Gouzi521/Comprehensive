package com.ma.pingan.comprehensive.dagger.component;

import com.ma.pingan.comprehensive.MainActivity;
import com.ma.pingan.comprehensive.ui.activity.BookDetailActivity;
import com.ma.pingan.comprehensive.ui.activity.RankingActivity;
import com.ma.pingan.comprehensive.ui.activity.SearchByAuthorActivity;
import com.ma.pingan.comprehensive.ui.activity.TopCategoryListActivity;

import dagger.Component;

/**
 * Created by mapingan
 * on 2017/6/20 0020.
 */
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    MainActivity inject(MainActivity activity);
    RankingActivity inject(RankingActivity activity);
    TopCategoryListActivity inject(TopCategoryListActivity activity);
    BookDetailActivity inject(BookDetailActivity activity);
    SearchByAuthorActivity  inject(SearchByAuthorActivity activity);
}
