package ca.hackathon.androiddsmedicalcare;

import Events.Child;
import android.graphics.Bitmap;

/**
 * Created by adrianlim on 15-06-16.
 */
public class DataObject {
    private String name;
    private String childid;
    private Bitmap image;

    DataObject (Child child){
        name = child.getFirstname();
        childid = child.getId();
        image = child.getImage();

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String newname) {
        this.name = newname;
    }

    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid;
    }
}
