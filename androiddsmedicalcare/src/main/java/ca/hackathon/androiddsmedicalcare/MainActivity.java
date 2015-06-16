package ca.hackathon.androiddsmedicalcare;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import Events.Child;
import Events.CustomerAdapter;
import util.AlarmReceiver;
import util.AlarmSetter;


public class MainActivity extends ActionBarActivity {

    private AlarmManager alarm;
    private AlarmSetter alarmSetter;

    private int hr;
    private int min;
    private int snoozeFreq;

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

        Child[] children = new Child[2];

        children[0] = new Child("afirst","alast","dob","gender","parentId","bedTime","id");
        children[1] = new Child("bfirst","blast","dob","gender","parentId","bedTime","id");

        ListAdapter buckysAdapter = new CustomerAdapter(this, children);
        ListView buckysListView = (ListView) findViewById(R.id.childListView);
        System.out.println("BUBUBUBUBUBUBUBUB#########");
        System.out.println(buckysListView);

        if (buckysListView != null){
            buckysListView.setAdapter(buckysAdapter);
        }

        buckysListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
                    }
                }
        );
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

    public void onClickSBD(View view){
        Toast.makeText(getApplicationContext(), "SBD button is clicked", Toast.LENGTH_SHORT).show();
    }

    public void onClickSummary(View view){
//        Toast.makeText(getApplicationContext(), "Summary is clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, Summary.class);
        startActivity(intent);
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