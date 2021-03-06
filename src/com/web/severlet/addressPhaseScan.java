package com.web.severlet;

import com.web.ipInfo.ipInfo;
import com.web.response.allRes;
import com.web.service.mutiScan;
import com.web.service.phase;
import com.web.service.verify;
import utils.ipStatistic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/addressPhaseScan")
public class addressPhaseScan extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        try {
            dealPhaseScan(request, response);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dealPhaseScan(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        String startAddress = request.getParameter("startAddress");
        String endAddress = request.getParameter("endAddress");
        String port = request.getParameter("startPort");
        String endPort = request.getParameter("endPort");
        PrintWriter writer = response.getWriter();

        try {
            verify.verifyPort(port);
            verify.verifyPort(endPort);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            writer.write(allRes.resList("-1", exception.getMessage(), null));
            return;
        }

        int start = Integer.parseInt(port);
        int end = Integer.parseInt(endPort);

        try {
            verify.rangeERROR(start, end);
        } catch (Exception exception) {
            writer.write(allRes.resList("-1", exception.getMessage(), null));
            return;
        }

        /** 数据录入成功之后向前端返回 */
        if (phase.addPhaseTask(startAddress, endAddress, port, endPort)) {
            writer.write(allRes.resList("1", "添加任务成功", null));
        } else {
            writer.write(allRes.resList("-1", "添加任务失败", null));
        }
    }
}
