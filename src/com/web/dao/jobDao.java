package com.web.dao;

import com.web.ipInfo.ipInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jobDao {
    private final Connection con;

    public jobDao() throws SQLException, ClassNotFoundException {
        this.con = connectMysql.connect();
    }

    //为每一个task建立一个job表
    public void createJob(int taskId) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE job_" + taskId + "(taskId int NOT NULL, " +
                "address VARCHAR(40) NOT NULL," +
                "port VARCHAR(10) NOT NULL," +
                "protocol VARCHAR(10)," +
                "service VARCHAR(10)," +
                "description VARCHAR(10)," +
                "CONSTRAINT job_"+ taskId +" FOREIGN KEY (taskId) REFERENCES task(taskId) " +
                "ON DELETE CASCADE ON UPDATE CASCADE" +
                ")";
        connectMysql.create(sql, con);
    }

    //扫描成功后插入成功记录
    public void insert(int taskId, ipInfo ip) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO job_" + taskId + " (taskId, address, port, protocol, service, description) VALUES" +
                "(" + taskId +", " +
                "'" + ip.getIpAddress() +"'" +
                "," + ip.getPort() +"" +
                ",'" + ip.getAgreement()+ "', " +
                "NULL, NULL)";
        connectMysql.insertWithKey(sql, con);
    }

    public ResultSet getJobList(String taskId) throws SQLException, ClassNotFoundException {
        String sql = "select * from job_" + taskId;
        return connectMysql.select(sql, con);
    }
}
