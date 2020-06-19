package com.web.task;

import com.web.dao.jobDao;
import com.web.dao.taskDao;
import com.web.enumeration.jobCharacter;
import com.web.ipInfo.ipInfo;
import com.web.ipInfo.job;
import com.web.socket.socket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 存放结果
     */
    private final List<ipInfo> result = new ArrayList<>();

    public task(Integer taskID) {
        this.taskId = taskID;
    }

    /**
     * 添加任务
     */
    public boolean addTask(job jobVo) {
        System.out.println("添加任务完成");
        if (this.job == null) {
            this.job = jobVo;
        } else {
            return false;
        }
        return true;
    }

    public void doMultiScan() {
        if (job.getCharacter() == jobCharacter.MULTIPORT) {
            List<ipInfo> scanResults = new ArrayList<>();
            for (int i = job.getPortMin(); i < job.getPortMax(); i++) {
                int finalI = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int success = socket.scan(job.getIp(), finalI);

                            if (success == 1) {
                                ipInfo ipVo = new ipInfo(job.getIp(), finalI, true, "未知", "TCP");
                                scanResults.add(ipVo);
                                jobDao.insert(taskId, ipVo);
                            } else if (success == -1) {
                                ipInfo ipVo = new ipInfo(job.getIp(), finalI, true, "未知", "UDP");
                                scanResults.add(ipVo);
                                jobDao.insert(taskId, ipVo);
                            }

                            taskDao.updateFinish(taskId);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            throw new RuntimeException("任务类型不对口");
        }
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public job getJob() {
        return job;
    }
}
