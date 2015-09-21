package studio.orange.mobile.reader.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.fragments.AllBookCategoryFragment;
import studio.orange.mobile.reader.fragments.SuggestBookCategoryFragment;

/**
 * Created by thienlm on 8/2/2015.
 */
public class BookCategoryActivity extends BaseActivity  {
    private PagerSlidingTabStrip mPageSlidingTabStrip;
    private ViewPager mViewPager;
    private BookByCategoryPageAdapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_book_category);
        initView();
        initListener();
    }
    private void initView() {
        getCategoryFromIntentData();
        mPageSlidingTabStrip    = (PagerSlidingTabStrip) findViewById(R.id.category_page_silding_tab_strip);
        mViewPager              = (ViewPager) findViewById(R.id.viewpager_book_by_cateogry);
        mAdpter                 = new BookByCategoryPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdpter);
        mPageSlidingTabStrip.setViewPager(mViewPager);
        mPageSlidingTabStrip.setIndicatorColor(Color.parseColor("#E84940"));
    }
    private void initListener() {

    }
    class BookByCategoryPageAdapter extends FragmentStatePagerAdapter {
        protected final String[] CONTENT = new String[] {
                getString(R.string.category_tab_txt_suggest_book),
                getString(R.string.category_tab_txt_all_book)
        };

        private int totalPage = CONTENT.length;

        public BookByCategoryPageAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length];
        }

        public void setCount(int count) {
            if (count > 0 && count <= 10) {
                totalPage = count;
                notifyDataSetChanged();
            }
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = SuggestBookCategoryFragment.instantiate(BookCategoryActivity.this, SuggestBookCategoryFragment.class.getName());
                default:
                    fragment = AllBookCategoryFragment.instantiate(BookCategoryActivity.this, AllBookCategoryFragment.class.getName());
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount() {
            return totalPage;
        }
    }

    @Override
    public void menuNavigationBarClicked() {
        onBackPressed();
    }
}
