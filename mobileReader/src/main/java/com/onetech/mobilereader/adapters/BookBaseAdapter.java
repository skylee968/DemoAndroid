package com.onetech.mobilereader.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.onetech.mobilereader.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thienlm on 8/1/2015.
 */
public class BookBaseAdapter extends OTBaseAdapter<BookEntity> {
    protected class ViewHolder{
        public TextView title;
        public TextView author;
        public ImageView imageCover;
    }
    public BookBaseAdapter(Activity _activity) {
        super(_activity);
//        activity = _activity;
//        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListData = new ArrayList<BookEntity>();
        //initImageLoader();
    }
    public void udpateData(List<BookEntity> listData) {
        if(mListData == null) {
            mListData = new ArrayList<BookEntity>();
        }
        mListData.clear();
        mListData.addAll(listData);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
}
