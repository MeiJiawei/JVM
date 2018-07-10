package com.meijiawei.chapt02;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致的内存溢出异常 常量池jdk1.8已经移到堆里面
 *
 * OLD: -XX:PermSize=10M -XX:MaxPermSize=10M
 * VM Args: -Xmx1m -XX:-UseGCOverheadLimit -XX:+PrintGCDetails
 *
 * 1.8   java.lang.OutOfMemoryError: Java heap space
 * 1.7   java.lang.OutOfMemoryError: PermGen space
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        int i = 0;
        while(true){
            //String.intern()是一个Native方法 有则返回 无则添加到常量池
            list.add(String.valueOf(i++).intern());
        }
    }
}
