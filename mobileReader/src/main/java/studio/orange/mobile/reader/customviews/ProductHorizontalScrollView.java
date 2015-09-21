package studio.orange.mobile.reader.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.entity.BookEntity;

import java.util.List;

/**
 * Created by thienlm on 8/21/2015.
 */
public class ProductHorizontalScrollView extends RelativeLayout {
    private Context mConext;
    private LayoutInflater mLayoutInflater;
    private View mMainView;
    private LinearLayout mProductView;
    private TextView mTxtProductCategoryTitle;
    private List<BookEntity> mListProducts;

    private DisplayImageOptions mOptions;

    public ProductHorizontalScrollView(Context context) {
        super(context);
        initView(context);
    }

    public ProductHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProductHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("NewApi")
    public ProductHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void initView(Context context) {
        mConext                     = context;
        mLayoutInflater             = (LayoutInflater) mConext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMainView                   = mLayoutInflater.inflate(R.layout.layout_product_horizontal_scroll_view, null);
        mProductView                = (LinearLayout) mMainView.findViewById(R.id.product_horizontal_container);
        mTxtProductCategoryTitle    = (TextView) mMainView.findViewById(R.id.product_category_title);
        this.addView(mMainView);

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
    public void setListProducts(List<BookEntity> listProducts) {
        mListProducts = listProducts;
        initHorizontalView();
    }
    private void initHorizontalView() {
        if(mListProducts != null && mListProducts.size() >0) {
            int size = mListProducts.size();
            for (int i = 0; i < size; i++) {
                initHorizontalItem(mListProducts.get(i));
            }
        }
    }
    private void initHorizontalItem(BookEntity product) {
//        LinearLayout layout             = new LinearLayout(mConext);
//        layout.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.convertDpToPixel(150, mConext), ScreenUtils.convertDpToPixel(180, mConext)));
//        layout.setGravity(Gravity.CENTER);
        RelativeLayout layout       = (RelativeLayout) mLayoutInflater.inflate(R.layout.item_book_row_grid_view, null);
        ImageView imageView         = (ImageView) layout.findViewById(R.id.img_cover_book);
        ImageLoader.getInstance().displayImage(product.getImageCover(), imageView, mOptions);
    }
}
