package com.ma.pingan.comprehensive.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ma.pingan.comprehensive.R;
import com.ma.pingan.comprehensive.base.BaseActivity;
import com.ma.pingan.comprehensive.base.Constant;
import com.ma.pingan.comprehensive.bean.BookListDetail;
import com.ma.pingan.comprehensive.bean.BookLists;
import com.ma.pingan.comprehensive.dagger.component.AppComponent;
import com.ma.pingan.comprehensive.dagger.component.DaggerActivityComponent;
import com.ma.pingan.comprehensive.mvp.contract.SubjectBookListDetailContract;
import com.ma.pingan.comprehensive.mvp.presenter.SubjectBookListDetailPresenter;
import com.ma.pingan.comprehensive.ui.adapter.SubjectBookListDetailBooksAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectBookListDetailActivity extends BaseActivity implements SubjectBookListDetailContract.View, BaseQuickAdapter.OnItemClickListener {

    public static final String INTENT_BEAN = "bookListsBean";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private TextView tvBookListTitle;
    private TextView  tvBookListAuthor;
    private TextView  tvBookListDesc;
    private TextView  btnShare;
    private ImageView ivAuthorAvatar;


    public static void startActivity(Context context, BookLists.BookListsBean bookListsBean) {
        context.startActivity(new Intent(context, SubjectBookListDetailActivity.class)
                .putExtra(INTENT_BEAN, bookListsBean));
    }

    @Inject
    SubjectBookListDetailPresenter mPresenter;
    private SubjectBookListDetailBooksAdapter adapter;
    private List<BookListDetail.BookListBean.BooksBean> mAllBooks = new ArrayList<>();

    private BookLists.BookListsBean bookListsBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subject_book_list_detail;
    }

    @Override
    protected void initToolBar() {
        mCommonToolbar.setTitle(R.string.subject_book_list_detail);
        mCommonToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        bookListsBean = (BookLists.BookListsBean) getIntent().getSerializableExtra(INTENT_BEAN);
    }

    @Override
    protected void configViews() {
        adapter = new SubjectBookListDetailBooksAdapter(R.layout.item_subject_book_list_detail, mAllBooks);
        View headerView = LayoutInflater.from(SubjectBookListDetailActivity.this).inflate(R.layout.header_view_book_list_detail, null);
        tvBookListTitle = ((TextView) headerView.findViewById(R.id.tvBookListTitle));
        tvBookListAuthor = ((TextView) headerView.findViewById(R.id.tvBookListAuthor));
        tvBookListDesc = ((TextView) headerView.findViewById(R.id.tvBookListDesc));
        btnShare = ((TextView) headerView.findViewById(R.id.btnShare));
        ivAuthorAvatar= (ImageView) headerView.findViewById(R.id.ivAuthorAvatar);
        adapter.addHeaderView(headerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


        recyclerview.setAdapter(adapter);
        mPresenter.attachView(this);
        mPresenter.getBookListDetail(bookListsBean._id);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showBookListDetail(BookListDetail data) {
        tvBookListTitle.setText(data.getBookList().getTitle());
        tvBookListDesc.setText(data.getBookList().getDesc());
        tvBookListAuthor.setText(data.getBookList().getAuthor().getNickname());
        Glide.with(this)
                .load(Constant.IMG_BASE_URL + data.getBookList().getAuthor().getAvatar())
                .placeholder(R.drawable.avatar_default)
                .into(ivAuthorAvatar);

        List<BookListDetail.BookListBean.BooksBean> list = data.getBookList().getBooks();
        mAllBooks.clear();
        mAllBooks.addAll(list);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BookDetailActivity.startActivity(this, mAllBooks.get(position).getBook().get_id());
    }
}
