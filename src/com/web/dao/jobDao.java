package com.web.dao;

import com.web.ipInfo.ipInfo;
import com.web.task.task;

public class jobDao {
    //为每一个task建立一个job表
    public static void createJob(int taskId) {
        String sql = "CREATE TABLE job_'" + taskId + "'(taskId int NOT NULL" +
                "address VARCHAR(40) NOT NULL" +
                "port VARCHAR(10) NOT NULL" +
                "protocol VARCHAR(10)" +
                "service VARCHAR(10)" +
                "description VARCHAR(10)" +
                ")";
        connectMysql.create(sql);
    }

    //扫描成功后插入成功记录
    public static void insert(int taskId, ipInfo ip) {
        String sql = "INSERT INTO job+ '" + taskId +"' (address, port, protocol, service, description) VALUES" +
                "('" + ip.getIpAddress() +"'" +
                "'" + ip.getPort() +"'" +
                "'" + ip.getAgreement()+ "'" +
                "NULL, NULL)";
        connectMysql.insert(sql);
    }
}
