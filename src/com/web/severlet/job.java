package com.web.severlet;

import com.web.response.allRes;
import com.web.service.taskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/job")
public class job extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");

        try {
            getJob(request, response);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        response.setHeader("Content-Type", "application/json");
    }

    public void getJob(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        String taskId = request.getParameter("taskId");
        PrintWriter writer = response.getWriter();
        List list = new ArrayList();

        list = taskService.getJobList(taskId);

        writer.write(allRes.resList("1", "success", list));
    }
}
