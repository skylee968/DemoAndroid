package com.onetech.mobilereader.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.onetech.mobilereader.R;
import com.onetech.mobilereader.configs.AppConfig;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.entity.CategoryEntity;
import com.onetech.mobilereader.customviews.ActionBarView;
import com.onetech.mobilereader.entity.UserEntity;
import com.onetech.mobilereader.uitls.BookUtils;

/**
 * Created by thienlm on 7/30/2015.
 */
public abstract class BaseActivity extends FragmentActivity implements ActionBarView.ActionBarListner{
    private FrameLayout mMainView;
    private ActionBarView mActionBar;

    private BookEntity mCurrnentBook;
    private CategoryEntity mCategory;

    protected UserEntity mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
        initListener();
    }
    private void initView() {
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
        mMainView           = (FrameLayout) findViewById(R.id.main_container_base);
        mActionBar          = (ActionBarView) findViewById(R.id.actionbar_container);
    }
    private void initListener() {
        mActionBar.setListener(this);
    }
    protected void setMainContentView(int resId) {
        View view   = getLayoutInflater().inflate(resId, mMainView, false);
        mMainView.addView(view);
    }
    protected void showActionBar(boolean isShow) {
        mActionBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    public void changeNaviIcon(int resIconId) {
        mActionBar.changeNaviIcon(resIconId);
    }
    @Override
    public abstract void menuNavigationBarClicked();

    @Override
    public void menuSearchClicked() {
        onSearchCLick();
    }

    protected void onSearchCLick() {
        Intent intent = new Intent(BaseActivity.this, SearchActivity.class);
        startActivity(intent);
    }
    public BookEntity getCurrnentBook() {
        return mCurrnentBook;
    }

    public void setCurrnentBook(BookEntity mCurrnentBook) {
        this.mCurrnentBook = mCurrnentBook;
    }


    protected void getBookFromIntentData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String content = bundle.getString(AppConfig.BUNDLE_KEY.BOOK_DETAIL);
            if(content != null) {
                setCurrnentBook(BookUtils.deserializeBookFromJson(content));
            }
        }
    }
    public void startReadingActivity(String uri) {
        Intent intent=new Intent(BaseActivity.this,ReaderActivity.class);
        intent.putExtra(AppConfig.BUNDLE_KEY.EBOOK_FILE, uri);
        startActivity(intent);
    }
    protected void getCategoryFromIntentData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String content = bundle.getString(AppConfig.BUNDLE_KEY.CATEGORY);
            if(content != null) {
                setCategory(BookUtils.deserializeCategoryFromJson(content));
            }
        }
    }

    public CategoryEntity getCategory() {
        return mCategory;
    }

    public void setCategory(CategoryEntity mCategory) {
        this.mCategory = mCategory;
    }
}
