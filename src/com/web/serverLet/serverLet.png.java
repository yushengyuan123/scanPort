package com.web.serverLet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/serverLet/*")
class serverLet extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        message = "hello world";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("i get post");
        System.out.println(request);
    }

}

