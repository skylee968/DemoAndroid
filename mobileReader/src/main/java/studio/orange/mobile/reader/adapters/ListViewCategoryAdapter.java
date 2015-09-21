package studio.orange.mobile.reader.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.entity.CategoryEntity;

import java.util.ArrayList;


public class ListViewCategoryAdapter extends OTBaseAdapter<CategoryEntity> {
	protected class ViewHolder{
		public TextView title;
		public TextView total;
		public ImageView imageCover;
	}

	public ListViewCategoryAdapter(Activity _activity) {
		super(_activity);
		mListData = new ArrayList<CategoryEntity>();
		initImageLoaderWithRoundedBitmap(50);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder v;
		if(convertView == null) {
			convertView 	= inflater.inflate(R.layout.item_category_list_view, parent,false);
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
		v.total.setText("(" + String.valueOf(cate.getCounter()) + ")");
		ImageLoader.getInstance().displayImage(cate.getImageCover(), v.imageCover, mOptions);
		return convertView;
	}
}
