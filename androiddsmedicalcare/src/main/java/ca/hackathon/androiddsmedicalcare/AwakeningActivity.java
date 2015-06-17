package ca.hackathon.androiddsmedicalcare;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AwakeningActivity extends ActionBarActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awakening);

        final Button button = (Button) findViewById(R.id.onclickAddnotsAwakening);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent noteactivity = new Intent(v.getContext(),notes.class);
                startActivity(noteactivity);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_awakening, menu);
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

    public void onButton(View view){
        Toast.makeText(getApplicationContext(),
                "Your Message", Toast.LENGTH_LONG).show();

    }

    public void onClickOkButton(View view){
        /*
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.happy_radiobutton:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.sad_radiobutton:
                if (checked)
                    // Ninjas rule
                    break;
        }
        */

        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        //intent.putExtra("EXTRA_ID", "SOME DATAS");
        startActivity(intent);
    }

    public void onClickAddNotesAwakening(View view){
        Intent intent = new Intent();
        intent.setClass(this, notes.class);
        startActivity(intent);

    }

}
