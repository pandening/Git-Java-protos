package net.hujian.xmemcache;

import net.rubyeye.xmemcached.*;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KeyIteratorImpl;
import net.rubyeye.xmemcached.utils.AddrUtil;
import sun.security.x509.KeyIdentifier;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * this is the class to test the xmemcache
 */
public  class MemcacheHandler {
    /**
     * the only instance of this class
     */
    private static MemcacheHandler _instance = null;
    /**
     * the client builder
     */
    private MemcachedClientBuilder builder = null;
    /**
     * the client
     */
    private MemcachedClient xmemcacheClient = null;

    /**
     * you should offer the ip+port of the memcache server
     *
     * @param ip       the ip address
     * @param port     the port the memcache listen on
     * @param poolSize the connection pool size
     */
    private MemcacheHandler(String ip, String port, int poolSize) {
        /**
         * create the builder and get a client.
         */
        this.builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses(ip + ":" + port)
        );
        builder.setFailureMode(true);
        builder.setConnectionPoolSize(poolSize);
        /**
         * get the client
         */
        try {
            xmemcacheClient = builder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the only instance of this class.
     * @param ip the ip of memcache server
     * @param port the port
     * @param ps the pool size
     * @return the instance
     */
    public static MemcacheHandler getInstance(String ip,String port,int ps) {
        if(_instance==null){
            _instance=new MemcacheHandler(ip,port,ps);
        }
        return _instance;
    }

    /**
     * set key=value
     * @param key
     * @param value
     * @param timeout 0 means forever.(millis)
     * there is another way to set without replay
     */
    public  boolean set(String key,String value,int timeout){
        try {
            return _instance.xmemcacheClient.set(key,timeout,value);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get the key.(for string)
     * @param key
     * @param timeout
     * @return
     */
    public  String getString(String key,Long timeout){
        try {
            return _instance.xmemcacheClient.get(key,timeout);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * change the key's timeout value
     * @param key the key
     * @param newTimeOut the new time out value
     */
    public boolean touch(String key,int newTimeOut){
        try {
            return _instance.xmemcacheClient.touch(key, newTimeOut);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * get the key's value and change the time out value
     * @param key the key
     * @param timeout the new time out value
     */
    public void getAndTouch(String key,int timeout){
        try {
            _instance.xmemcacheClient.getAndTouch(key, timeout);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    /**
     * add a value
     * @param key
     * @param value
     * @param timeout
     * @return return false means add error.the key has existed
     */
    public boolean add(String key,String value,int timeout){
        try {
            return _instance.xmemcacheClient.add(key,timeout,value);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * append a new value
     * @param key
     * @param pd
     */
    public boolean append(String key,String pd){
        try {
            return _instance.xmemcacheClient.append(key,pd);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * delete the key-value,and no replay from memcache server
     * @param key
     */
    public void deleteWithNoReplay(String key){
        try {
            _instance.xmemcacheClient.deleteWithNoReply(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete with replay
     * @param key
     * @return
     */
    public boolean delete(String key){
        try {
            return _instance.xmemcacheClient.delete(key);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * add a value in the front of the old value
     * @param key
     * @param preP
     */
    public boolean prepend(String key,String preP){
        try {
            return _instance.xmemcacheClient.prepend(key,preP);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * replace the kay's value to nValue
     * @param key
     * @param nValue
     * @param timeout
     * @return
     */
    public boolean replace(String key,String nValue,int timeout){
        try {
            return _instance.xmemcacheClient.replace(key,timeout,nValue);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * update the key by cas
     * @param key
     * @param timeout
     * @param newV
     * @param cas
     */
    public boolean casUpdate(String key,int timeout,String newV,long cas){
        try {
            GetsResponse<Integer> result=_instance.xmemcacheClient.gets(key);
            long CAS=result.getCas();
            return _instance.xmemcacheClient.cas(key,timeout,newV,CAS);
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (MemcachedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * iterate to show the key
     */
    public void showKeyValues(){
        KeyIterator iterator=
                null;
        try {
            iterator = _instance.xmemcacheClient.getKeyIterator(AddrUtil.getOneAddress("127.0.0.1:12000"));
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        while(iterator.hasNext()){
            try {
                System.out.println("traversal:"+iterator.next());
            } catch (MemcachedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * shut down the client
     */
    public void shutDown(){
        if(_instance!=null){
            try {
                _instance.xmemcacheClient.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
