package com.unitT;

public class TestPrductor {

    @Before
    public void testBefore(){
        System.out.println("测试before");
    }

    @Test
    public void testAdd(){
        System.out.println("测试添加");
    }
    @Test
    public void testDel(){
        System.out.println("测试删除");
    }
    @After
    public void testAfter(){
        System.out.println("测试after");
    }

}