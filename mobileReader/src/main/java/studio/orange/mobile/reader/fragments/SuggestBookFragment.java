package studio.orange.mobile.reader.fragments;

import studio.orange.mobile.reader.entity.RequestEntity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SuggestBookFragment extends OTFragmentGridViewBase {
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
		mRequest.requestType 	= RequestEntity.REQUEST_TYPE.SUGGEST_BOOKS;
	}
	@Override
	public void onResume() {
		super.onResume();
		loadData();
	}
}
