package studio.orange.mobile.reader.notifications;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by thienlm on 7/8/2015.
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver{
    // Receives the broadcast directly from GCM service
    @Override
    public void onReceive(Context context, Intent intent) {
        // Explicitly specify that GcmMessageHandler will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmMessageHandler.class.getName());
        // Start the service, keeping the device awake while it is executing.
        startWakefulService(context, (intent.setComponent(comp)));
        // Return successful
        setResultCode(Activity.RESULT_OK);
    }
}
