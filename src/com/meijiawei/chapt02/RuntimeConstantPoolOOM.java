package com.meijiawei.chapt02;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致的内存溢出异常
 *
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * java1.7以及以上不会有问题
 * 1.7以下会java.lang.OutOfMemoryError: PermGen space
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
