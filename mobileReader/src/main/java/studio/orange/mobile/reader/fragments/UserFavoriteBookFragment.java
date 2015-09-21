package studio.orange.mobile.reader.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import studio.orange.mobile.reader.entity.RequestEntity;
import studio.orange.mobile.reader.uitls.UserUtils;

public class UserFavoriteBookFragment extends OTFragmentListViewBase {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	void initData() {
		mRequest 				= new RequestEntity();
		mRequest.pageIndex  	= 1;
		mRequest.type 			= "";
		mRequest.requestType 	= RequestEntity.REQUEST_TYPE.USER_FAVORITE_BOOK;
		mRequest.userId			= UserUtils.getUserEntity().getId();
	}
	@Override
	public void onResume() {
		super.onResume();
		loadData();
	}
}
