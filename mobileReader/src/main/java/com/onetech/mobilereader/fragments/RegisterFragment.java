package com.onetech.mobilereader.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.onetech.mobilereader.R;
import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entityresult.RegisterResultEntity;
import com.onetech.mobilereader.models.UserModel;
import com.onetech.mobilereader.uitls.CommonUtils;
import com.onetech.mobilereader.uitls.Md5Utils;
import com.onetech.mobilereader.uitls.ValidationUtils;

import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by thienlm on 7/20/2015.
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener{
    private EditText mEdtUsername;
    private EditText mEdtEmail;
    private EditText mEdtPassword;
    private EditText mEdtConfirmPasswod;
    private TextView mBtnRegister;

//    private TextView mTxtBack;
    private RequestEntity mRequest;
    public interface RegisterHandler {
        void onBackPressed();
        void onRegisterPressed();
        void onSuccessRegister(RegisterResultEntity result);
    }
    private RegisterHandler mRegisterListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) {
            mView = inflater.inflate(R.layout.fragment_register, container, false);
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
        mEdtUsername        = (EditText) mView.findViewById(R.id.username_register_edt);
        mEdtEmail           = (EditText) mView.findViewById(R.id.email_register_edt);
        mEdtPassword        = (EditText) mView.findViewById(R.id.password_register_edt);
        mEdtConfirmPasswod  = (EditText) mView.findViewById(R.id.confirm_password_register_edt);
        mBtnRegister        = (TextView) mView.findViewById(R.id.register_btn);
//        mTxtBack            = (TextView) mView.findViewById(R.id.back_btn);
    }
    private void initListener(){
        mEdtUsername.setOnClickListener(this);
        mEdtEmail.setOnClickListener(this);
        mEdtPassword.setOnClickListener(this);
        mEdtConfirmPasswod.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
//        mTxtBack.setOnClickListener(this);
    }
    public void setRegisterHandler(RegisterHandler _handler) {
        mRegisterListener = _handler;
    }
    public void setVisibility(boolean isVisiable) {
        mView.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.username_register_edt:
                break;
            case R.id.email_register_edt:
                break;
            case R.id.password_register_edt:
                break;
            case R.id.confirm_password_register_edt:
                break;
            case R.id.register_btn:
                registerUser();
                if(mRegisterListener != null) {
                    mRegisterListener.onRegisterPressed();
                }
                break;
//            case R.id.back_btn:
//                if(mRegisterListener != null) {
//                    mRegisterListener.onBackPressed();
//                }
//                break;
            default:
                break;
        }
    }
    private  void registerUser() {
        if(!validateInfo()) {
            return;
        }

        mRequest.signKey        = CommonUtils.generateSignature((mRequest.username + mRequest.email));
        mRequest.passwords      = Md5Utils.md5(mRequest.passwords);
        mRequest.imeiNumber     = String.valueOf((new Random()).nextLong());
        UserModel.getInstance().registerUser(mRequest, new Callback<RegisterResultEntity>() {
            @Override
            public void success(RegisterResultEntity registerResultEntity, Response response) {
                if(registerResultEntity != null && registerResultEntity.data != null && mRegisterListener != null) {
                    mRegisterListener.onSuccessRegister(registerResultEntity);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Register Error:", error.toString());
            }
        });
    }
    private boolean validateInfo() {
        mRequest                = new RequestEntity();
        mRequest.username       = mEdtUsername.getText().toString().trim();
        mRequest.email          = mEdtEmail.getText().toString().trim();
        mRequest.passwords      = mEdtPassword.getText().toString().trim();
        String confirmPassword  = mEdtConfirmPasswod.getText().toString().trim();

        if(mRequest.username.length() < 1) {
            mEdtUsername.setFocusable(true);
            CommonUtils.showToast(getActivity().getString(R.string.common_msg_invalid_username));
        }
        if(!ValidationUtils.validateEmail(mRequest.email)) {
            mEdtEmail.setFocusable(true);
            CommonUtils.showToast(getActivity().getString(R.string.common_msg_invalid_email));
            return false;
        }
        if(mRequest.passwords.length() < 1) {
            mEdtPassword.setFocusable(true);
            CommonUtils.showToast(getActivity().getString(R.string.common_msg_invalid_passowd));
            return false;
        }
        if(confirmPassword.length() < 1) {
            mEdtConfirmPasswod.setFocusable(true);
            CommonUtils.showToast(getActivity().getString(R.string.common_msg_confirm_passowd_not_match));
            return false;
        }
        if(!confirmPassword.equals(mRequest.passwords)) {
            mEdtConfirmPasswod.setFocusable(true);
            CommonUtils.showToast(getActivity().getString(R.string.common_msg_confirm_passowd_not_match));
            return false;
        }
        return true;
    }
}
