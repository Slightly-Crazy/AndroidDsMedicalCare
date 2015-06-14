package util;

import android.content.Entity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by adrianlim on 15-06-13.
 */
public class ServerConnector {

    private HttpClient http;
    private String rootUrl;

    public ServerConnector(){
        http = new DefaultHttpClient();
        rootUrl = "ds-medical-care.meteor.com/api/";
    }

    public boolean authenticateUser(String username, String password) throws IOException, JSONException{
        JSONObject user = getJson("parents/"+username);
        String serverpassword = user.get("password").toString();
        if (password.equals(username)){
            Conf.currentUserId = user.get("_id").toString();
            Conf.currentUserName = user.get("username").toString();
            return true;
        }
        else{
            return false;
        }
    }

    public void pushNote(String childName, String date, String note){
        //stuff....
    }

    private Dictionary getSuper(String parentId) throws IOException, JSONException{
        InputStream inputStream = null;
        String result;

        String url = rootUrl + "superparents/"+parentId;
        HttpResponse httpResponse = http.execute(new HttpGet(url));
        inputStream = httpResponse.getEntity().getContent();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        JSONObject superobject = getJson("superparents/"+parentId);
        String superstring = superobject.toString();

        Dictionary map = new Gson().fromJson(superstring, new TypeToken<HashMap>() {}.getType());
        return map;
    }

    private JSONObject getJson(String uri) throws IOException,JSONException{
        InputStream inputStream = null;
        String result = "";

        String url = rootUrl + uri;
        HttpResponse httpResponse = http.execute(new HttpGet(url));
        inputStream = httpResponse.getEntity().getContent();

        if(inputStream != null)
            result = convertInputStreamToString(inputStream);
        else
            result = "Did not work!";

        JSONObject jsonResult = new JSONObject(result);

        return jsonResult;
    }

    private void sendJson(String uri, HashMap map) throws Exception{
        String path = rootUrl+uri;
        HttpPost httpost = new HttpPost(path);
        JSONObject holder = getJsonObjectFromMap(map);
        StringEntity se = new StringEntity(holder.toString());
        httpost.setEntity(se);
        httpost.setEntity(se);
        //sets a request header so the page receving the request
        //will know what to do with it
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");

        //Handles what is returned from the page
        ResponseHandler responseHandler = new BasicResponseHandler();
        http.execute(httpost, responseHandler);
    }

    private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {

        //all the passed parameters from the post request
        //iterator used to loop through all the parameters
        //passed in the post request
        Iterator iter = params.entrySet().iterator();

        //Stores JSON
        JSONObject holder = new JSONObject();

        //using the earlier example your first entry would get email
        //and the inner while would get the value which would be 'foo@bar.com'
        //{ fan: { email : 'foo@bar.com' } }

        //While there is another entry
        while (iter.hasNext())
        {
            //gets an entry in the params
            Map.Entry pairs = (Map.Entry)iter.next();

            //creates a key for Map
            String key = (String)pairs.getKey();

            //Create a new map
            Map m = (Map)pairs.getValue();

            //object for storing Json
            JSONObject data = new JSONObject();

            //gets the value
            Iterator iter2 = m.entrySet().iterator();
            while (iter2.hasNext())
            {
                Map.Entry pairs2 = (Map.Entry)iter2.next();
                data.put((String)pairs2.getKey(), (String)pairs2.getValue());
            }

            //puts email and 'foo@bar.com'  together in map
            holder.put(key, data);
        }
        return holder;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
