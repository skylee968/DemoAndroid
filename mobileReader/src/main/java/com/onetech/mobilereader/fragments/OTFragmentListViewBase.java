package com.onetech.mobilereader.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.onetech.mobilereader.R;
import com.onetech.mobilereader.activities.BookDetailActivity;
import com.onetech.mobilereader.adapters.ListViewBookAdapter;
import com.onetech.mobilereader.configs.AppConfig;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entityresult.BookResultEntity;
import com.onetech.mobilereader.models.BookModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class OTFragmentListViewBase extends OTFragmentBase implements AdapterView.OnItemClickListener{
    protected RequestEntity mRequest;
    protected ListViewBookAdapter mAdapter;
    protected ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mMainView == null) {
            mMainView = inflater.inflate(R.layout.fragment_book_list, container, false);
            initView();
            initListener();
        } else {
            if(mMainView.getParent() != null) {
                ((ViewGroup) mMainView.getParent()).removeView(mMainView);
            }
        }
        return mMainView;
    }
    private void initView() {
//		mSwipRefreshLayout = (SwipeRefreshLayout) mMainView.findViewById(R.id.swipe_container);
//		mSwipRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_bright,
//				android.R.color.holo_green_light,
//	            android.R.color.holo_orange_light,
//	            android.R.color.holo_red_light);
        mListView           = (ListView) mMainView.findViewById(R.id.listview_book);
        mAdapter 			= new ListViewBookAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        initData();
    }
    private void initListener() {
//		initSwipeOptions();
        mListView.setOnItemClickListener(this);
    }
    abstract void initData();
    @Override
    public void onRefresh() {
//		new Handler().postDelayed(new Runnable() {
//	        @Override public void run() {
//	        	hideSwipeProgress();
//	        }
//	    }, 2000);
    }
    protected void loadData() {
        BookModel.getInstance().getBooks(mRequest, new Callback<BookResultEntity>() {
            @Override
            public void success(BookResultEntity bookResultEntity, Response response) {
                if (bookResultEntity != null && bookResultEntity.data != null) {
                    mAdapter.updateListData(bookResultEntity.data);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        BookEntity book  = (BookEntity) mAdapter.getItem(i);
        if(book != null) {
            Gson gson	  	= new Gson();
            String content 	= gson.toJson(book);
            Intent intent 	= new Intent(getActivity(), BookDetailActivity.class);
            intent.putExtra(AppConfig.BUNDLE_KEY.BOOK_DETAIL, content);
            startActivity(intent);
        }
    }
}

