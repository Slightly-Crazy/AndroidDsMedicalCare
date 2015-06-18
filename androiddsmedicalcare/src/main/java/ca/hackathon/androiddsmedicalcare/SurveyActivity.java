package ca.hackathon.androiddsmedicalcare;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class SurveyActivity extends Activity {
    private static PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("SurveyActivity", "ping");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        // Ask to disable alarm in AlarmSetter
        //Intent disableAlarmIntent = new Intent(this, AlarmSetter.class);
        //disableAlarmIntent.putExtra("sender", "SurveyActivity");
        //alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, disableAlarmIntent, 0);
        // Send Intent immediately
        //AlarmManager alarm = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
        //alarm.set(AlarmManager.RTC, 0, alarmIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
