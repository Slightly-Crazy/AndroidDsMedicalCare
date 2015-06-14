package ca.hackathon.androiddsmedicalcare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by Jennifer on 2015-06-13.
 */
public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlarmReceiver", "ping");
        int ID = 0;
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification notification = getNotification(context);
        notificationManager.notify(ID, notification);
    }
    private Notification getNotification(Context context) {
        // create notification
        Notification.Builder mBuilder =
                new Notification.Builder(context)
                        .setSmallIcon(R.drawable.ic_assignment_ind_white_48dp)
                        .setContentTitle("Reminder")
                        .setContentText("Fill out Sleep survey for Alice")
                        .setPriority(Notification.PRIORITY_MAX)
                        .setLights(Color.RED, 3000, 3000)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000})
                        .setAutoCancel(true);  // dismiss notification when pressed on
        // Creates an explicit intent for an Activity in your app
        Intent surveyIntent = new Intent(context, SurveyActivity.class);
        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(SurveyActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(surveyIntent);
        PendingIntent startSurveyIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(startSurveyIntent);
        return mBuilder.build();
    }
}
