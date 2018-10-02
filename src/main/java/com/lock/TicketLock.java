package com.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketLock {
    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger ticketNum = new AtomicInteger();
    public int lock(){
        int myTocketNum = ticketNum.getAndIncrement();
        while (serviceNum.get() != myTocketNum){}
        return myTocketNum;
    }
    public void unlock(int myTicket){
        int next = myTicket + 1;
        serviceNum.compareAndSet(myTicket,next);
    }
}
