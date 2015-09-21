package studio.orange.mobile.reader.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.dialogs.UpdateDialog;
import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.entity.ServerConfigEntity;
import studio.orange.mobile.reader.entityresult.ServerConfigResultEntity;
import studio.orange.mobile.reader.models.CommonModel;
import studio.orange.mobile.reader.models.UserModel;
import studio.orange.mobile.reader.notifications.GCMClientManager;
import studio.orange.mobile.reader.uitls.CommonUtils;
import studio.orange.mobile.reader.uitls.UserUtils;
import com.onetech.otcore.view.customviews.ProgressAnimation;
import com.onetech.otcore.view.dialogs.BaseDialog;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by thienlm on 7/6/2015.
 */
public class SplashActivity extends BaseActivity implements View.OnClickListener{
//    private String TAG = "android-plus-quickstart";

    private GCMClientManager mPushClientManager;
    private String PROJECT_NUMBER                       = "485627361649";
    private final int SPLASH_DISPLAY_TIME               = 3000;
    private TextView mVersionName                       = null;
    private View mErrorContainer                        = null;
    private TextView mTxtErrorMessage                   = null;

    private AsyncTask<Void, Void, Void> mRegisterTask   = null;
    private ProgressAnimation mProgressBar              = null;
    private Handler mHandler;
    private Runnable mRunnable;

    private TextView mBtnRetry;

    private String mRegisterId                  = null;
    private String mEmail                       = null;


    public interface ExitDialogHandler{
        public void doExit(boolean isForceUpdate);
        public void doAction();
    }
    private UpdateDialog mUpdateDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView(savedInstanceState);
        initListener();
        //registerGCM();
    }
    private void initView(Bundle savedInstanceState) {
        mErrorContainer     = (LinearLayout) findViewById(R.id.error_container);
        mTxtErrorMessage    = (TextView) mErrorContainer.findViewById(R.id.error_message);
        mVersionName        = (TextView) findViewById(R.id.txtViewVersion);

        mProgressBar        =(ProgressAnimation)findViewById(R.id.splashProgressBar);
        mBtnRetry           = (Button) findViewById(R.id.btn_retry);
        String verName      = CommonUtils.getVersionName();
        if (verName != null) {
            mVersionName.setText(verName);
        }

        hideErrorMessage();

    }
    private void initListener() {
        mBtnRetry.setOnClickListener(this);
    }

    @Override
    public void menuNavigationBarClicked() {

    }

    private void checkConnection() {
        go2HomePage();
    }

    private void go2HomePage() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void go2LoginPage() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void doHandler(){
        mProgressBar.start();
        mHandler  = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                checkUser();
            }
        };
        mHandler.postDelayed(mRunnable, SPLASH_DISPLAY_TIME);


    }
    private  void showUpdateDialog(final ServerConfigEntity entity){
        if(entity != null && entity.system != null) {
            try {
                if(entity.system.target_version_update.compareTo(CommonUtils.getVersionName()) > 0) {
                    mUpdateDialog = new UpdateDialog(this, entity.system.is_force_update, entity.system.url_update);
                    mUpdateDialog.setOnDialogListener(new BaseDialog.OnDialogListener() {
                        @Override
                        public void onPositiveButtonClicked() {
                            CommonUtils.openWebUrl(entity.system.url_update);
                        }

                        @Override
                        public void onNegativeButtonClicked() {
                            if(entity.system.is_force_update) {
                                mUpdateDialog.dismiss();
                                SplashActivity.this.finish();
                            } else {
                                go2HomePage();
                            }
                        }

                        @Override
                        public void onDefaultButtonClicked() {
                            mUpdateDialog.dismiss();
                        }
                    });
                    mUpdateDialog.show();
                } else {
                    go2HomePage();
                }
            }catch (Exception ex){
                return;
            }
        } else {
            showErrorMessage();
        }


    }
    private void showErrorMessage() {
        mTxtErrorMessage.setText(getString(R.string.common_txt_server_error_default));
        mErrorContainer.setVisibility(View.VISIBLE);
    }
    private void hideErrorMessage() {
        mErrorContainer.setVisibility(View.INVISIBLE);
    }
    private void sendRegisterUser(){
        if(mEmail == null || mRegisterId == null) {
            go2LoginPage();
            return;
        }
        String signKey          = CommonUtils.generateSignature(mRegisterId);
        RequestEntity request   = new RequestEntity();
        request.registerId      = mRegisterId;
        request.email           = mEmail;
        request.signKey         = signKey;
        CommonModel.getInstance().getServerConfig(request, new Callback<ServerConfigResultEntity>() {
            @Override
            public void success(ServerConfigResultEntity serverConfigResultEntity, Response response) {
                if (serverConfigResultEntity != null && serverConfigResultEntity.data != null) {
                    if (serverConfigResultEntity.data.user != null) {
                        UserModel.getInstance().saveUserEntity(serverConfigResultEntity.data.user);
                    }
                    showUpdateDialog(serverConfigResultEntity.data);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                int a = 0;
            }
        });
    }
    private void registerUser() {
        mPushClientManager = new GCMClientManager(this, PROJECT_NUMBER);
        mPushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {
                mRegisterId = registrationId;
                //sendRegisterUser();
                Log.e("REGISTER ID", registrationId);
            }

            @Override
            public void onFailure(String ex) {
                super.onFailure(ex);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ServerConfigEntity config = new ServerConfigEntity();
//        config.system = new ServerEntity();
//        config.system.target_version_update = "1.0.1";
//        config.system.is_force_update       = false;
//        config.system.url_update            = "http://google.com";
        //showUpdateDialog(config);
        registerUser();
        doHandler();
    }
    private void checkUser() {
        UserUtils.getUserEntity();
        go2HomePage();
//        if(user != null && user.getEmail() != null) {
//            mEmail = user.getEmail();
//            registerUser();
//        } else {
//            go2LoginPage();
//        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
        if(mProgressBar != null && mProgressBar.isRunning()) {
            mProgressBar.stop();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_retry:
                hideErrorMessage();
                doHandler();
                break;
            default:
                break;
        }
    }
}
