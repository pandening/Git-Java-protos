package net.hujian.jredis;


import redis.clients.jedis.*;

import java.util.*;

/**
 * Created by hujian on 16-9-4.
 */
public class JredisHandler {
    /**
     * this is the only instance of this class
     */
    private static JredisHandler _instance=null;
    /**
     * the local Jedis pool
     */
    private JedisPool     jedisPool=null;
    /**
     * the local jedis client
     */
    private Jedis          jedis=null;
    /**
     * the remote pool,for hash store
     */
    private ShardedJedisPool  shardedJedisPool=null;
    /**
     * the share remote Jedis client(hash to store)
     */
    private ShardedJedis  shardedJedis=null;

    /**
     * the simple constructor
     * @param ip the redis server's ip address
     * @param port the redis server listener port
     * @param timeout the connection timeout
     */
    private JredisHandler(String ip,String port,int timeout){
        /**
         * local pool
         */
        JedisPoolConfig jConfig=new JedisPoolConfig();
        jConfig.setMaxIdle(50);
        jConfig.setMaxIdle(10);
        jConfig.setMaxWaitMillis(timeout);
        jConfig.setTestOnBorrow(false);
        jedisPool=new JedisPool(jConfig,ip,Integer.parseInt(port));
        /**
         * remote pool,you can add the connection here
         */
        List<JedisShardInfo> shardInfos=new ArrayList<JedisShardInfo>();
        shardInfos.add(new JedisShardInfo(ip,6379,"master"));
        shardedJedisPool=new ShardedJedisPool(jConfig,shardInfos);
        /**
         * get the jedis and shared jedis client
         */
        jedis=jedisPool.getResource();
        shardedJedis=shardedJedisPool.getResource();
    }

    /**
     * get the only instance of this class
     * @param ip
     * @param port
     * @param timeout
     * @return
     */
    public static JredisHandler getInstance(String ip,String port,int timeout){
        if(_instance==null){
            _instance=new JredisHandler(ip,port,timeout);
        }
        return _instance;
    }

    /**
     * flush current db
     * @return
     */
    public String flushDB(){
        return jedis.flushDB();
    }

    /**
     * flush all.
     * @return
     */
    public String flushALL(){
        return jedis.flushAll();
    }

    /**
     * if exists
     * @param key
     * @return
     */
    public boolean exists(String key){
        return shardedJedis.exists(key);
    }

    /**
     * new key-value pair
     * @param key the new key
     * @param value the new value
     */
    public String set(String key,String value){
        return shardedJedis.set(key,value);
    }

    /**
     * if key not exist,set it.else do nothing
     * @param key
     * @param values
     * @return
     */
    public Long setnx(String key,String values){
        return shardedJedis.setnx(key,values);
    }

    /**
     * set the timeout values.
     * @param key
     * @param timeout
     * @param values
     */
    public void setex(String key,int timeout,String values){
        shardedJedis.setex(key,timeout,values);
    }

    /**
     * get the old value and set the new value
     * @param key
     * @param nV
     * @return
     */
    public String getSet(String key,String nV){
        return shardedJedis.getSet(key,nV);
    }

    /**
     * get a sub str of values
     * @param key
     * @param s start position
     * @param e end position
     * @return
     */
    public String sbStr(String key,int s,int e){
        return shardedJedis.getrange(key,s,e);
    }

    /**
     * get the all key of this db
     * @return return the total keys
     */
    public Set<String> keys(){
        return jedis.keys("*");
    }

    /**
     * if you want to show total keys
     */
    public void showKeys(){
        Set<String> keys=this.keys();
        Iterator<String> itr=keys.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }

    /**
     * delete the key from the redis
     * @param key
     * @return if not find just ignore
     */
    public Long delete(String ...key){
        return jedis.del(key);
    }

    /**
     * set the expire time,timeout will del
     * @param key
     * @param seconds
     * @return
     */
    public Long setExpire(String key,int seconds){
        return jedis.expire(key,seconds);
    }

    /**
     * check the time to live
     * @param key
     * @return
     */
    public Long ttl(String key){
        return jedis.ttl(key);
    }

    /**
     * get the key's store type
     * @param key
     * @return
     */
    public String type(String key){
        return jedis.type(key);
    }

    /**
     * get the key's value
     * @param key
     * @return
     */
    public String get(String key){
        return jedis.get(key);
    }

    /**
     * append
     * @param key
     * @param pd
     * @return new length
     */
    public Long append(String key,String pd){
        return jedis.append(key,pd);
    }

    /**
     * many keys-values
     * @param kvs
     * @return
     */
    public String mset(String ...kvs){
        return jedis.mset(kvs);
    }

    /**
     * many gets
     * @param keys
     * @return
     */
    public List<String> mget(String ...keys){
        return jedis.mget(keys);
    }

    /**
     * push a new value
     * @param key
     * @param itemV
     * @return
     */
    public Long lpush(String key,String itemV){
        return shardedJedis.lpush(key, itemV);
    }

    /**
     * get total data
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key,long start,long end){
        return shardedJedis.lrange(key,start,end);
    }

    /**
     * pop
     * @param key
     * @return
     */
    public String lpop(String key){
       return shardedJedis.lpop(key);
    }

    /**
     * get the length
     * @param key
     * @return
     */
    public Long llen(String key){
        return shardedJedis.llen(key);
    }

    /**
     * sort the list
     * @param key
     * @return
     */
    public List<String> sort(String key){
        return shardedJedis.sort(key);
    }

    /**
     * delete the values(count)
     * @param key
     * @param count
     * @param delV
     */
    public Long lrem(String key,int count,String delV){
        return shardedJedis.lrem(key,count,delV);
    }

    /**
     * trim..
     * @param key
     * @param start
     * @param end
     * @return
     */
    public String ltrim(String key,int start,int end){
        return shardedJedis.ltrim(key,start,end);
    }

    /**
     * close connection
     */
    public void close(){
        jedisPool.close();
        shardedJedisPool.close();
    }
}
