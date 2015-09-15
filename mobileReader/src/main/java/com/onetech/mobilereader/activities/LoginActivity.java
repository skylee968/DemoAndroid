package com.onetech.mobilereader.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.onetech.mobilereader.R;
import com.onetech.mobilereader.entity.RequestEntity;
import com.onetech.mobilereader.entityresult.LoginResultEntity;
import com.onetech.mobilereader.fragments.LoginFragment;
import com.onetech.mobilereader.models.UserModel;
import com.onetech.mobilereader.notifications.GCMClientManager;
import com.onetech.mobilereader.uitls.CommonUtils;


/**
 * Created by thienlm on 7/6/2015.
 */
public class LoginActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{
    private String TAG = "android-plus-quickstart";

    private GCMClientManager mPushClientManager;
    private String PROJECT_NUMBER                       = "485627361649";
    private AsyncTask<Void, Void, Void> mRegisterTask   = null;

//    private TextView mBtnLogin;

    /* Request code used to invoke sign in user interactions. */
  private static final int RC_SIGN_IN = 0;

  /* Client used to interact with Google APIs. */
  private GoogleApiClient mGoogleApiClient;

  /* A flag indicating that a PendingIntent is in progress and prevents
   * us from starting further intents.
   */
    private boolean mIntentInProgress;

    private int mSignInProgress;

    // Used to store the PendingIntent most recently returned by Google Play
    // services until the user clicks 'sign in'.
    private PendingIntent mSignInIntent;

    // Used to store the error code most recently returned by Google Play services
    // until the user clicks 'sign in'.
    private int mSignInError;

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_SIGN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;

    private static final String SAVED_PROGRESS  = "sign_in_progress";
    private String mRegisterId                  = null;
    private String mEmail                       = null;

    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_login);

//        final Window window = getWindow();
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView(savedInstanceState);
        initListener();
        //registerGCM();
    }
    private void initView(Bundle savedInstanceState) {
        mLoginFragment          = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_fragment);
//        mBtnLogin               = (TextView) findViewById(R.id.next_btn);
        if (savedInstanceState != null) {
            mSignInProgress = savedInstanceState
                    .getInt(SAVED_PROGRESS, STATE_DEFAULT);
        }
        mGoogleApiClient        = buildGoogleApiClient();
    }

    private void initListener() {
//        mBtnLogin.setOnClickListener(this);
        mLoginFragment.setLoginHandler(new LoginFragment.LoginHandler() {
            @Override
            public void loginBtnPressed() {

            }

            @Override
            public void registerNowBtnPressed() {
                go2RegisterPage();
            }

            @Override
            public void forgotPasswordBtnPressed() {

            }

            @Override
            public void loginSuccess(LoginResultEntity loginResult) {
                go2HomePage();
            }
        });

    }

    @Override
    public void menuNavigationBarClicked() {
        onBackPressed();
    }

    private GoogleApiClient buildGoogleApiClient() {
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN);

        return builder.build();
    }

    private void go2HomePage() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void go2RegisterPage() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    private void sendRegisterUser(){
        if(mEmail == null || mRegisterId == null) {
            return;
        }

        String signKey          = CommonUtils.generateSignature(mRegisterId);
        RequestEntity request   = new RequestEntity();
        request.registerId      = mRegisterId;
        request.email           = mEmail;
        request.signKey         = signKey;
    }

    private void registerUser() {
        mPushClientManager = new GCMClientManager(this, PROJECT_NUMBER);
        mPushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {
                if (registrationId != null) {
                    mRegisterId = registrationId;
                    if (!mGoogleApiClient.isConnected()) {
                        mGoogleApiClient.connect();
                    }
                }
            }

            @Override
            public void onFailure(String ex) {
                super.onFailure(ex);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_PROGRESS, mSignInProgress);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    // If the error resolution was successful we should continue
                    // processing errors.
                    mSignInProgress = STATE_SIGN_IN;
                } else {
                    // If the error resolution was not successful or the user canceled,
                    // we should stop processing errors.
                    mSignInProgress = STATE_DEFAULT;
                }
                if (!mGoogleApiClient.isConnecting()) {
                    // If Google Play services resolved the issue with a dialog then
                    // onStart is not called so we need to re-attempt connection here.
                    mGoogleApiClient.connect();
                }
                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mEmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
        if(mEmail != null) {
            Log.e("EMAIL:", mEmail);
//            sendRegisterUser();
        }
        mSignInProgress = STATE_DEFAULT;
    }

    @Override
    public void onConnectionSuspended(int i) {
        if(!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
//            case R.id.next_btn:
//                if(mRegisterId == null) {
//                    registerUser();
//                } else if(!mGoogleApiClient.isConnecting()){
//                    mSignInProgress = STATE_SIGN_IN;
//                    mGoogleApiClient.connect();
//                }
//                break;
            default:
                break;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

        if (result.getErrorCode() == ConnectionResult.API_UNAVAILABLE) {
            // An API requested for GoogleApiClient is not available. The device's current
            // configuration might not be supported with the requested API or a required component
            // may not be installed, such as the Android Wear application. You may need to use a
            // second GoogleApiClient to manage the application's optional APIs.
            Log.w(TAG, "API Unavailable.");
        } else if (mSignInProgress != STATE_IN_PROGRESS) {
            // We do not have an intent in progress so we should store the latest
            // error resolution intent for use when the sign in button is clicked.
            mSignInIntent = result.getResolution();
            mSignInError = result.getErrorCode();

            if (mSignInProgress == STATE_SIGN_IN) {
                // STATE_SIGN_IN indicates the user already clicked the sign in button
                // so we should continue processing errors until the user is signed in
                // or they click cancel.
                resolveSignInError();
            }
        }
    }
    private void resolveSignInError() {
            if (mSignInIntent != null) {
                // We have an intent which will allow our user to sign in or
                // resolve an error.  For example if the user needs to
                // select an account to sign in with, or if they need to consent
                // to the permissions your app is requesting.

                try {
                    // Send the pending intent that we stored on the most recent
                    // OnConnectionFailed callback.  This will allow the user to
                    // resolve the error currently preventing our connection to
                    // Google Play services.
                    mSignInProgress = STATE_IN_PROGRESS;
                    startIntentSenderForResult(mSignInIntent.getIntentSender(),
                            RC_SIGN_IN, null, 0, 0, 0);
                } catch (IntentSender.SendIntentException e) {
                    Log.i(TAG, "Sign in intent could not be sent: "
                            + e.getLocalizedMessage());
                    // The intent was canceled before it was sent.  Attempt to connect to
                    // get an updated ConnectionResult.
                    mSignInProgress = STATE_SIGN_IN;
                    mGoogleApiClient.connect();
                }
            } else {
                // Google Play services wasn't able to provide an intent for some
                // error types, so we show the default Google Play services error
                // dialog which may still start an intent on our behalf if the
                // user can resolve the issue.
                createErrorDialog().show();
            }
        }
    private Dialog createErrorDialog() {
        if (GooglePlayServicesUtil.isUserRecoverableError(mSignInError)) {
            return GooglePlayServicesUtil.getErrorDialog(
                    mSignInError,
                    this,
                    RC_SIGN_IN,
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Log.e(TAG, "Google Play services resolution cancelled");
                            mSignInProgress = STATE_DEFAULT;
                        }
                    });
        } else {
            return new AlertDialog.Builder(this)
                    .setMessage(R.string.play_services_error)
                    .setPositiveButton(R.string.close,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.e(TAG, "Google Play services error could not be "
                                            + "resolved: " + mSignInError);
                                    mSignInProgress = STATE_DEFAULT;
                                }
                            }).create();
        }
    }
}
