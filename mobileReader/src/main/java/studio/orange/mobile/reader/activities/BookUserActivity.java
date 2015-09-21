package studio.orange.mobile.reader.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.fragments.UserBookFragment;
import studio.orange.mobile.reader.fragments.UserFavoriteBookFragment;

/**
 * Created by thienlm on 8/2/2015.
 */
public class BookUserActivity extends BaseActivity  {
    private PagerSlidingTabStrip mPageSlidingTabStrip;
    private ViewPager mViewPager;
    private BookByCategoryPageAdapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_book_user);
        initView();
        initListener();
    }
    private void initView() {
        getCategoryFromIntentData();
        mPageSlidingTabStrip    = (PagerSlidingTabStrip) findViewById(R.id.page_silding_tab_strip_user_book);
        mViewPager              = (ViewPager) findViewById(R.id.viewpager_user_book);
        mAdpter                 = new BookByCategoryPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdpter);
        mPageSlidingTabStrip.setViewPager(mViewPager);
        mPageSlidingTabStrip.setIndicatorColor(Color.parseColor("#E84940"));
    }
    private void initListener() {

    }
    class BookByCategoryPageAdapter extends FragmentStatePagerAdapter {
        protected final String[] CONTENT = new String[] {
                getString(R.string.user_book_tab_txt_my_book),
                getString(R.string.user_book_tab_txt_favorite_book)
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
                    fragment = UserBookFragment.instantiate(BookUserActivity.this, UserBookFragment.class.getName());
                default:
                    fragment = UserFavoriteBookFragment.instantiate(BookUserActivity.this, UserFavoriteBookFragment.class.getName());
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
