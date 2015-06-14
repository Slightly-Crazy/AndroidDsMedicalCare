package util;

import android.content.Entity;
import android.util.Log;
import android.widget.Toast;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by adrianlim on 15-06-13.
 */
public class ServerConnector {

    private HttpClient http;
    private String rootUrl;

    public ServerConnector(){
        http = new DefaultHttpClient();
        rootUrl = "http://ds-medical-care.meteor.com/api/";
    }

    public boolean authenticateUser(String username, String password) throws IOException, JSONException{
        Hashtable<String,String> userList = getUser(username);

        if (userList.get("password").equals(password)){
            Conf.currentUserId = userList.get("_id");
            Conf.currentUserName = userList.get("_username");
            return true;
        }
        else {
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

    public Hashtable<String,String> getUser(String username) throws IOException {
        URL url = new URL(rootUrl+"/parents/username="+username);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String[] output = sb.toString().split("\"");

        Hashtable<String,String> dict = new Hashtable<String, String>();
        LinkedList<String> list = new LinkedList<String>();
        int count = 0;
        int size = output.length/4;
        for (String out:output){
            if (count%2==1) {
                if (out.equals("data")) {
                }
                else {
                    list.add(out);
                }
            }

            count++;
        }
        for (int x=0; x<list.size()-1;x+=2){
            dict.put(list.get(x),list.get(x+1));
        }
        return dict;
    }
}
