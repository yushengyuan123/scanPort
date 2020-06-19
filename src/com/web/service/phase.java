package com.web.service;

import com.web.ipInfo.ipInfo;
import com.web.socket.socket;
import utils.ipStatistic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class phase {
    public static List<ipInfo> dealPhase(String startAddress, String endAddress , int port) throws InterruptedException {
        List<ipInfo> availableIp = new ArrayList<>();
        List<String> reachAbleList;
        Vector<Thread> threadVector = new Vector<Thread>();

        //获取所有可到达的ip地址列表
        reachAbleList = ipStatistic.findIPs(startAddress, endAddress);
        //查询可达到的ip地址，移除不可达的
        for (int i = 0; i < reachAbleList.size(); i++) {
            String item = reachAbleList.get(i);
            Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (!socket.isAddressReachable(item)) {
                        reachAbleList.remove(item);
                    } else {
                        int success = 0;
                        try {
                            success = socket.scan(item, port);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (success == 1) {
                            availableIp.add(new ipInfo(item, port, true, "未知", "TCP"));
                        } else if (success == -1) {
                            availableIp.add(new ipInfo(item, port, true, "未知", "UDP"));
                        } else {
                            reachAbleList.remove(item);
                        }
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

        return availableIp;
    }
}
