package com.web.service;

import com.web.dao.jobDao;
import com.web.dao.taskDao;
import utils.ipStatistic;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class taskService {
    //todo 这段代码阻塞了
    public List getTask() throws SQLException, ClassNotFoundException {
        List list = new ArrayList();
        taskDao dao = new taskDao();
        ResultSet rs = dao.getTaskList();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();

        while (rs.next()) {
            Map rowData = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                //如果扫描返回地址段，则后台假如地址段的数目
                if (md.getColumnName(i).equals("sort")) {
                    if (rs.getObject(i).equals("MULTIADDRESS")) {
                        List<String> number = ipStatistic.findIPs(
                                rs.getObject(2).toString(), rs.getObject(3).toString());
                        rowData.put("addressNumber", number.size());
                    }

                }
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        rs.close();

        return list;
    }

    //获得作业列表
    public static List getJobList(String taskId) throws SQLException, ClassNotFoundException {
        jobDao jobDao = new jobDao();

        List list = new ArrayList();
        ResultSet rs = jobDao.getJobList(taskId);
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();

        while (rs.next()) {
            Map rowData = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        rs.close();
        return list;
    }
}
