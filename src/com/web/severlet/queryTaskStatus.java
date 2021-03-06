package com.web.severlet;

import com.web.dao.connectMysql;
import com.web.response.allRes;
import com.web.service.taskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/task")
public class queryTaskStatus extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        String path = request.getPathInfo();

        try {
            getTaskList(request, response);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.setHeader("Content-Type", "application/json");
    }

    public void getTaskList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        List list = new ArrayList();
        PrintWriter writer = response.getWriter();
        taskService newTask = new taskService();
        list = newTask.getTask();
        writer.write(allRes.resList("1", "success", list));
    }
}
