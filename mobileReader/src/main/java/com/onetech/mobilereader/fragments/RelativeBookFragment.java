package com.onetech.mobilereader.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.onetech.mobilereader.R;
import com.onetech.mobilereader.activities.BookDetailActivity;
import com.onetech.mobilereader.configs.AppConfig;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.entity.RequestEntity;

/**
 * Created by thienlm on 7/30/2015.
 */
public class RelativeBookFragment extends OTFragmentGridViewBase {
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
        mRequest.requestType 	= RequestEntity.REQUEST_TYPE.RELATIVE_BOOK;
    }
    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        BookEntity book  = (BookEntity) mAdapter.getItem(i);
        if(book != null) {
            Gson gson	  	= new Gson();
            String content 	= gson.toJson(book);
            Intent intent 	= new Intent(getActivity(), BookDetailActivity.class);
            intent.putExtra(AppConfig.BUNDLE_KEY.BOOK_DETAIL, content);
            startActivity(intent);
            getActivity().finish();
        }
    }
}
