import net.hujian.jredis.JredisHandler;

/**
 * Created by hujian on 16-9-4.
 */
public class TestDemo {
    /**
     * print handler
     * @param out
     */
    public static void print(String out){
        System.out.println(out);
    }
    public static void main(String[] args){
        JredisHandler handler=JredisHandler.getInstance("127.0.0.1","6379",10000);
        //handler.flushDB();
        //handler.set("hujian","i am hujian");
        //print(handler.setExpire("hujian", 10000).toString());
        //print(handler.ttl("hujian").toString());
        handler.mset("a","aa","b","bb");
        for(String v:handler.mget("a","b")){
            print("mget->"+v);
        }
        //handler.setnx("hujian","i change");
        //print(handler.get("hujian"));
        handler.lpush("hujian","i am hujian");
        for(String v:handler.lrange("hujian",0,-1)){
            print("all=>"+v);
        }
        //handler.lpush("hujian","i am hujian bb");
        //print(handler.lpop("hujian"));
        print(handler.llen("hujian").toString());
        handler.ltrim("hujian",0,2);
        for(String v:handler.lrange("hujian",0,-1)){
            print("trim=>"+v);
        }
    }
}
