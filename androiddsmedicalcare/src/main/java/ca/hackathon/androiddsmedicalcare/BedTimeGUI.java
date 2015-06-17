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
import android.view.View;
import android.widget.TimePicker;


public class BedTimeGUI extends Activity {
    private int radioId;
    private static PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("BedTimeGUI", "ping");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_time_gui);
        // Ask to disable alarm in AlarmSetter
        Intent disableAlarmIntent = new Intent(this, AlarmSetter.class);
        disableAlarmIntent.putExtra("sender", "BedTimeGUI");
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, disableAlarmIntent, 0);
        // Send Intent immediately
        AlarmManager alarm = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
        alarm.set(AlarmManager.RTC, 0, alarmIntent);
    }



    public void onClickRadio(View view){

        int radioId = view.getId();

        }

    public void onClickOkButton(View view){

        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);

        int hr = tp.getCurrentHour();
        int min = tp.getCurrentMinute();

        String Bedtime = hr + ":" + min;

        String mood;
        switch(radioId) {
            case R.id.happy_radiobutton:
                    mood = "happy";
                    break;
            case R.id.sad_radiobutton:
                    mood = "sad";
                    break;
            default:
                break;
        }

        Intent intent = new Intent();
        intent.setClass(this, MainActivity1.class);
        //intent.putExtra("EXTRA_ID", "SOME DATAS");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bed_time_gui, menu);
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

    public void onClickAddnotesBedTime(View viewe){
        Intent intent = new Intent();
        intent.setClass(this,notes.class);
        startActivity(intent);
    }
}
