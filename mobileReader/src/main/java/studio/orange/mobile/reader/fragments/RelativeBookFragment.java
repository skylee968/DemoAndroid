package studio.orange.mobile.reader.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;

import studio.orange.mobile.reader.activities.BookDetailActivity;
import studio.orange.mobile.reader.configs.AppConfig;
import studio.orange.mobile.reader.entity.BookEntity;
import studio.orange.mobile.reader.entity.RequestEntity;

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
