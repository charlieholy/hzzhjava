package com.test;

public class notify {

    private static final String syncObj = "";

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
