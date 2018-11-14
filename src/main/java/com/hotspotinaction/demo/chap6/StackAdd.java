package com.hotspotinaction.demo.chap6;

/**
 * Created by chenpeiwen on 2018/11/14
 */


//-server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*StackAdd.add
//这时候运行的话会报错，找不到hsdis-amd64.dll。可以到这里去下载hsdis-amd64.dll。把这个文件扔到%JAVA_HOME%\jre\bin\server下就好了，运行起来就能看到我们想要的东东了，
public class StackAdd {

    public int baseValue = 4095; // 0x0000000000000fff

    public static void main(String[] args) {
        StackAdd adder = new StackAdd();
        int i = 123; // 0x7b
        int j = 456; // 0x1c8
        int r = adder.add(i, j);
        System.out.println(r);
    }

    public int add(int i, int j) {
        int temp = i + j;
        temp += baseValue;
        return temp;
    }

}