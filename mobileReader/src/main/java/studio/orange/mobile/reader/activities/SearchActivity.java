package studio.orange.mobile.reader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.adapters.ListViewBookAdapter;
import studio.orange.mobile.reader.configs.AppConfig;
import studio.orange.mobile.reader.customviews.LoadingAndNotFoundView;
import studio.orange.mobile.reader.customviews.SearchBarView;
import studio.orange.mobile.reader.entity.BookEntity;
import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entityresult.BookResultEntity;
import studio.orange.mobile.reader.models.CommonModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by thienlm on 8/16/2015.
 */
public class SearchActivity extends BaseActivity implements SearchBarView.OnSearchBarListner, AdapterView.OnItemClickListener{
    private SearchBarView mSearchBar;
    protected RequestEntity mRequest;
    protected ListViewBookAdapter mAdapter;
    protected ListView mListView;
    private LoadingAndNotFoundView mLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showActionBar(false);
        setMainContentView(R.layout.activity_search);
        initView();
        initListener();
    }
    private void initView() {
        mSearchBar          = (SearchBarView) findViewById(R.id.search_bar);
        mListView           = (ListView) findViewById(R.id.list_view_search_result);
        mLoadingView        = (LoadingAndNotFoundView) findViewById(R.id.search_loading_container);
        mAdapter 			= new ListViewBookAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setVisibility(View.GONE);
        mLoadingView.switchView(false,  true);
        mLoadingView.setRetryButtonVisibility(false);
        mLoadingView.setNotFoundMessage(getString(R.string.common_txt_search_book_center));
    }

    private void initListener() {
        mSearchBar.setOnSearchBarListner(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void menuNavigationBarClicked() {
    }

    @Override
    protected void onSearchCLick() {

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        BookEntity book  = (BookEntity) mAdapter.getItem(i);
        if(book != null) {
            Gson gson	  	= new Gson();
            String content 	= gson.toJson(book);
            Intent intent 	= new Intent(SearchActivity.this, BookDetailActivity.class);
            intent.putExtra(AppConfig.BUNDLE_KEY.BOOK_DETAIL, content);
            startActivity(intent);
        }
    }
    @Override
    public void menuNavigationSearchBarClicked() {
        onBackPressed();
    }

    @Override
    public void menuSearchBtnClicked(String searchKey) {
//        CommonUtils.showToast(searchKey);
        mLoadingView.switchView(true, false);
        mListView.setVisibility(View.GONE);
        CommonModel.getInstance().searchBookName(searchKey, new Callback<BookResultEntity>() {
            @Override
            public void success(BookResultEntity bookResultEntity, Response response) {
                if(bookResultEntity != null && bookResultEntity.data != null && bookResultEntity.data.size() > 0) {
                    mAdapter.updateListData(bookResultEntity.data);
                    mListView.setVisibility(View.VISIBLE);
                    mLoadingView.switchView(false, false);
                } else {
                    mListView.setVisibility(View.GONE);
                    mLoadingView.switchView(false, true);
                }
            }
            @Override
            public void failure(RetrofitError error) {
                mListView.setVisibility(View.GONE);
                mLoadingView.switchView(false, true);
                Log.e("SEARCH ERROR:", error.getMessage());
            }
        });
    }
}
