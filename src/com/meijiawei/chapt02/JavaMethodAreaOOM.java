package com.meijiawei.chapt02;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 借助CGLib使方法区出现内存溢出异常
 *
 * OLD: -XX:PermSize=10M -XX:MaxPermSize=10M
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 *
 * java.lang.OutOfMemoryError: Metaspace
 * 1.7以下会java.lang.OutOfMemoryError: PermGen space
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args){
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> proxy.invoke(obj, args1));
            enhancer.create();
        }
    }

    static class OOMObject {

    }
}
