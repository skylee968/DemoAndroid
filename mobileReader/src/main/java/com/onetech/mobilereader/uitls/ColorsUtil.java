package com.onetech.mobilereader.uitls;

import android.content.Context;
import android.graphics.Color;

import com.onetech.mobilereader.R;

/**
 * Created by thienlm on 8/15/2015.
 */
public class ColorsUtil {
    public  static String getStringColor(Context context, int resId) {
        return Integer.toHexString(context.getResources().getColor(resId));
    }
}
