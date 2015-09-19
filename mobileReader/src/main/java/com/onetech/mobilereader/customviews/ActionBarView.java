package com.onetech.mobilereader.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.onetech.mobilereader.R;
import com.onetech.mobilereader.uitls.ScreenUtils;


/**
 * Created by thienlm on 7/11/2015.
 */
public class ActionBarView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private ImageView mBtnSearch;
    private ImageView mBtnNavigation;

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("NewApi")
    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public ActionBarView(Context context) {
        super(context);
        initView(context);
    }

    public interface ActionBarListner {
        void menuNavigationBarClicked();

        void menuSearchClicked();
    }

    private ActionBarListner mListener;

    private void initView(Context context) {
        mContext                = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView                   = inflater.inflate(R.layout.layout_action_bar, null);
        mBtnSearch              = (ImageView) mView.findViewById(R.id.btn_search);
        mBtnNavigation          = (ImageView) mView.findViewById(R.id.btn_navigation);
        this.addView(mView);
        initListener();
    }

    private void initListener() {
        mBtnNavigation.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
    }

    public void changeNaviIcon(int resIconId) {
        mBtnNavigation.setBackgroundResource(resIconId);
    }

    public void setListener(ActionBarListner _listener) {
        mListener = _listener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_navigation:
                if (mListener != null) {
                    mListener.menuNavigationBarClicked();
                }
                break;
            case R.id.btn_search:
                if (mListener != null) {
                    mListener.menuSearchClicked();
                }
                break;
            default:
                break;
        }
    }
}
