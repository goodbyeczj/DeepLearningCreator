package com.rkb.runnable;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 18-12-20 下午12:43
 */
@Component
public class InterruptRunnable implements Runnable {
    public volatile boolean isOver = false;

    @Override
    public void run() {
        while (!isOver) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "...run");
        }
    }
}
