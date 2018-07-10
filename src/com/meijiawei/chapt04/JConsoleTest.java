package com.meijiawei.chapt04;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试JConsole监视
 *
 * VM参数：-Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class JConsoleTest {
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for(int i = 0; i < num ; i++){
            Thread.sleep(50);

            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }

    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }
}
