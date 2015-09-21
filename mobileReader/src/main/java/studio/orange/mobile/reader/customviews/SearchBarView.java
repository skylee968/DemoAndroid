package studio.orange.mobile.reader.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import studio.orange.mobile.reader.R;


/**
 * Created by thienlm on 7/11/2015.
 */
public class SearchBarView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private ImageView mBtnSearch;
    private ImageView mBtnNavigation;
    private EditText mEdtSearchBox;

    public SearchBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initListener();
    }

    public SearchBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initListener();
    }

    @SuppressLint("NewApi")
    public SearchBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initView();
        initListener();
    }

    public SearchBarView(Context context) {
        super(context);
        mContext = context;
        initView();
        initListener();
    }

    public interface OnSearchBarListner {
        void menuNavigationSearchBarClicked();
        void menuSearchBtnClicked(String searchText);
    }
    private OnSearchBarListner mListener;
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView                   = inflater.inflate(R.layout.layout_search_bar, null);
        mBtnSearch              = (ImageView) mView.findViewById(R.id.btn_search);
        mBtnNavigation          = (ImageView) mView.findViewById(R.id.btn_navigation);
        mEdtSearchBox           = (EditText) mView.findViewById(R.id.edt_search_box);
        this.addView(mView);
    }
    private void initListener() {
        mBtnNavigation.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
    }
    public void changeNaviIcon(int resIconId) {
        mBtnNavigation.setBackgroundResource(resIconId);
    }
    public void setOnSearchBarListner(OnSearchBarListner _listener) {
        mListener = _listener;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_navigation:
                if(mListener != null) {
                    mListener.menuNavigationSearchBarClicked();
                }
                break;
            case R.id.btn_search:
                if(mListener != null) {
                    mListener.menuSearchBtnClicked(mEdtSearchBox.getText().toString().trim());
                }
                break;
            default:
                break;
        }
    }
}
