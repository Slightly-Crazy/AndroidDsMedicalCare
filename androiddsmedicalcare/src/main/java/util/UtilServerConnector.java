package util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by adrianlim on 15-06-14.
 */
public class UtilServerConnector {

    public static void sendFileToServer(String childId, String data) throws IOException{
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://example.com/"+childId);
        httpPost.setEntity(new StringEntity(data));

/*
 * Execute the HTTP Request
 */
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity respEntity = response.getEntity();

            if (respEntity != null) {
                // EntityUtils to get the response content
                String content =  EntityUtils.toString(respEntity);
            }
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }
    }
}
