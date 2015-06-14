package ca.hackathon.androiddsmedicalcare;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import util.AlarmReceiver;
import util.AlarmSetter;
import util.UtilServerConnector;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private AlarmManager alarm;
    private AlarmSetter alarmSetter;

    private int hr;
    private int min;
    private int snoozeFreq;

    private int date;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        hr = 15;
        min = 10;
        snoozeFreq = 30;
        setAlarm();
        setCurrDate();

    }

    public void onClickCamera(View view){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, baos);

        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        ImageButton btn = (ImageButton)findViewById(R.id.portraitButton);
        btn.setImageBitmap(bp);

        try {
            UtilServerConnector.sendFileToServer("", imageEncoded);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onClickBedtime(View view){

        Intent intent = new Intent();
        intent.setClass(this, BedTimeGUI.class);
        //intent.putExtra("EXTRA_ID", "SOME DATAS");
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "button is clicked", Toast.LENGTH_SHORT).show();
    }

    public void onClickAwakening(View view){
        Intent intent = new Intent();
        intent.setClass(this, AwakeningActivity.class);
        //intent.putExtra("EXTRA_ID", "SOME DATAS");
        startActivity(intent);
    }

    public void onClickCalendarButton (View view) {
        Toast.makeText(getApplicationContext(), "Summary is clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, Summary.class);
        startActivity(intent);
    }

    public void onClickSBD(View view){
        Toast.makeText(getApplicationContext(), "SBD button is clicked", Toast.LENGTH_SHORT).show();
    }

    public void onClickAlert(View view){
        Toast.makeText(getApplicationContext(), "Alert is clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, ReminderActivity.class);
        startActivity(intent);
//
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void setAlarm() {
        alarm = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        alarmSetter = new AlarmSetter(hr, min, snoozeFreq, this, alarm);
        alarmSetter.setAlarm();
    }


    public void setCurrDate() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date = extras.getInt("date");
            month = extras.getInt("month");
            year = extras.getInt("year");
        } else {
            Calendar cal = Calendar.getInstance();
            date = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            year = cal.get(Calendar.DATE);
        }

        Log.i("TEXT", "Text");

        TextView textView = (TextView) findViewById(R.id.date);
        textView.setText(date + " " + month + " " + year);
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

    public void setDate(int date) {
        this.date = date;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }


    }



}