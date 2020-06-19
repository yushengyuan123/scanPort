package com.web.dao;

import com.web.task.task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class taskDao {
    public static ResultSet getTaskList() {
        String sql = "select * from task";
        return connectMysql.select(sql);
    }

    public static void addTask(task task) {
        String sql = "INSERT INTO task (taskId, startAddress, endAddress, startPort, endPort, hasFinish) VALUES ('" + task.getTaskId() + "','" + task.getJob().getIp() + "','" + task.getJob().getPort() + "','" + task.getJob().getPortMin() + "','" + task.getJob().getPortMax() + ", 0');";
        connectMysql.insert(sql);
    }

    public static void updateFinish(int taskId) throws SQLException, ClassNotFoundException {
        String numberSql = "select hasFinish from task where taskId = '" + taskId +"'";
        String sql = "UPDATE task set hasFinish = '" + numberSql + 1 + "' where taskId = '" + taskId + "'";
        System.out.println(sql);
        connectMysql.update(sql);
    }
}
