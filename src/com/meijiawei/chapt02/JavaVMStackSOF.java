package com.meijiawei.chapt02;

/**
 * 线程请求的栈深度大于虚拟机所允许的最大深度 StackOverflowError
 *
 * 栈容量
 * VM Args: -Xss128k
 *
 * 异常信息：java.lang.StackOverflowError
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args){
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();

        try{
            javaVMStackSOF.stackLeak();
        } catch (Throwable e){
            System.out.println(javaVMStackSOF.stackLength);
            throw e;
        }
    }
}
