package studio.orange.mobile.reader.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.activities.BaseActivity;
import studio.orange.mobile.reader.entity.BookEntity;
import studio.orange.mobile.reader.entity.CategoryEntity;

/**
 * Created by thienlm on 7/5/2015.
 */
public class BaseFragment extends Fragment {
    protected View mView;
    protected DisplayImageOptions mOptions;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void setCurrentBook(BookEntity book) {
        ((BaseActivity)getActivity()).setCurrnentBook(book);
    }
    protected BookEntity getCurrentBook() {
        return ((BaseActivity)getActivity()).getCurrnentBook();
    }
    protected CategoryEntity getCurrentCategory() {
        return ((BaseActivity)getActivity()).getCategory();
    }
    protected void initImageLoader() {
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.not_found_icon)
                .showImageForEmptyUri(R.drawable.not_found_icon)
                .showImageOnFail(R.drawable.not_found_icon)
                .resetViewBeforeLoading(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .cacheInMemory(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300)).handler(new Handler()).build();
    }
}
