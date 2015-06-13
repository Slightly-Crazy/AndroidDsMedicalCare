package util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Jennifer on 2015-06-13.
 */
public class AlarmSetter {

    private AlarmManager alarm;
    private PendingIntent alarmIntent;
    private int hr;
    private int min;
    private Context context;
    private int snoozeFreq;

    public AlarmSetter(int hr, int min, int snoozeFreq, Context context, AlarmManager alarm) {
        this.hr = hr;
        this.min = min;
        this.context = context;
        this.alarm = alarm;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSnoozeFreq(int snoozeFreq) {
        this.snoozeFreq = snoozeFreq;
    }

    public void setAlarm() {
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hr);
        calendar.set(Calendar.MINUTE, min);
        alarm.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                1000 * 60 * snoozeFreq, alarmIntent);
    }


}
