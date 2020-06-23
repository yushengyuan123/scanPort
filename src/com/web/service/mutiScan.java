package com.web.service;

import com.web.dao.taskDao;
import com.web.enumeration.jobCharacter;
import com.web.ipInfo.ipInfo;

import com.web.ipInfo.job;
import com.web.socket.socket;
import com.web.task.task;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                            scanResults.add(new ipInfo(address, finalI, true, "未知", "UDP"));
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

    public static boolean test(String address, String startPort, String endPort) throws SQLException, ClassNotFoundException {
            task multiTask = new task(jobCharacter.MULTIPORT);
            job multiJob = new job(address, null, null, startPort, endPort, jobCharacter.MULTIPORT);
            taskDao taskDao = new taskDao();

            if (multiTask.addTask(multiJob)) {
                //任务添加成功，写入数据库
                int id = taskDao.addTask(multiTask);
                //添加完任务然后开始交给另外的线程进行处理
                multiTask.doMultiScan(id);
                return true;
            } else {
                System.out.println("任务添加失败");
            }
        return false;
    }
}
