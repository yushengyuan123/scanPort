package com.web.dao;

import com.web.task.task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class taskDao {
    private final Connection con;

    public taskDao() throws SQLException, ClassNotFoundException {
        this.con = connectMysql.connect();
    }

    public ResultSet getTaskList() throws SQLException, ClassNotFoundException {
        String sql = "select * from task";
        return connectMysql.select(sql, con);
    }

    public int addTask(task task) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO task (startAddress, endAddress, startPort, endPort, hasFinish, sort) VALUES ('" + task.getJob().getIp() + "','" + task.getJob().getEndIp() + "'," + task.getJob().getPortMin() + ", " + task.getJob().getPortMax() + ", "+ task.getFinishTaskNumber() +",'" + task.getJob().getCharacter()+"');";
        return connectMysql.insert(sql, con);
    }

    public void updateFinish(int taskId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE task set hasFinish = hasFinish + 1 where taskId = " + taskId + "";
        connectMysql.update(sql, con);
    }

    public void close() throws SQLException {
        con.close();
    }

}
