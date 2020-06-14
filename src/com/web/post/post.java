package com.web.post;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import com.google.gson.JsonParser;
import com.web.business.business;
import com.web.ipInfo.ipInfo;
import com.web.socket.socket;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet(name = "/post")
public class post extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String address = request.getParameter("address");
        if (address != null) {
            business test = new business(address);
            test.startScan();
        } else {
            System.out.println("地址为空");
        }
    }
}
