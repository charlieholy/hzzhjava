package com.reg;

public class Reg {

    static class RegNormal{
       public int pc = 0;
       public int cpsr = 0;

        @Override
        public String toString() {
            return " pc: " + pc + " cpsr " + cpsr;
        }
    }
    static class RegFiq{
        public int lr = 0;
        public int spsr = 0;

        @Override
        public String toString() {
            return " lr " + lr + " spsr " + spsr;
        }
    }

    public static RegNormal regNormal;
    public static RegFiq regFiq;

    static synchronized void irq(){
        if(Reg.regNormal.cpsr == 0) {
            Reg.regFiq.spsr = Reg.regNormal.cpsr;
            Reg.regFiq.lr = Reg.regNormal.pc;

            //System.out.println("irq: " + regFiq.toString());

            Reg.regNormal.cpsr = 1;
            Reg.regNormal.pc = 100;
        }
    }
    static synchronized void normal(){
        if(Reg.regNormal.cpsr == 1 && Reg.regNormal.pc == 100){
            Reg.regNormal.cpsr = Reg.regFiq.spsr;
            Reg.regNormal.pc = Reg.regFiq.lr;
            System.out.println("irq");
            //System.out.println("normal: " + regNormal.toString());
        }


        //System.out.println("regNo: " + regNormal.toString());
    }

    public static synchronized void pc(){
        if(Reg.regNormal.cpsr == 0){
            System.out.println("pc " + Reg.regNormal.pc++);
        }
    }


    public static void main(String[] args) {
        System.out.println("nani");

        regNormal = new RegNormal();
        regFiq = new RegFiq();
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    pc();
                try{Thread.sleep(1000);}catch (Exception e){}
            }}
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try{Thread.sleep(1000);}catch (Exception e){}

                        normal();



                }
            }
        }).start();

        while (true){
            //set irq
            irq();
            try{Thread.sleep(1000);}catch (Exception e){}
        }
    }


}
