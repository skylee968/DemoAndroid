package studio.orange.mobile.reader.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.configs.Constants;
import studio.orange.mobile.reader.entity.UserEntity;
import studio.orange.mobile.reader.models.UserModel;
import studio.orange.mobile.reader.socials.Auth;
import studio.orange.mobile.reader.socials.FacebookAuth;
import studio.orange.mobile.reader.socials.GoogleSocialAuth;
import studio.orange.mobile.reader.socials.SocialProfile;

/**
 * Created by thienlm on 7/6/2015.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, Auth.OnAuthListener {

    public static final String USER_AUTHENTICATED   = "user_authenticated"; //value is a Boolean
    public static final String USER_SOCIAL          = "user_social"; //value is a String and means user is logged with Social.FACEBOOK or Social.GOOGLE
    public static final String PROFILE_NAME         = "profile_name";  //value is a String
    public static final String PROFILE_EMAIL        = "profile_email";
    public static final String PROFILE_IMAGE        = "profile_image";  //value is a String
    public static final String PROFILE_COVER        = "profile_cover"; //value is a String


    private GoogleSocialAuth mGoogleAuth;
    private FacebookAuth mFacebookAuth;
    private String mSocialNetwork;

    private TextView mTxtGoogleBtn;
    private TextView mTxtFacebookBtn;
    private TextView mTxtCloseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_login);

        initView();
        initListener();
    }

    private void initView() {
        mTxtGoogleBtn           = (TextView) findViewById(R.id.btn_login_google);
        mTxtFacebookBtn         = (TextView) findViewById(R.id.btn_login_facebook);
        mTxtCloseBtn            = (TextView) findViewById(R.id.btn_login_close);


        mGoogleAuth = new GoogleSocialAuth(this, this);
        mFacebookAuth = new FacebookAuth(this, this);

    }
    private void initListener(){
        mTxtGoogleBtn.setOnClickListener(this);
        mTxtFacebookBtn.setOnClickListener(this);
        mTxtCloseBtn.setOnClickListener(this);
    }
    @Override
    public void menuNavigationBarClicked() {
        onBackPressed();
    }


    private void go2HomePage() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //disconnect google client api
        mGoogleAuth.logout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mSocialNetwork != null) {
            if(mSocialNetwork.equals(SocialProfile.GOOGLE) && requestCode == GoogleSocialAuth.GOOGLE_SIGN_IN){
                if(resultCode == RESULT_OK) {
                    //call connect again because google just authorized app
                    mGoogleAuth.login();
                } else {
                    onLoginCancel();
                }
            }
            if(mSocialNetwork.equals(SocialProfile.FACEBOOK)) {
                mFacebookAuth.getFacebookCallbackManager().onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login_google:
                mSocialNetwork  = SocialProfile.GOOGLE;
                mGoogleAuth.login();
                break;
            case R.id.btn_login_facebook:
                mSocialNetwork  = SocialProfile.FACEBOOK;
                mFacebookAuth.login();
                break;
            case R.id.btn_login_close:
                mSocialNetwork  = SocialProfile.NONE;
                finish();
                break;
            default:
                mSocialNetwork  = SocialProfile.NONE;
                break;
        }
    }

    @Override
    public void onLoginSuccess(SocialProfile profile) {
        Gson gson = new Gson();
        Log.e("USER PROFILE", gson.toJson(profile));
        //save on shared preferences
        saveAuthenticatedUser(profile);

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onLoginError(String message) {
        Log.e("teste", message);
    }

    @Override
    public void onLoginCancel() {}

    @Override
    public void onRevoke() {}

    private void saveAuthenticatedUser(SocialProfile profile){
        UserEntity user = new UserEntity();
        user.setName(profile.getName());
        user.setEmail(profile.getEmail());
        user.setAvatar(profile.getImage());

        Constants.mUserInfo = user;
        UserModel.getInstance().saveUserEntity(user);

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putBoolean(USER_AUTHENTICATED, true);
//        editor.putString(USER_SOCIAL,   profile.getNetwork());
//        editor.putString(PROFILE_NAME,  profile.getName());
//        editor.putString(PROFILE_EMAIL, profile.getEmail());
//        editor.putString(PROFILE_IMAGE, profile.getImage());
//        editor.putString(PROFILE_COVER, profile.getCover());
//        editor.apply();
    }
}
