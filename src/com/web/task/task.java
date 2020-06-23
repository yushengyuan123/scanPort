package com.web.task;

import com.sun.deploy.net.MessageHeader;
import com.web.dao.jobDao;
import com.web.dao.taskDao;
import com.web.enumeration.jobCharacter;
import com.web.ipInfo.ipInfo;
import com.web.ipInfo.job;
import com.web.socket.socket;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import utils.ipStatistic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 任务状态
 */
public class task {
    private Integer taskId;

    private Integer finishTaskNumber;

    /**
     * 任务信息
     */
    private job job;

    private Integer finish_number = 0;

    /**
     * 存放结果
     */
    private final List<ipInfo> result = new ArrayList<>();

    public task(String sort) {
        this.finishTaskNumber = 0;
    }


    /**
     * 添加任务
     */
    public boolean addTask(job jobVo) {
        if (this.job == null) {
            this.job = jobVo;
        } else {
            return false;
        }
        return true;
    }

    public void doMultiScan(int taskId) throws SQLException, ClassNotFoundException {
        jobDao jobDao = new jobDao();
        taskDao taskDao = new taskDao();

        if (job.getCharacter().equals(jobCharacter.MULTIPORT)) {
            List<ipInfo> scanResults = new ArrayList<>();
            jobDao.createJob(taskId);

            for (int i = job.getPortMin(); i < job.getPortMax(); i++) {
                int finalI = i;
                Thread childThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int success = socket.scan(job.getIp(), finalI);
                            if (success == 1) {
                                ipInfo ipVo = new ipInfo(job.getIp(), finalI, true, "未知", "TCP");
                                result.add(ipVo);
                                jobDao.insert(taskId, ipVo);
                            } else if (success == 0) {
                                ipInfo ipVo = new ipInfo(job.getIp(), finalI, true, "未知", "UDP");
                                result.add(ipVo);
                                jobDao.insert(taskId, ipVo);
                            }
                            taskDao.updateFinish(taskId);
                        } catch (IOException | SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                childThread.start();
            }
        } else {
            throw new RuntimeException("任务类型不对口");
        }
    }

    public void doPhase(int taskId) throws SQLException, ClassNotFoundException {
        jobDao jobDao = new jobDao();
        taskDao taskDao = new taskDao();

        if (job.getCharacter().equals(jobCharacter.MULTIADDRESS)) {
            //获取所有可到达的ip地址列表
            List<String> reachAbleList = ipStatistic.findIPs(job.getIp(), job.getEndIp());
            jobDao.createJob(taskId);

            for (String item : reachAbleList) {
                for (int i = job.getPortMin(); i < job.getPortMax(); i++) {
                    int finalI = i;
                    Thread childThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //先检测该端口是否可达到
                                if (socket.isAddressReachable(item)) {
                                    System.out.println(item);
                                    try {
                                        int success = socket.scan(item, finalI);
                                        if (success == 1) {
                                            ipInfo ipVo = new ipInfo(item, finalI, true, "未知", "TCP");
                                            result.add(ipVo);
                                            jobDao.insert(taskId, ipVo);
                                        } else if (success == 0) {
                                            ipInfo ipVo = new ipInfo(item, finalI, true, "未知", "UDP");
                                            result.add(ipVo);
                                            jobDao.insert(taskId, ipVo);
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                taskDao.updateFinish(taskId);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    childThread.start();
                }
            }
        } else {
            throw new RuntimeException("任务类型不对口");
        }
    }


    public job getJob() {
        return job;
    }

    public Integer getFinishTaskNumber() {
        return finishTaskNumber;
    }

}
