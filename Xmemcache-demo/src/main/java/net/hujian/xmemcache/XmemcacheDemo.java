package net.hujian.xmemcache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * Created by hujian on 16-9-4.
 * test the xmemcache(a client for memcached)
 */
public class XmemcacheDemo {
    /**
     * print a string to console
     * @param str
     */
    public static void print(String str){
        System.out.println(str);
    }
    public static void main(String[] args) throws IOException{
        MemcacheHandler handler=MemcacheHandler.getInstance("127.0.0.1","12000",10);
        handler.set("hujian","you are hujian",0);
        handler.replace("hujian","i am hujian",0);
        print(handler.getString("hujian",100L));
        handler.append("hujian","yes");
        handler.prepend("hujian","no");
        print(handler.getString("hujian",100L));
        handler.showKeyValues();


    }
}
