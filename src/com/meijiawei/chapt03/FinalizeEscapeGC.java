package com.meijiawei.chapt03;

/**
 * 1. 一个对象自我拯救的演示
 * 2. 自救机会只有一次 因为一个对象的finalize()方法最多执行一次
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am still alive !");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        System.out.println("finalize method executed");
        //拯救自己
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        //finalize
        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead !");
        }

        //对象第二次拯救自己失败
        SAVE_HOOK = null;
        System.gc();

        //finalize
        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead !");
        }
    }
}
