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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import util.Conf;


public class BedTimeGUI extends Activity {
    private int radioId;
    private static PendingIntent alarmIntent;

    private int date;
    private int month;
    private int year;
    private String time;
    private String notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.date = extras.getInt("date");
           this.month = extras.getInt("month");
            this.year = extras.getInt("year");
        }


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
        intent.setClass(this, MainActivity.class);
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


    public void setTime(JSONObject event) throws JSONException {
        JSONObject dateObject = new JSONObject();
        dateObject = event.getJSONObject("timeStamp");

        //String date = new String();
        String date = dateObject.getString("label");
       // "2005-10-05T00:00:00.000Z",
        int t_index = 0;
        for (int i = 0; i < date.length(); i++) {
            char item = date.charAt(i);
            String item_str = Character.toString(item);
            if (item_str.equals("T")) {
                t_index = i;
            }
        }
        //String result = new String();
        String result = date.substring(t_index,date.length());
        this.time =  result;
    }

    public String getTime(){
        return this.time;
    }


    public void setNotes(JSONObject event) throws JSONException {

        //Conf conf = new Conf();
        //JSONObject notesObject = new JSONObject();
        JSONObject notesObject = event.getJSONObject("note");

        //String notes = new String();
        String notes = notesObject.getString("label");
        this.notes = notes;



    }


    public String getNotes(){
        return notes;
    }
    public void onClickAddnotesBedTime(View viewe){
        Intent intent = new Intent();
        intent.setClass(this, notes.class);

        //intent.putExtra("EXTRA_ID", "SOME DATAS");

        intent.putExtra("year", year);
        intent.putExtra("date", date);
        intent.putExtra("month", month);
        startActivity(intent);
    }
}
