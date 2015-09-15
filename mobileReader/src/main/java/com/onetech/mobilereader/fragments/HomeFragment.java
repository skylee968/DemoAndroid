package com.onetech.mobilereader.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.onetech.mobilereader.R;
import com.onetech.mobilereader.activities.HomeActivity;
import com.onetech.mobilereader.uitls.ColorsUtil;

/**
 * Created by thienlm on 8/2/2015.
 */
public class HomeFragment extends BaseFragment {
    private PagerSlidingTabStrip mPageSlidingTabStrip;
    private ViewPager mViewPager;
    private HomeViewPagerAdapter mAdapter;
    private HomeActivity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            initView();
            initListener();
        } else if(mView.getParent() != null) {
            ((ViewGroup)mView.getParent()).removeView(mView);
        }
        return mView;
    }
    private void initView() {
        mActivity               = (HomeActivity) getActivity();

        mPageSlidingTabStrip    = (PagerSlidingTabStrip) mView.findViewById(R.id.home_page_silding_tab_strip);
        mViewPager              = (ViewPager) mView.findViewById(R.id.viewpager_home_fragment);
        mAdapter                = new HomeViewPagerAdapter(mActivity.getSupportFragmentManager());

        mViewPager.setAdapter(mAdapter);
        mPageSlidingTabStrip.setViewPager(mViewPager);

        mPageSlidingTabStrip.setIndicatorColor(Color.parseColor("#E84940"));

    }
    private void initListener() {
    }
    class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
        protected final String[] CONTENT = new String[] {
                getString(R.string.home_tab_txt_categroy),
                getString(R.string.home_tab_txt_suggest_book),
                getString(R.string.home_tab_txt_new_book)
        };
        private int totalPage = CONTENT.length;

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = CategoryFragment.instantiate(mActivity, CategoryFragment.class.getName());
                    break;
                case 1:
                    fragment = SuggestBookFragment.instantiate(mActivity, SuggestBookFragment.class.getName());
                    break;
                case 2:
                    fragment = NewBookFragment.instantiate(mActivity, NewBookFragment.class.getName());
                    break;
                default:
                    break;
            }
            return fragment;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length];
        }
        @Override
        public int getCount() {
            return totalPage;
        }
    }
}
