package studio.orange.mobile.reader.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.entity.BookEntity;
import studio.orange.mobile.reader.fragments.BookDetailFragment;
import studio.orange.mobile.reader.fragments.RelativeBookFragment;

/**
 * Created by thienlm on 7/30/2015.
 */
public class BookDetailActivity extends BaseActivity {
    private ViewPager mViewPager;
    private BookDetailAdapter mAdapter;
    private BookEntity mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_book_detail);
        initView();
        initListenter();
    }
    private void initView() {
        getBookFromIntentData();
        mViewPager      = (ViewPager) findViewById(R.id.viewpager_book_detail);
        mAdapter        = new BookDetailAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }
    private void initListenter() {

    }

    @Override
    public void menuNavigationBarClicked() {
        onBackPressed();
    }

    class BookDetailAdapter extends FragmentStatePagerAdapter {
        private int counter = 2;
        public BookDetailAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 1:
                    fragment = RelativeBookFragment.instantiate(BookDetailActivity.this, RelativeBookFragment.class.getName());
                    break;
                default:
                    fragment = BookDetailFragment.instantiate(BookDetailActivity.this, BookDetailFragment.class.getName());
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return counter;
        }
    }
}
