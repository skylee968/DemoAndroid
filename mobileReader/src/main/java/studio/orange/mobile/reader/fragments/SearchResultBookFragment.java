package studio.orange.mobile.reader.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import studio.orange.mobile.reader.entity.RequestEntity;

public class SearchResultBookFragment extends OTFragmentListViewBase {
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
		mRequest.requestType 	= RequestEntity.REQUEST_TYPE.SEARCH;
	}
	@Override
	public void onResume() {
		super.onResume();
		loadData();
	}
}
