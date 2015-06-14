package Events;


        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import ca.hackathon.androiddsmedicalcare.R;

public class CustomerAdapter extends ArrayAdapter<Child> {

    public CustomerAdapter(Context context, Child[] childArray) {
        super(context, R.layout.child_row ,childArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.child_row, parent, false);

        Child childItem = getItem(position);
        TextView childInfo = (TextView) customView.findViewById(R.id.childInfo);
        ImageView childImage = (ImageView) customView.findViewById(R.id.childImage);

        childInfo.setText(childItem.getInfo());
        //childImage.setImageResource(R.drawable.chunky);
        return customView;
    }
}
