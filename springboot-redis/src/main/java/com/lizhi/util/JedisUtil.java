package com.lizhi.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class JedisUtil {

    @Resource
    private JedisPool jedisPool;

    /**
     * 获得连接的方法
     * @return
     */
    private Jedis getResouce(){
        return jedisPool.getResource();
    }

    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = getResouce();
        try {
            jedis.set(key, value);
            return value;
        }finally{
            jedis.close();
        }
    }

    public void expire(byte[] key, int i) {
        Jedis jedis = getResouce();
        try {
            jedis.expire(key ,i);
        }finally{
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getResouce();
        try {
           return jedis.get(key);
        }finally{
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis = getResouce();
        try {
            jedis.del(key);
        }finally{
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_session_prefix) {
        Jedis jedis = getResouce();
        try {
            return jedis.keys((shiro_session_prefix+"*").getBytes());
        }finally{
            jedis.close();
        }
    }
}
