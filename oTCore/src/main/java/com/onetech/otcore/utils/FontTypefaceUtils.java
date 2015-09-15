package com.onetech.otcore.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontTypefaceUtils {
	public static void overrideFonts(Context _context,Typeface typeFace,final View v) {
		
		if(typeFace == null)
			return;
		
		try {
			final Context context = _context;
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context,typeFace,child);
				}
			} else if (v instanceof TextView) {				
				((TextView) v).setTypeface(typeFace);
			}
		} catch (Exception e) {
			Log.e("FONT LOAD FAILED:",e.getMessage());
		}
	}
}
