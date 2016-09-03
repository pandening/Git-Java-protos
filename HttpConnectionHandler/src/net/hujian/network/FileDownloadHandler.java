package net.hujian.network;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class FileDownloadHandler {
    private String Link=null;
    private String StorePath=null;
    private OnDownloadFileListener Listener=null;

    /**
     * the listener
     * @param listener
     */
    public void setListener(OnDownloadFileListener listener){
        this.Listener=listener;
        System.out.println("get the listener");
        this.puppet();
    }
    /**
     * the constructor
     * @param link
     * @param store
     */
    public FileDownloadHandler(String link,String store){
        this.Link=link;
        this.StorePath=store;
    }

    /**
     * the puppet function.
     * always run after get the listener
     */
    public void puppet(){
        byte[] btImg = getImageFromNetByUrl(this.Link);
        if(null != btImg && btImg.length > 0){
            System.out.println("download=>" + btImg.length/(1024.0*1024.0) + " Mbs");
            String fileName =this.StorePath;
            writeImageToDisk(btImg, fileName);
            this.Listener.complete(this.StorePath);
        }else{
            System.out.println("the link is empty!");
        }
    }
    public static void writeImageToDisk(byte[] img, String fileName){
        try {
            File file = new File(fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[] getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            InputStream inStream = conn.getInputStream();
            byte[] btImg = readInputStream(inStream);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
    /**
     * the interface
     */
    public interface OnDownloadFileListener{
        /**
         * you should get the file at the filePath
         * @param filePath
         */
        public void complete(String filePath);
    }
}