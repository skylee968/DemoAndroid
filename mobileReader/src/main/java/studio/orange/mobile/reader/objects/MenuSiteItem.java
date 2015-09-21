package studio.orange.mobile.reader.objects;


import studio.orange.mobile.reader.R;

public class MenuSiteItem {
	public int id;
	public int level;
	public String name;
	public int resId;
	public MenuSiteItem(){
		id 		= -1;
		level	= 1;
		name	= "";
		resId	= R.drawable.ic_launcher;
	}
}
