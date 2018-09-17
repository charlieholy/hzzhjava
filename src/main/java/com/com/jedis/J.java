package com.com.jedis;

import redis.clients.jedis.Jedis;

public class J {
    public static void main(String args[]){
        System.out.println("j");
        //连接redis服务器，192.168.0.100:6379
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String g = jedis.get("gg");
        System.out.println("g: " + g);
        return;
    }
}
