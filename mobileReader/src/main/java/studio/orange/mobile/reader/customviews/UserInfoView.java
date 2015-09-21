package studio.orange.mobile.reader.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.entity.UserEntity;

/**
 * Created by thienlm on 8/18/2015.
 */
public class UserInfoView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private View mMainViewContainer;
    private LinearLayout mUserInfoContainer;
    private LinearLayout mLoginContainer;

    private ImageView mImgAvatar;
    private TextView mTxtUserName;
    private TextView mTxtUserCoins;

    private TextView mTxtLoginBtn;

    private UserEntity mUserInfo;

    private OnUserInfoListener mOnUserInfoListener;

    public interface OnUserInfoListener {
        void onLoginClick();
    }
    public UserInfoView(Context context) {
        super(context);
        initView(context);
    }

    public UserInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public UserInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("NewApi")
    public UserInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }
    private void initView(Context context) {
        mContext                    = context;
        LayoutInflater inflater     = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMainViewContainer          = inflater.inflate(R.layout.layout_user_info, null);
        mUserInfoContainer          = (LinearLayout) mMainViewContainer.findViewById(R.id.info_user_container);
        mLoginContainer             = (LinearLayout) mMainViewContainer.findViewById(R.id.login_user_container);
        mImgAvatar                  = (ImageView) mMainViewContainer.findViewById(R.id.img_avatar_user);
        mTxtUserName                = (TextView) mMainViewContainer.findViewById(R.id.user_name);
        mTxtUserCoins               = (TextView) mMainViewContainer.findViewById(R.id.user_coins);
        mTxtLoginBtn                = (TextView) mMainViewContainer.findViewById(R.id.btn_login_user);
        showUserInfoView(false);
        this.addView(mMainViewContainer);

        initListener();
    }
    private void initListener() {
        mTxtLoginBtn.setOnClickListener(this);
    }
    private void showUserInfoView(boolean isShow) {
        mUserInfoContainer.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mLoginContainer.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }
    public void setOnUserInfoListener(OnUserInfoListener onUserInfoListener) {
        mOnUserInfoListener = onUserInfoListener;
    }
    public void updateUserInfo(UserEntity user) {
        if(user != null) {
            mUserInfo   = user;
            mTxtUserName.setText(mUserInfo.getName() == null ? "" : mUserInfo.getName());
            showUserInfoView(true);
        }
    }
    @Override
    public void onClick(View v) {
        int id =  v.getId();
        switch (id) {
            case R.id.btn_login_user:
                if(mOnUserInfoListener != null) {
                    mOnUserInfoListener.onLoginClick();
                }
                break;
            default:
                break;
        }
    }
}
