package com.asm;

public class Stack {
    public static void main(String args[]) {
        int i = 0;
        System.out.println();
        int m = 1024*1024;
        byte[] b =  new byte[2*m];


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stack(String[] args){
        String str = "nani";
        if(str.equals("nani")){
            int i = 3;
            while (i>0){
                long j = i;
                i--;
            }
        }else {
            char b = 'a';
            System.out.println(b);
        }
    }
}
