package com.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {


    private AtomicReference<Integer> owner = new AtomicReference<Integer>();

    public static void main(String[] args) {
        Integer j = 1;
        Integer k = 2;
        SpinLock spinLock = new SpinLock();
        boolean isok = spinLock.owner.compareAndSet(null,k);
        System.out.println("isOk: " + isok);
        isok = spinLock.owner.compareAndSet(k,j);
        System.out.println("isOK: " + isok);

        System.out.println("j: " + j);
        System.out.println("k: " + k);

    }

}
