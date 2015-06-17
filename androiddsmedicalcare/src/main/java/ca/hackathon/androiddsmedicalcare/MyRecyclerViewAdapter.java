package ca.hackathon.androiddsmedicalcare;

/**
 * Created by adrianlim on 15-06-16.
 */
import android.app.AlarmManager;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView name;
        Button bedButton;
        Button awakeButton;
        Button trackButton;
        ImageButton alarmButton;
        ImageButton photoButton;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameView);
            bedButton = (Button) itemView.findViewById(R.id.bedButton);
            awakeButton = (Button) itemView.findViewById(R.id.awakeButton);
            trackButton = (Button) itemView.findViewById(R.id.trackButton);
            alarmButton = (ImageButton) itemView.findViewById(R.id.alarmButton);
            photoButton = (ImageButton) itemView.findViewById(R.id.photoButton);

            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        final String childid = mDataset.get(position).getChildid();

        holder.name.setText(mDataset.get(position).getName());
        holder.bedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BedTimeGUI.class);
                intent.putExtra("childId",childid);
                v.getContext().startActivity(intent);
            }
        });
        holder.awakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AwakeningActivity.class);
                intent.putExtra("childId", childid);
                v.getContext().startActivity(intent);
            }
        });
        holder.trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SurveyActivity.class);
                intent.putExtra("childId",childid);
                v.getContext().startActivity(intent);
            }
        });
        holder.alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReminderActivity.class);
                intent.putExtra("childId",childid);
                v.getContext().startActivity(intent);
            }
        });
        holder.photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Need special activity for this maybe.
            }
        });
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
