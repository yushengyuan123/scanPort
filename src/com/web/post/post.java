package com.web.post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.web.service.mutiScan;
import com.web.ipInfo.ipInfo;
import com.web.response.allRes;
import com.web.service.verify;

@WebServlet(name = "/post")
public class post extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        try {
            dealMultiScan(request, response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        request.setCharacterEncoding("utf-8");
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
            writer.write(allRes.resList("-1", exception.toString(), null));
        }

        try {
            verify.verifyPort(startPort);
        } catch (Exception exception) {
            writer.write(allRes.resList("-1", exception.toString(), null));
        }

        try {
            verify.verifyPort(endPort);
        } catch (Exception exception) {
            writer.write(allRes.resList("-1", exception.toString(), null));
        }

        int start = Integer.parseInt(startPort);
        int end = Integer.parseInt(endPort);

        List<ipInfo> a = mutiScan.startScan(address, start, end);
        writer.write(allRes.resList("1", "success", a));
    }
}

