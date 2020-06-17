package com.web.severlet;

import com.web.service.dealSingle;
import com.web.ipInfo.ipInfo;
import com.web.response.allRes;
import com.web.service.verify;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "/single")
public class singleScan extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        singlePort(request, response);
    }

    private void singlePort(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String address = request.getParameter("address");
        String port = request.getParameter("port");
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
            verify.verifyPort(port);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            writer.write(allRes.resList("-1", exception.getMessage(), null));
            return;
        }

        List<ipInfo> result = dealSingle.querySingleSocket(address, Integer.parseInt(port));

        writer.write(allRes.resList("1", "success", result ));
    }
}
