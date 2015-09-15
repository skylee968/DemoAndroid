package com.onetech.mobilereader.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.onetech.mobilereader.R;
import com.onetech.mobilereader.entity.CategoryEntity;
import com.onetech.mobilereader.https.RestClient;

import java.util.ArrayList;


public class GridViewCategoryAdapter extends OTBaseAdapter<CategoryEntity> {
	protected class ViewHolder{
		public TextView title;
		public TextView total;
		public ImageView imageCover;
	}

	public GridViewCategoryAdapter(Activity _activity) {
		super(_activity);
		mListData = new ArrayList<CategoryEntity>();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder v;
		if(convertView == null) {
			convertView 			= inflater.inflate(R.layout.item_category_grid_view, parent,false);
			v 				= new ViewHolder();
			v.title 		= (TextView) convertView.findViewById(R.id.txt_name_category);
			v.total			= (TextView) convertView.findViewById(R.id.txt_total_category);
			v.imageCover 	= (ImageView) convertView.findViewById(R.id.img_cover_category);
			convertView.setTag(v);
		} else {
			v = (ViewHolder) convertView.getTag();
		}
		CategoryEntity cate 		= mListData.get(position);
		v.title.setText(cate.getName());
		v.total.setText(cate.getCounter());
		ImageLoader.getInstance().displayImage(RestClient.BOOK_IMAGE_COVER_URL + cate.getId(), v.imageCover, mOptions);
		return convertView;
	}
}
