package studio.orange.mobile.reader.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class OTGridView extends GridView {
	@SuppressLint("NewApi")
	public OTGridView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr);
	}
	public OTGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	public OTGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public OTGridView(Context context) {
		super(context);
	}
}
