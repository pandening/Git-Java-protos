package net.hujian.network;

import org.apache.commons.lang.text.StrBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hujian on 2016/9/2.
 * you should offer an url,then the handler will get the content of
 * this url.(String)
 */
public class HttpHandler {
    /**
     * the listener
     */
    private OnHttpRequestResponseListener Listener=null;

    /**
     * set the listener
     * @param listener
     */
    public void setListener(OnHttpRequestResponseListener listener) throws IOException{
        this.Listener=listener;
        /**
         * after set the listener,just do it!
         */
        this.puppet();
    }
    /**
     * this is the input url
     */
    private String Url=null;
    /**
     * the response code
     */
    private int responseCode=-1;

    /**
     * the constructor,you should offer the url
     * @param url
     */
    public HttpHandler(String url){
        this.Url=url;
    }
    private void puppet()
    throws IOException{
        /**
         * create the url object
         */
        URL url_=new URL(this.Url);
        /**
         * get an connection
         */
        HttpURLConnection connection=(HttpURLConnection)url_.openConnection();
        /**
         * get
         */
        connection.setRequestMethod("GET");
        /**
         * set the codec
         */
        connection.setRequestProperty("Accept-Charset", "utf-8");
        /**
         * timeout
         */
        connection.setConnectTimeout(10000);
        /**
         * get the response code
         */
        this.responseCode=connection.getResponseCode();
        /**
         * 200 means ok
         */
        if(this.responseCode==200){
            /**
             * get the response text
             */
            InputStream is=connection.getInputStream();
            String content=this.getStringFromHttpStream(is);
            /**
             * notify
             */
            this.Listener.getContent(content);
        }
    }
    /**
     * this function will get the string type result from the
     * input stream
     * @param in the http stream
     * @return
     */
    private String getStringFromHttpStream(InputStream in) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(in,"utf-8"));
        StrBuilder content=new StrBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            content.append(line);
            content.append('\n');
        }
        return content.toString();
    }

    /**
     * the interface.you should implement the codes in your app
     */
    public interface OnHttpRequestResponseListener{
        /**
         * get the html content
         * @param html
         */
        void getContent(String html);
    }
}
