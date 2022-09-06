package com.mszlu.blog.test;

/**
 * @author ginga
 * @version 1.0
 * @ClassName MyDemo
 * @Date 6/9/2022 下午3:25
 */
public class MyDemo {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String content;

    public String getContent() {
        return threadLocal.get();
    }

    public void setContent(String content) {
//        this.content = content;
        threadLocal.set(content);
    }

    public static void main(String[] args) {

        final MyDemo myDemo = new MyDemo();

        for (int i = 0; i < 5; i++) {
            final Thread thread = new Thread(() -> {
                myDemo.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("******************");
                System.out.println(Thread.currentThread().getName() + "---->" + myDemo.getContent());
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
