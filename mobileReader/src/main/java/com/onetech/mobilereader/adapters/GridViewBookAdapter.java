package com.onetech.mobilereader.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.onetech.mobilereader.R;
import com.onetech.mobilereader.entity.BookEntity;
import com.onetech.mobilereader.https.RestClient;

import org.w3c.dom.Text;


public class GridViewBookAdapter extends BookBaseAdapter {

	public GridViewBookAdapter(Activity _activity) {
		super(_activity);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			convertView 			= inflater.inflate(R.layout.item_book_row_grid_view, parent,false);
			viewHolder 				= new ViewHolder();
			viewHolder.title 		= (TextView) convertView.findViewById(R.id.txt_name_book);
			viewHolder.author		= (TextView) convertView.findViewById(R.id.txt_author_book);
			viewHolder.imageCover	= (ImageView) convertView.findViewById(R.id.img_cover_book);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		BookEntity book 		= mListData.get(position);
		viewHolder.title.setText(book.getName());
		viewHolder.author.setText(book.getAuthor().getLastName());
		ImageLoader.getInstance().displayImage(RestClient.BOOK_IMAGE_COVER_URL + book.getId(), viewHolder.imageCover, mOptions);
		return convertView;
	}
}
