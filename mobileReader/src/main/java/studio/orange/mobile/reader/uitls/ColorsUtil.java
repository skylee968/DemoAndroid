package studio.orange.mobile.reader.uitls;

import android.content.Context;

/**
 * Created by thienlm on 8/15/2015.
 */
public class ColorsUtil {
    public  static String getStringColor(Context context, int resId) {
        return Integer.toHexString(context.getResources().getColor(resId));
    }
}
