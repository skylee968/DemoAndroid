package com.onetech.mobilereader.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;

public class OTFragmentBase extends BaseFragment implements OnRefreshListener{
	protected View mMainView	 					= null;
	//protected SwipeRefreshLayout mSwipRefreshLayout = null;

//	protected void initSwipeOptions() {
//        mSwipRefreshLayout.setOnRefreshListener(this);
//        setAppearance();
//        //showSwipeProgress();
//        //disableSwipe();
//    }
//
//    private void setAppearance() {
//        mSwipRefreshLayout.setColorSchemeColors(android.R.color.holo_blue_bright,
//        android.R.color.holo_green_light,
//        android.R.color.holo_orange_light,
//        android.R.color.holo_red_light);
//    }
//
//    public void showSwipeProgress() {
//        mSwipRefreshLayout.setRefreshing(true);
//    }
//    public void hideSwipeProgress() {
//        mSwipRefreshLayout.setRefreshing(false);
//    }
//    public void enableSwipe() {
//        mSwipRefreshLayout.setEnabled(true);
//    }
//    public void disableSwipe() {
//        mSwipRefreshLayout.setEnabled(false);
//    }
    @Override 
    public void onRefresh() {
    }
}
