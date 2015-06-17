package ca.hackathon.androiddsmedicalcare;

import Events.Child;

/**
 * Created by adrianlim on 15-06-16.
 */
public class DataObject {
    private String name;

    DataObject (Child child){
        name = child.getFirstname();
    }

    public String getName() {
        return name;
    }

    public void setName(String newname) {
        this.name = newname;
    }

}
