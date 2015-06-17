package util;

import android.app.Application;

/**
 * Created by adrianlim on 15-06-13.
 */
public class Conf{
    private static Conf mInstance = null;

    public String currentUserName = "";
    public String currentUserId = "";
    public String currentChildName = "";
    public String currentChildId = "";

    protected Conf(){}

    public static synchronized Conf getmInstance(){
        if (null ==mInstance){
            mInstance = new Conf();
        }
        return mInstance;
    }
}
