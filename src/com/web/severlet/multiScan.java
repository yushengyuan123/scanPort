package com.web.severlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.web.ipInfo.job;
import com.web.service.mutiScan;
import com.web.ipInfo.ipInfo;
import com.web.response.allRes;
import com.web.service.verify;
import com.web.task.task;

@WebServlet(name = "/multiScan")
public class multiScan extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        try {
            addMultiTask(request, response);
        } catch (InterruptedException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dealMultiScan(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException {
        String address = request.getParameter("address");
        String startPort = request.getParameter("startPort");
        String endPort = request.getParameter("endPort");
        PrintWriter writer = response.getWriter();

        //地址检验
        try {
            verify.verifyAddress(address);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            writer.write(allRes.resList("-1", exception.getMessage(), null));
            return;
        }

        try {
            verify.verifyPort(startPort);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            writer.write(allRes.resList("-1", exception.getMessage(), null));
            return;
        }

        try {
            verify.verifyPort(endPort);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            writer.write(allRes.resList("-1", exception.getMessage(), null));
            return;
        }

        int start = Integer.parseInt(startPort);
        int end = Integer.parseInt(endPort);

        try {
            verify.rangeERROR(start, end);
        } catch (Exception exception) {
            writer.write(allRes.resList("-1", exception.getMessage(), null));
            return;
        }

        List<ipInfo> a = mutiScan.startScan(address, start, end);
        writer.write(allRes.resList("1", "success", a));
    }

    public void addMultiTask(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, SQLException, IOException, ClassNotFoundException {
        String address = request.getParameter("address");
        String startPort = request.getParameter("startPort");
        String endPort = request.getParameter("endPort");
        PrintWriter writer = response.getWriter();

        /** 数据录入成功之后向前端返回 */
        if (mutiScan.test(address, startPort, endPort)) {
            writer.write(allRes.resList("1", "添加任务成功", null));
        } else {
            writer.write(allRes.resList("-1", "添加任务失败", null));
        }
    }
}

