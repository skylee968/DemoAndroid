package com.onetech.mobilereader.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;


import com.onetech.mobilereader.R;
import com.onetech.mobilereader.dialogs.AboutUsDialog;
import com.onetech.mobilereader.dialogs.DirectoryDialog;
import com.onetech.mobilereader.dialogs.DirectoryDialog.ResultDirectorySelected;
import com.onetech.mobilereader.dialogs.GuideDialog;
import com.onetech.mobilereader.dialogs.RatingDialog;
import com.onetech.mobilereader.fragments.HomeFragment;
import com.onetech.mobilereader.fragments.NavigationDrawerFragment;
import com.onetech.mobilereader.uitls.CommonUtils;
import com.onetech.mobilereader.uitls.UserUtils;


public class HomeActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
	private ProgressDialog mProgressDialog 				= null;
	private DirectoryDialog mDirectoryDialog 			= null;
	private ResultDirectorySelected mResultDirectory	= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainContentView(R.layout.activity_home);
		changeNaviIcon(R.mipmap.ic_navi);
        mNavigationDrawerFragment 	= (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
        initView();
		initListener();
		initProgress(null);
    }
    private void initView(){
		mResultDirectory=new ResultDirectorySelected() {
			@Override
			public void onChooseDirectory(String dir) {
//				//Toast.makeText(getApplicationContext(), dir, Toast.LENGTH_LONG).show();
//				Intent intent=new Intent(HomeDemoActivity.this,ReaderActivity.class);
//				intent.putExtra(BUNDLE_KEY.EBOOK_FILE, dir);
//				startActivity(intent);
			}
		};
	}
	
	private void initListener(){
		
	}
	protected void initProgress(String message) {
		mProgressDialog = new ProgressDialog(HomeActivity.this);
		if (message != null) {
			mProgressDialog.setMessage(message);
		} else {
			mProgressDialog.setMessage(getString(R.string.common_waitting_message));
		}
	}
	public void showSelectFileDialog(){
		mDirectoryDialog=new DirectoryDialog(HomeActivity.this, mResultDirectory, null);
	}
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment mMainFragment = null;
        switch (position) {
			case NavigationDrawerFragment.MENU_NAVIGATION_ID.HOME:
				mMainFragment = HomeFragment.instantiate(this, HomeFragment.class.getName());
				break;
			case NavigationDrawerFragment.MENU_NAVIGATION_ID.MY_BOOK:
				if(UserUtils.isUserValid()) {
					Intent mMyBookIntent = new Intent(HomeActivity.this, BookUserActivity.class);
					startActivity(mMyBookIntent);
				} else {
					CommonUtils.showToast(getString(R.string.common_msg_please_login));
				}
				return;
			case NavigationDrawerFragment.MENU_NAVIGATION_ID.SETTING:
				return;
			case NavigationDrawerFragment.MENU_NAVIGATION_ID.RATING:
				RatingDialog ratingDialog = new RatingDialog(HomeActivity.this);
				ratingDialog.show();
				return;
			case NavigationDrawerFragment.MENU_NAVIGATION_ID.GUIDE:
				GuideDialog guideDialog = new GuideDialog(HomeActivity.this);
				guideDialog.show();
				return;
			case NavigationDrawerFragment.MENU_NAVIGATION_ID.ABOUT:
				AboutUsDialog aboutUsDialog = new AboutUsDialog(HomeActivity.this);
				aboutUsDialog.show();
				return;
			default:
				mMainFragment = HomeFragment.instantiate(this, HomeFragment.class.getName());
				break;
		}
    	replaceFragment(mMainFragment);
    }
    private void replaceFragment(Fragment fragment) {
		if (fragment == null) {
			return;
		}
		String backStateName = fragment.getClass().getName();
		String fragmentTag = backStateName;

		FragmentManager fragmentManager = getSupportFragmentManager();
		boolean fragmentPopped = fragmentManager.popBackStackImmediate(
				backStateName, 0);

		if (!fragmentPopped
				&& fragmentManager.findFragmentByTag(fragmentTag) == null) {

			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.replace(R.id.container, fragment, fragmentTag);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(backStateName);
			ft.commit();
		}
	}


	@Override
	public void menuNavigationBarClicked() {
		mNavigationDrawerFragment.toggleMenu();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if(getSupportFragmentManager().getBackStackEntryCount() <= 1) {
			finish();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateUserInfo();
	}
	private void updateUserInfo() {
		mUserInfo = UserUtils.getUserEntity();
		if(mUserInfo != null) {
			mNavigationDrawerFragment.updateUserInfo(mUserInfo);
		}
	}
}
