package com.web.service;

import com.web.dao.taskDao;
import com.web.enumeration.jobCharacter;
import com.web.ipInfo.ipInfo;
import com.web.ipInfo.job;
import com.web.socket.socket;
import com.web.task.task;
import utils.ipStatistic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class phase {
    public static boolean addPhaseTask(String startAddress, String endAddress , String startPort, String endPort) throws SQLException, ClassNotFoundException {
        task phaseTask = new task(jobCharacter.MULTIADDRESS);
        job phaseJob = new job(startAddress, endAddress, null, startPort, endPort, jobCharacter.MULTIADDRESS);
        taskDao taskDao = new taskDao();

        if (phaseTask.addTask(phaseJob)) {
            //任务添加成功，写入数据库
            int id = taskDao.addTask(phaseTask);
            //添加完任务然后开始交给另外的线程进行处理
            phaseTask.doPhase(id);
            return true;
        } else {
            System.out.println("任务添加失败");
            return false;
        }
    }

    public static List<ipInfo> dealPhase(String startAddress, String endAddress , int port, int endPort) throws InterruptedException {
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
