package com.onetech.mobilereader.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onetech.mobilereader.entity.CategoryEntity;
import com.onetech.mobilereader.entity.RequestEntity;

public class SuggestBookCategoryFragment extends OTFragmentGridViewBase {
	private CategoryEntity mCategory;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	void initData() {
		mCategory				= getCurrentCategory();
		if(mCategory != null) {
			mRequest 				= new RequestEntity();
			mRequest.pageIndex  	= 1;
			mRequest.type 			= "";
			mRequest.requestType 	= RequestEntity.REQUEST_TYPE.SUGGEST_BOOK_CATEGORY;
			mRequest.cateId			= mCategory.getId();
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		if(mRequest != null) {
			loadData();
		}
	}
}
