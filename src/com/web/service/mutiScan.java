package com.web.service;

import com.web.ipInfo.ipInfo;

import com.web.socket.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class mutiScan {
    public static List<ipInfo> startScan(String address, int startPort, int endPort) throws InterruptedException {
        Vector<Thread> threadVector = new Vector<Thread>();
        List<ipInfo> scanResults = new ArrayList<>();

        for (int i = startPort; i < endPort; i++) {
            int finalI = i;

            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int success = socket.scan(address, finalI);
                        if (success == 1) {
                            scanResults.add(new ipInfo(address, finalI, true, "未知", "TCP"));
                        } else if (success == -1) {
                            scanResults.add(new ipInfo(address, finalI, true, "未知", "UCP"));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            threadVector.add(childThread);
            childThread.start();
        }


        //阻塞主线程，等待所有子线程执行完毕后回调。
        for (Thread thread : threadVector) {
            thread.join();
        }

        System.out.println("子线程执行完毕");
        System.out.println("list长度" + scanResults.size());

        return scanResults;
    }
}
