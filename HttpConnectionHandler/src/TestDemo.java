import net.hujian.network.HttpHandler;

import java.io.IOException;

/**
 * Created by hujian on 2016/9/2.
 */
public class TestDemo {
    public static void main(String[] args){
        try {
            HttpHandler http=new HttpHandler("http://www.nankai.edu.cn");
            http.setListener(new HttpHandler.OnHttpRequestResponseListener() {
                @Override
                public void getContent(String html) {
                    System.out.print(html);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
