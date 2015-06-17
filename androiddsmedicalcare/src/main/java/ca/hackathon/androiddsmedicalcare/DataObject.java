package ca.hackathon.androiddsmedicalcare;

import Events.Child;

/**
 * Created by adrianlim on 15-06-16.
 */
public class DataObject {
    private String name;
    private String childid;

    DataObject (Child child){
        name = child.getFirstname();
        childid = child.getId();
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
