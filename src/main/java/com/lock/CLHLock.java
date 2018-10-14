package com.lock;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class CLHLock {
    public static class CLHNode{
        private boolean isLocked = true; //

    }

    public static void main(String[] args) {
        System.out.println();
    }

    private volatile  CLHNode tail;
    private static final AtomicIntegerFieldUpdater<CLHLock.CLHNode> UPDATER = AtomicIntegerFieldUpdater
            .newUpdater(CLHNode.class,"tail");
}
