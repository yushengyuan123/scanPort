package com.web.post;

import com.google.gson.Gson;
import com.web.service.dealSingle;
import com.web.ipInfo.ipInfo;
import com.web.response.allRes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        ipInfo[] info = dealSingle.querySingleSocket(address, Integer.parseInt(port));
        allRes res = new allRes("1", "success", info);
        PrintWriter writer = response.getWriter();
        Gson gson = new Gson();
        writer.write(gson.toJson(res));
    }
}
