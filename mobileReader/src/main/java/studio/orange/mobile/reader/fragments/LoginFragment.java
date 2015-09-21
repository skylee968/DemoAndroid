package studio.orange.mobile.reader.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entityresult.LoginResultEntity;
import studio.orange.mobile.reader.models.UserModel;
import studio.orange.mobile.reader.uitls.CommonUtils;
import studio.orange.mobile.reader.uitls.Md5Utils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by thienlm on 7/20/2015.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener{
    private EditText mEdtUsername;
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private TextView mBtnLogin;
    private TextView mTxtRegisterNow;
    private TextView mTxtForgotPassword;
    private RequestEntity mRequestEntity;


    public interface LoginHandler {
        void loginBtnPressed();
        void registerNowBtnPressed();
        void forgotPasswordBtnPressed();
        void loginSuccess(LoginResultEntity loginResult);
    }
    private LoginHandler mLoginListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) {
            mView = inflater.inflate(R.layout.fragment_login, container, false);
            initView();
            initListener();
        } else {
            if(mView.getParent() != null) {
                ((ViewGroup)mView.getParent()).removeView(mView);
            }
        }
        return mView;
    }
    private void initView() {
        mEdtUsername            = (EditText) mView.findViewById(R.id.username_login_edt);
        mEdtEmail               = (EditText) mView.findViewById(R.id.email_login_edt);
        mEdtPassword            = (EditText) mView.findViewById(R.id.password_login_edt);
        mBtnLogin               = (TextView)   mView.findViewById(R.id.login_btn);
        mTxtRegisterNow         = (TextView) mView.findViewById(R.id.register_now_btn);
        mTxtForgotPassword      = (TextView) mView.findViewById(R.id.forgot_password_btn);
    }
    private void initListener(){
        mBtnLogin.setOnClickListener(this);
        mTxtForgotPassword.setOnClickListener(this);
        mTxtRegisterNow.setOnClickListener(this);
    }
    public void setLoginHandler(LoginHandler _handler) {
        mLoginListener = _handler;
    }
    public void setVisibility(boolean isVisiable) {
        mView.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_btn:
                loginUser();
                if(mLoginListener != null) {
                    mLoginListener.loginBtnPressed();
                }
                break;
            case R.id.register_now_btn:
                if(mLoginListener != null) {
                    mLoginListener.registerNowBtnPressed();
                }
                break;
            case R.id.forgot_password_btn:
                if(mLoginListener != null) {
                    mLoginListener.forgotPasswordBtnPressed();
                }
                break;
            default:
                break;
        }
    }
    private void loginUser() {
        if(!validateInfo()) {
            return;
        }
        mRequestEntity.signKey          = CommonUtils.generateSignature(mRequestEntity.passwords);
        mRequestEntity.passwords        = Md5Utils.md5(mRequestEntity.passwords);

        UserModel.getInstance().loginUser(mRequestEntity, new Callback<LoginResultEntity>() {
            @Override
            public void success(LoginResultEntity loginResultEntity, Response response) {
                if(loginResultEntity != null && loginResultEntity.data != null && mLoginListener != null) {
                    mLoginListener.loginSuccess(loginResultEntity);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    private boolean validateInfo() {
        mRequestEntity                  = new RequestEntity();
        mRequestEntity.username         = mEdtUsername.getText().toString().trim();
        mRequestEntity.passwords        = mEdtPassword.getText().toString().trim();
        mRequestEntity.email            = mEdtEmail.getText().toString().trim();

        if(mRequestEntity.username.length() < 1) {
            mEdtUsername.setFocusable(true);
            CommonUtils.showToast(getActivity().getString(R.string.common_msg_invalid_username));
            return false;
        }
//        if(!ValidationUtils.validateEmail(mRequestEntity.email)) {
//            mEdtEmail.setFocusable(true);
//            CommonUtils.showToast(getActivity().getString(R.string.common_msg_invalid_email));
//            return false;
//        }
        if(mRequestEntity.passwords.length() < 1) {
            mEdtPassword.setFocusable(true);
            CommonUtils.showToast(getActivity().getString(R.string.common_msg_invalid_passowd));
            return false;
        }
        return true;
    }
}
