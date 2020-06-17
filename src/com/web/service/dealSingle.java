package com.web.service;

import com.web.ipInfo.ipInfo;
import com.web.socket.socket;

import java.io.IOException;

public class dealSingle {
    public static ipInfo[] querySingleSocket(String address, int port) throws IOException {
        //true代表扫描成功，false，代表扫描不成功
        ipInfo[] array = new ipInfo[1];
        ipInfo info = null;
        int scanResult = socket.scan(address, port);
        if (scanResult == 1) {
            info = new ipInfo(address, port, true,"未知", "TCP");
        }
        array[0] = info;
        return array;
    }
}
