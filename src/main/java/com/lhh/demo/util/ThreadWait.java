package com.lhh.demo.util;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

//史上的生产者消费者模式
//测试wait方法的同步块锁对象是否需要与调用wait的对象一致： 答案为需要一致
//实验：1.将同步块对象换成arrayList,wait与notify方法使用arrayList调用
//实验：2.将同步块对象换成lockObject,wait与notify方法使用arrayList调用
//实验：3.将同步块对象换成lockObject,wait与notify方法使用lockObject调用
public class ThreadWait {
    private static Object lockObject = new Object();
    private static int n;
    private static int i = 0;
    private final static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] arg) {
        Thread produce = new Thread(() -> {
            while (i < 20) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                synchronized (arrayList) {
                    System.out.println("produce i:" + i + " and notify");
                    arrayList.add(i);
                    i++;
                    arrayList.notify();
                }
            }
            System.out.println("produce over,exit Thread");
        });
        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (arrayList) {
                    if (arrayList.size() == 0) {
                        try {
                            System.out.println("consumer Thread wait to arrayList not null");
                            arrayList.wait();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    System.out.println("consumer :" + arrayList.get(0));
                    arrayList.remove(0);
                }
            }
        });
        produce.start();
        try{
            Thread.sleep(3);
        }catch (Exception e) {
            e.printStackTrace();
        }
        consumer.start();
        n = 100;
        FutureTask ft = new FutureTask<>(new MyCallable());   //FutureTask实际上是一个runnable和future接口的实现类,实际在run方法中调用callable的call方法
                                                            //并将返回值记录在成员变量中，达到有返回值的线程。并实现Future任务的接口功能，如取消任务（线程interrupt）等
        new Thread(ft).start();
        System.out.println("main Thread");
        try {
            int result = (Integer) ft.get();    //这一步main线程会进入死循环，等待子线程callable call方法返回
            System.out.println(n+ "的阶加：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for(int i = 0; i < n; i++) {
                sum += n;
            }
            Thread.sleep(5000);
            System.out.println("callable Thread and has sleep 5s");
            return sum;
        }
    }
}
