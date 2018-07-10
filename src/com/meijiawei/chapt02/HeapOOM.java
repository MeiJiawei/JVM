package com.meijiawei.chapt02;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出异常测试
 *
 * -Xms最小堆容量 -Xmx最大堆容量 dump内存堆转储快照
 * VM Args:
 * -Xms20m -Xmx20m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -XX:+PrintGCApplicationStoppedTime
 * -XX:+PrintGCApplicationConcurrentTime
 * -XX:+PrintHeapAtGC
 *
 * 异常信息：java.lang.OutOfMemoryError: Java heap space
 */
public class HeapOOM {
    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();

        while(true){
            list.add(new OOMObject());

//            Thread.sleep(1000);
        }
    }

    static class OOMObject {

    }
}
