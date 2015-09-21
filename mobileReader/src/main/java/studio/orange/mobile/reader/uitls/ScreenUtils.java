package studio.orange.mobile.reader.uitls;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by thienlm on 8/21/2015.
 */
public class ScreenUtils {
    public static int convertDpToPixel(float paramFloat, Context paramContext) {
        return (int) TypedValue.applyDimension(1, paramFloat, paramContext
                .getResources().getDisplayMetrics());
    }
}
