// UNUSED

package util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import ca.hackathon.androiddsmedicalcare.R;

/**
 * Created by Jennifer on 2015-06-13.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TESTING", "TESTING");
        int ID = 0;
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(ID, getNotification(context));
    }
    private Notification getNotification(Context context) {

        Notification.Builder notificationSetting;
        notificationSetting = new Notification.Builder(context)
                .setContentTitle("Log Notification")
                .setContentText("Log Time")
                .setLights(Color.RED, 3000, 3000)
                .setSmallIcon(R.drawable.alert)
                .setVibrate(new long[]{1000, 1000, 1000, 1000});

        return notificationSetting.build();
    }
}
