package ca.hackathon.androiddsmedicalcare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Jennifer on 2015-06-13.
 */
public class AlarmSetter extends BroadcastReceiver {

    static private AlarmManager alarm;
    static private Intent intent;
    static private PendingIntent alarmIntent;
    static private int hr;
    static private int min;
    static private Context context;
    static private int snoozeFreq;

    // used for receiving Intents
    public AlarmSetter() {
        this.intent = new Intent(context, AlarmReceiver.class);
        this.alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public AlarmSetter(int hr, int min, int snoozeFreq, Context context, AlarmManager alarm) {
        this.hr = hr;
        this.min = min;
        this.context = context;
        this.alarm = alarm;
        this.snoozeFreq = snoozeFreq;
        this.intent = new Intent(context, AlarmReceiver.class);
        this.alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hr);
        calendar.set(Calendar.MINUTE, min);
        Log.i("setAlarm", "before");
        alarm.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                1000 * snoozeFreq, alarmIntent);
        Log.i("setAlarm", "after");

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra("sender").equals("BedTimeGUI")) {
            Log.i("AlarmSetter", "disable timer");
            alarm.cancel(alarmIntent);
        }
    }
}