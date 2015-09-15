package com.onetech.mobilereader.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.onetech.mobilereader.R;
import com.onetech.mobilereader.activities.BookDetailActivity;
import com.onetech.mobilereader.adapters.GridViewBookAdapter;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.entity.RequestEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class OTFragmentBookShelfGridViewBase extends OTFragmentBase implements AdapterView.OnItemClickListener{
	protected RequestEntity mRequest;
	protected GridViewBookAdapter mAdapter;
	protected GridView mGridView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(mMainView == null) {
			mMainView = inflater.inflate(R.layout.fragment_book_shelf_grid, container, false);
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
		mGridView			= (GridView) mMainView.findViewById(R.id.gridview_book);
		mAdapter 			= new GridViewBookAdapter(getActivity());
		mGridView.setAdapter(mAdapter);
	}
	private void initListener() {
//		initSwipeOptions();
		mGridView.setOnItemClickListener(this);
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

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Intent intent = new Intent(getActivity(), BookDetailActivity.class);
		startActivity(intent);
	}


}
