package util;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by adrianlim on 15-06-13.
 */
public class ServerConnector {

    private HttpClient http;
    private String rootUrl;

    public ServerConnector(){
        http = new DefaultHttpClient();
        rootUrl = "";
    }

    public JSONObject getJson(String uri) throws IOException,JSONException{
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

    public void sendJson(String uri, JSONObject json){
        String url = rootUrl + uri;
        // Fill this in!
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
