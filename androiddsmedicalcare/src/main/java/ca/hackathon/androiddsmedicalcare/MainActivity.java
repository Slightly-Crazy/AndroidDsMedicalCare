package ca.hackathon.androiddsmedicalcare;

/**
 * Created by adrianlim on 15-06-16.
 */
import Events.Child;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import util.Conf;
import util.ServerConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MainActivity";
    private ServerConnector se = new ServerConnector();
    private LinkedList<Child> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            childList = se.getChildrenOfParent(Conf.getmInstance().currentUserId);
        }  catch (IOException e){
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new
      MyRecyclerViewAdapter.MyClickListener() {
          @Override
          public void onItemClick(int position, View v) {
              Log.i(LOG_TAG, " Clicked on Item " + position);
          }
      });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        int counter = 0;
        for (Child child:childList) {
            DataObject obj = new DataObject(child);
            results.add(counter, obj);
            counter ++;
        }
        return results;
    }
}