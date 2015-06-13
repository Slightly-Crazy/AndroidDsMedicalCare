package Events;

import java.sql.Time;

/**
 * Created by wendywang on 15-06-13.
 */


public class Events {

    private String name;
    private Time time;

    public Events(String name, Time time){
        this.name = name;
        this.time = time;
    }

    public Events(Time time){
        this.time = time;
    }

    public void setName(String eventName){
        name = eventName;
    }

    public void setTime(Time eventTime){
        time = eventTime;
    }

    public String getName(){
        return name;
    }

    public Time getTime(){
        return time;
    }




}
