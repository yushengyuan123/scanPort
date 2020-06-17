package com.web.severlet;

import com.web.ipInfo.ipInfo;
import com.web.response.allRes;
import com.web.service.mutiScan;
import com.web.service.verify;
import utils.ipStatistic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "/addressPhaseScan")
public class addressPhaseScan extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        try {
            dealAddressPhaseScan(request, response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dealAddressPhaseScan(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException {
        String startAddress = request.getParameter("startAddress");
        String endAddress = request.getParameter("endAddress");
        String port = request.getParameter("port");
        PrintWriter writer = response.getWriter();

        List<String> resultIP = ipStatistic.findIPs(startAddress, endAddress);
        writer.write(allRes.resList("1", "success", resultIP));
    }
}
