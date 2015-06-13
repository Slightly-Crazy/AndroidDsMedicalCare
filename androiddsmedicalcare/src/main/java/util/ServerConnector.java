package util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

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

    public String getJson(String uri) throws Exception{
        InputStream inputStream = null;
        String result = "";

        String url = rootUrl + uri;
        HttpResponse httpResponse = http.execute(new HttpGet(url));
        return null;
    }
}
