package com.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class notify {

    private static final String syncObj = "";

    ConcurrentHashMap<String,String> kk = new ConcurrentHashMap<String,String>();

    public static void main(String args[]){
        System.out.println("sdf");
        new Thread(new Runnable() {
            public void run() {
                for(;;){

                    try{
                        synchronized (syncObj){
                        System.out.println("before wait...");
                        syncObj.wait();
                        System.out.println("after wait...");
                        }
                        Thread.sleep(2000);}catch(Exception e){}
                       // break;
                }
            }
        }).start();

        for(;;){
            try{
                synchronized (syncObj){
                syncObj.notifyAll();
                System.out.println("notify");}
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }



    }
}
