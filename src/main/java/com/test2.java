package com;
import java.util.HashSet;


class Kl{
    public Kl(String a){m=a;}
    private String m = "";

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return m.hashCode();
    }
}

public class test2 {

    public static void main(String args[]){
        StringBuilder sb = new StringBuilder();
        HashSet<Kl> j = new HashSet();
        Kl a = new Kl("a");
        Kl a2 = new Kl("a");
        System.out.println(a.hashCode());
        System.out.println(a2.hashCode());
        j.add(a);
        j.add(a2);
        System.out.println("j size: " + j.size());
    }


}
