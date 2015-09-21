package studio.orange.mobile.reader.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import studio.orange.mobile.reader.R;
import studio.orange.mobile.reader.objects.MenuSiteItem;

import java.util.ArrayList;
import java.util.List;


public class MenuSiteAdapter extends BaseAdapter {
	private class AppViewHolder {
		public View mainView;
		public TextView title;
		public ImageView icon;
	}

	private Activity activity;
	private List<MenuSiteItem> mData;
	private static LayoutInflater inflater = null;

	public MenuSiteAdapter(Activity _activity) {
		activity = _activity;
		mData = new ArrayList<MenuSiteItem>();
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public void updateAppList(List<MenuSiteItem> _mData) {
		mData.clear();
		mData.addAll(_mData);
		notifyDataSetChanged();
	}

	public void insertAppList(List<MenuSiteItem> _mData) {
		mData.addAll(_mData);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public MenuSiteItem getItem(int arg0) {
		if (mData == null) {
			return null;
		}
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.layout_navigation_listview_item,
					parent, false);
			viewHolder 			= new AppViewHolder();
			viewHolder.mainView	= (View)convertView.findViewById(R.id.mainMenuItem);
			viewHolder.title 	= (TextView) convertView
					.findViewById(R.id.menuSiteName);	
			viewHolder.icon		= (ImageView)convertView.findViewById(R.id.iconMenuDrawer);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (AppViewHolder) convertView.getTag();
		}
		
		MenuSiteItem temp = mData.get(position);
		viewHolder.title.setText(temp.name);
//		if(temp.level==1){
//			viewHolder.mainView.setBackgroundResource(R.drawable.background_listview_item_menu_parent);
//			viewHolder.icon.setVisibility(View.GONE);
//			viewHolder.title.setTextColor(activity.getResources().getColor(R.color.white));
//			viewHolder.title.setTypeface(viewHolder.title.getTypeface(), Typeface.BOLD);
//		}else{
//			viewHolder.mainView.setBackgroundResource(R.drawable.background_listview_item_menu_child);
			viewHolder.icon.setBackgroundResource(temp.resId);
//			viewHolder.icon.setVisibility(View.VISIBLE);
//			viewHolder.title.setTextColor(activity.getResources().getColor(R.color.white));
//			viewHolder.title.setTypeface(viewHolder.title.getTypeface(), Typeface.NORMAL);
//		}
		return convertView;
	}

}

