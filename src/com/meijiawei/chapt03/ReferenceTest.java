package com.meijiawei.chapt03;

import java.lang.ref.*;
import java.util.HashSet;
import java.util.Set;

public class ReferenceTest {
    public static void main(String[] args) {
//        testSoftReference();
        test();
    }

    public static void testSoftReference() {
        //这就是一个强引用
        String str = "hello";

        //现在我们由上面的强引用创建一个软引用,所以现在str有两个引用指向它
        SoftReference<String> soft = new SoftReference<>(str);
        str = null;

        System.gc();
        System.gc();

        //可以使用get()得到引用指向的对象
        System.out.println(soft.get());//输出hello

    }

    public static ReferenceQueue<Store> queue = new ReferenceQueue<Store>();

    public static void checkQueue() {
        if (queue != null) {
            @SuppressWarnings("unchecked")
            Reference<Store> ref = (Reference<Store>) queue.poll();
            if (ref != null)
                System.out.println(ref + "......" + ref.get());
        }
    }


    public static void test() {
        //创建10个软引用 软引用主要用户实现类似缓存的功能
        Set<SoftReference<Store>> hs1 = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            SoftReference<Store> soft = new SoftReference<Store>(new Store("soft" + i), queue);
            System.out.println("create soft" + soft.get());
            hs1.add(soft);
        }
        System.gc();
        checkQueue();

        //创建10个弱引用 弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾
        Set<WeakReference<Store>> hs2 = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            WeakReference<Store> weak = new WeakReference<>(new Store("weak" + i), queue);
            System.out.println("create weak" + weak.get());
            hs2.add(weak);
        }

        System.gc();
        checkQueue();


        //创建10个虚引用 虚引用主要用于检测对象是否已经从内存中删除
        Set<PhantomReference<Store>> hs3 = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            PhantomReference<Store> phantom = new PhantomReference<>(new Store("phantom" + i), queue);
            System.out.println("create phantom  " + phantom.get());
            hs3.add(phantom);
            phantom.isEnqueued();
        }
        System.gc();
        checkQueue();
    }

}

class Store {
    public static final int SIZE = 10000;
    private double[] arr = new double[SIZE];
    private String id;

    public Store() {

    }

    public Store(String id) {
        super();
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(id + "被回收了");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;

    }
}
