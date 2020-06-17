package com.web.service;

import com.sun.deploy.util.StringUtils;
import com.web.enumeration.constValue;

public class verify {
    //错误检验1代表正确，
    public static void verifyAddress(String address) {
        if (address == null || address.isEmpty()){
            throw new RuntimeException("输入不能为空");
        }
        if (!address.matches("((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}")) {
            throw new RuntimeException("请输入正确的IP地址");
        }
    }

    public static void verifyPort(String port) {
        int IntPort = Integer.parseInt(port);
        if (port.isEmpty()) {
            throw new RuntimeException("端口输入不能为空");
        }
        if (IntPort <= 0 || IntPort > constValue.MAX_PORT) {
            throw new RuntimeException("端口范围输入错误");
        }
        if (!port.matches("[0-9]*")) {
            throw new RuntimeException("请输入数字");
        }
    }
}
